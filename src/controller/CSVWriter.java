//package controller;
//
//import model.SQLiteConnector;
//
//import javax.swing.table.DefaultTableModel;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Created by Tobias on 07/12/15.
// * In reference to "Programmieren Lernen mit Java - Chapter 11 - File IO"
// */
//public static class CSVWriter {
//
//    static BufferedWriter out = null;
//    static Connection connection = null;
//    static File filename = null;
//
//    public static void writeFile(file filename) {
//
//
//        connection = SQLiteConnector.dbConnector();
//
//        try {
//            out = new BufferedWriter(new FileWriter(filename));
//
//
//                String query = "select patientID, firstname, lastname, medicalCondition, nextAppointment, phoneNumber, billing, comment from PatientInfo";
//                PreparedStatement pst = connection.prepareStatement(query);
//                ResultSet rs = pst.executeQuery();
//
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                while (rs.next()) {
//                    model.addRow(new String[]{
//                            rs.getString("patientID"),
//                            rs.getString("firstname"),
//                            rs.getString("lastname"),
//                            rs.getString("medicalCondition"),
//                            rs.getString("nextAppointment"),
//                            rs.getString("phoneNumber"),
//                            rs.getString("billing"),
//                            rs.getString("comment")
//                    });
//                }
//                table.sizeColumnsToFit(0);
//
//                rs.close();
//                pst.close();
//
//
//
//
//        } catch (IOException, SQLException e1) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
//            }
//        }
//
//
//
//    }
//
//
//}
//
