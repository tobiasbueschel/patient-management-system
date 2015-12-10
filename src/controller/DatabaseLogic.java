package controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FilenameUtils;

import model.SQLiteConnector;

public class DatabaseLogic {

	private static int patientID;
	private  File profilePhoto = null;
	private  ArrayList<File> medicalImages = new ArrayList<File>();
	private  ArrayList<Image> mImg = new ArrayList<Image>();

	public Connection conn = null;
	
	// DEFAULT CONSTRUCTOR
	public DatabaseLogic(){
		patientID = 0;
	}

	// CONSTRUCTOR CALLED WHEN PATIENT IS OPENED
	public DatabaseLogic(int patientID){
		DatabaseLogic.patientID = patientID;
	}
	
	// OPENS DATABASE CONNECTION
	public void openConnection(){
		conn = SQLiteConnector.dbConnector();
		try {conn.setAutoCommit(false);}
		catch (SQLException e) {e.printStackTrace();}
	}
	
	// CLOSES DATABASE CONNECTION
	public void closeConnection(){
		try {this.conn.close();}
		catch (SQLException e) {e.printStackTrace();}
	}
	
	
	// ========================================= SETTER METHODS ============================================
	
	/** inserts new row with an automatically generated patientID */
	public void insertPatientID(){
		
		System.out.println("inserPatientID " + patientID);
		
		if (DatabaseLogic.patientID == 0){
		try {
			String sql = "insert into PatientInfo default values";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int aRows = stmt.executeUpdate();
			conn.commit();
			
			
			/** sets new patientID @link http://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc */
			 if (aRows == 0) {
		            throw new SQLException("Failed to create new patient.");
		        }

		        try (ResultSet genKeys = stmt.getGeneratedKeys()) {
		            if (genKeys.next()) {
		                DatabaseLogic.patientID = genKeys.getInt(1);
		            }
		            else {
		                throw new SQLException("Failed to create new patient.");
		            }
		        }				
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}
	
	public ImageIcon setProfilePhoto() {
		Image scaledImg = null;
		
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());        
        JFileChooser fc = new JFileChooser();

        fc.addChoosableFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);
        
        int status = fc.showOpenDialog(null);
        
        if (status == JFileChooser.APPROVE_OPTION) {
        	profilePhoto = fc.getSelectedFile();
        }
		
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(profilePhoto));
			scaledImg = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        scaledImg = scaledImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
		
