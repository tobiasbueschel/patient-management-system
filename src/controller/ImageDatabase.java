package controller;

import model.SQLiteConnector;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 * Created by Tobias on 08/12/15.
 */
public class ImageDatabase {

    File imgFile = null;
    String imgPath = null;

    public ImageDatabase(){
    	openImage();
    }
    
	
    public void openImage(){
    	
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());        
        JFileChooser fc = new JFileChooser();

        fc.addChoosableFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);
        
        int status = fc.showOpenDialog(null);
        
        if (status == JFileChooser.APPROVE_OPTION) {
            imgFile = fc.getSelectedFile();
            imgPath = imgFile.getPath();

        }

    }


    
    
    
    public void insertProfileImages(int patientID) {
    	try {
			Connection conn = SQLiteConnector.dbConnector();
			conn.setAutoCommit(false);
			
			String sql = "insert into PatientInfo (profilePhoto) values(?)";
			String sql2 = "update PatientInfo SET profilePhoto=? where patientID=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql2);

			FileInputStream   fis = new FileInputStream(imgFile);
			stmt.setBinaryStream(1, fis, (int) imgFile.length());
			stmt.setInt(2, patientID);
			stmt.execute();
			System.out.println("hello");

			conn.commit();
			fis.close();
			conn.close();
			System.out.println("image upload worked");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
    
    
    public void insertMedicalImages(int patientID) throws Exception {
    	Connection conn = SQLiteConnector.dbConnector();
        conn.setAutoCommit(false);
        
        String sql = "insert or ignore into PatientImages (patientID, patientImage) values(?,?)";
               
        PreparedStatement stmt = conn.prepareStatement(sql);

        FileInputStream   fis = new FileInputStream(imgFile);
        stmt.setInt(1, patientID);
        stmt.setBinaryStream(2, fis, (int) imgFile.length());
        stmt.execute();

        conn.commit();
        fis.close();
        conn.close();
      }

}

//
//    
//    public void setPatientPhoto() {
//      String ext = "." + FilenameUtils.getExtension(imgFile.getAbsolutePath()); // http://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
//
//      new CopyFile(imgFile.getAbsolutePath(), "images/profile.photos/profile" + ext);
//
//      InputStream is;
//      try {
//          is = new BufferedInputStream(new FileInputStream("images/profile.photos/profile" + ext));
//          Image savedImg = ImageIO.read(is);
//          savedImg = savedImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
//          lblPatientPhoto.setIcon(new ImageIcon(savedImg));
//    }
//    
//    }

