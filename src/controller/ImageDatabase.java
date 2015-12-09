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

    public ImageDatabase(int patientID){
    	openImage();
    	try {
			insertImage(patientID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//            System.out.println(imgFile.getPath());
        }
        
//        try {
//            is = new FileInputStream(imgPath);
//
//            is.close();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
    }

//    public void read(InputStream is){
//            try {
//				imgByte = IOUtils.toByteArray(is);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    }
    
    
    
    public void insertImage(int patientID) throws Exception {
    	Connection conn = SQLiteConnector.dbConnector();
        conn.setAutoCommit(false);

        String sql = "INSERT INTO PatientInfo (profilePhoto) where patientID=" + patientID + "VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        FileInputStream   fis = new FileInputStream(imgFile);
        stmt.setBinaryStream(1, fis, (int) imgFile.length());
        stmt.execute();

        conn.commit();
        fis.close();
        conn.close();
        System.out.println("image upload worked");
      }



//    public void insertImage(int patientID){
//        Connection conn = SQLiteConnector.dbConnector();
//
////        String delSql                       =   "DELETE FROM ACCOUNTS";
////        SQLiteStatement delStmt         =   db.compileStatement(delSql);
////        delStmt.execute();
//        
//        String sql ="insert into PatientInfo (profilePhoto) where patientID=" + patientID + "VALUES(?)";
//        Statement st = conn.prepareStatement(sql);
//        
//        
//        st.bindBlob(3, this.imgByte);
//        st.setBinaryStream(1, this.imgByte);
//        
//        conn.close();
//    }

    
//    public Account getCurrentAccount() {
//        SQLiteDatabase db       =   dbHelper.getWritableDatabase();
//        String sql              =   "SELECT * FROM ACCOUNTS";
//        Cursor cursor           =   db.rawQuery(sql, new String[] {});
//
//        if(cursor.moveToFirst()){
//            this.accId             = cursor.getInt(0);
//            this.accName           = cursor.getString(1);
//            this.accImage          = cursor.getBlob(2);
//        }
//        if (cursor != null && !cursor.isClosed()) {
//            cursor.close();
//        }
//        db.close();
//        if(cursor.getCount() == 0){
//            return null;
//        } else {
//            return this;
//        }
    
    public void setPatientPhoto() {
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
    }
    
    }