		return new ImageIcon(scaledImg);

	}
	
	
	public void insertProfilePhoto(){
		
		if (profilePhoto != null){
		try {
			String sql = "update PatientInfo SET profilePhoto=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			FileInputStream   fis = new FileInputStream(profilePhoto);
			stmt.setBinaryStream(1, fis, (int) profilePhoto.length());
			stmt.setInt(2, DatabaseLogic.patientID);
			System.out.println(DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		}
		
	}
	
	
	public ImageIcon setMedicalImages(){
		File file = null;
		Image img = null;
		InputStream is = null;
    	
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        JFileChooser fc = new JFileChooser();

        fc.addChoosableFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);

        int status = fc.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
        	file = fc.getSelectedFile();
        }
            try {
                is = new BufferedInputStream(new FileInputStream(file));
                img = ImageIO.read(is);
                img = img.getScaledInstance(310, 200, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return new ImageIcon(img);
 }
        
	

	public void insertMedicalImages(){
        FileInputStream fis = null;
        File file = null;
        
        if ( !medicalImages.isEmpty() ){
        
		try {			
        	for (int i = 0; i < medicalImages.size(); i++){
        		System.out.println(i);
        		System.out.println(medicalImages.size());
        		System.out.println(medicalImages.get(i));
        		file = medicalImages.get(i);
				fis = new FileInputStream(file);
		        String sql = "insert or ignore into PatientImages (patientID, patientImage) values(?,?)";       
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        
		        stmt.setInt(1, DatabaseLogic.patientID);
		        stmt.setBinaryStream(2, fis, (int) file.length());
		        stmt.execute();
	
		        conn.commit();
		        fis.close();
		        stmt.close();
        	}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        }
	
	}
	
	public void insertFirstName(String firstName){
		
		try {
			String sql = "update PatientInfo SET firstName=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, firstName);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertLastName(String lastName){
		
		try {
			String sql = "update PatientInfo SET lastName=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, lastName);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertDOB(){
		
	}
	
	public void insertStreet(String street){
		
		try {
			String sql = "update PatientInfo SET street=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, street);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertPostCode(String postCode){
		
		try {
			String sql = "update PatientInfo SET postCode=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, postCode);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertCity(String city){
		
		try {
			String sql = "update PatientInfo SET city=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, city);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertPhoneNumber(String phoneNumber){
		
		try {
			String sql = "update PatientInfo SET phoneNumber=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, phoneNumber);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void insertEmergency(String emergencyNumber){
		
		try {
			String sql = "update PatientInfo SET emergencyNumber=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, emergencyNumber);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertGender(String gender){
		
		try {
			String sql = "update PatientInfo SET gender=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, gender);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertMedicalCondition(String medicalCondition){
		
		try {
			String sql = "update PatientInfo SET medicalCondition=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, medicalCondition);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertBilling(String billing){
		
		try {
			String sql = "update PatientInfo SET billing=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, billing);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertComment(String comment){
		
		try {
			String sql = "update PatientInfo SET comment=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, comment);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertInsurance(String insurance){
		
		try {
			String sql = "update PatientInfo SET insurance=? where patientID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, insurance);
			stmt.setInt(2, DatabaseLogic.patientID);
			stmt.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertNextAppointment(){
		
	}
	
	// ========================================= GETTER METHODS ============================================
	
	public String getFirstName(int patientID){
		String firstName = null;

	        try {
	            String sql = "select firstName from PatientInfo where patientID=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, patientID);          
	            ResultSet rs = stmt.executeQuery();	            
	            firstName = rs.getString(1);
	            rs.close();
	         
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		return firstName;
	}
	
	public String getLastName(int patientID){
		String lastName = null;

        try {
            String sql = "select lastName from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            lastName = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return lastName;
	}
	
	public String getDOB(int patientID){
		String lastName = null;
		
		
		return lastName;
	}	
	
	public String getStreet(int patientID){
		String street = null;

        try {
            String sql = "select street from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            street = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return street;
	}	
	
	public String getPostCode(int patientID){
		String postCode = null;

        try {
            String sql = "select postCode from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            postCode = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return postCode;
	}
	
	public String getCity(int patientID){
		String city = null;
		
        try {
            String sql = "select city from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            city = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return city;
	}	
	
	
	public String getPhoneNumber(int patientID){
		String phoneNumber = null;
		
        try {
            String sql = "select phoneNumber from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            phoneNumber = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return phoneNumber;
	}	
	
	
	public String getEmergencyNumber(int patientID){
		String emergencyNumber = null;
		
        try {
            String sql = "select emergencyNumber from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            emergencyNumber = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return emergencyNumber;
	}	
	
	public String getGender(int patientID){
		String gender = null;
		
        try {
            String sql = "select gender from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            gender = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return gender;
	}
	
	public String getMedicalCondition(int patientID){
		String medicalCondition = null;
		
        try {
            String sql = "select medicalCondition from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            medicalCondition = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return medicalCondition;
	}
	
	public String getBilling(int patientID){
		String billing = null;
		
        try {
            String sql = "select billing from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            billing = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return billing;
	}
	
	public String getComment(int patientID){
		String comment = null;
		
        try {
            String sql = "select comment from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            comment = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return comment;
	}
	
	public String getInsurance(int patientID){
		String insurance = null;
		
        try {
            String sql = "select insurance from PatientInfo where patientID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            ResultSet rs = stmt.executeQuery();	            
            insurance = rs.getString(1);
            rs.close();
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return insurance;
	}
	
	public ImageIcon getProfilePhoto(int patientID){
		BufferedImage buffImg = null;
		Image img = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        try {
            String sql = "select * from PatientInfo where patientID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            rs = stmt.executeQuery();

            byte[] bl = rs.getBytes("profilePhoto");
            
            if ( bl != null){
            	   buffImg = ImageIO.read(new ByteArrayInputStream(bl));
                   img = buffImg;
                   img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // source: http://stackoverflow.com/questions/17762404/resizing-image-to-fit-exactly-jlabel-of-300-by-300-px
            }
            else{
            	switch (rs.getString("gender")){
            	case "Male": 
            		return new ImageIcon("images/profile.photos/default_male.png");
            	case "Female":
            		return new ImageIcon("images/profile.photos/default_female.png");
            	}
            }
          
			} catch (Exception e) {
				e.printStackTrace();
			}
        	finally{
        		try {rs.close();} catch (SQLException e) {e.printStackTrace();}
                try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
        	}

			return new ImageIcon(img);   
	}
	
	public File getNextAppointment(int patientID){
		File nextAppointment = null;
		
		
		return nextAppointment;
	}
	
	public ArrayList<Image> getMedicalImages(int patientID){
		BufferedImage buffImg = null;
		Image img = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		       
        try{
        	
            String sql = "select patientImage from PatientImages where patientID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientID);          
            rs = stmt.executeQuery();
            System.out.println(" Hello ich bin es + size of array" + mImg.size());

	        
            while(rs.next()){
	            byte[] bl = rs.getBytes("patientImage");
		            		            
		            if ( bl != null){
		            	   buffImg = ImageIO.read(new ByteArrayInputStream(bl));
		                   img = buffImg;
		                   mImg.add(img);
		            }
            }
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	finally{
    		try {rs.close();} catch (SQLException e) {e.printStackTrace();}
            try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
    	}
		
		return mImg;
	}
	
	
	public File getMedicalImage(int index) {
		return medicalImages.get(index);
	}
	
	
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		DatabaseLogic.patientID =  patientID;
	}
	
	public void deleteMedicalImage(){
        FileInputStream fis = null;
        File file = null;
        
        if ( !medicalImages.isEmpty() ){
        
		try {			
        	for (int i = 0; i < medicalImages.size(); i++){
        		System.out.println(i);
        		System.out.println(medicalImages.size());
        		System.out.println(medicalImages.get(i));
        		file = medicalImages.get(i);
				fis = new FileInputStream(file);
		        String sql = "insert or ignore into PatientImages (patientID, patientImage) values(?,?)";       
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        
		        stmt.setInt(1, DatabaseLogic.patientID);
		        stmt.setBinaryStream(2, fis, (int) file.length());
		        stmt.execute();
	
		        conn.commit();
		        fis.close();
		        stmt.close();
        	}

		} catch (Exception e) {
			e.printStackTrace();
		}
        }
	}
}
