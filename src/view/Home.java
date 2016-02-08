package view;

import model.SQLiteConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.DatabaseLogic;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  <p>
 *      Home is the main class of the PRMS application.
 *      It hosts a JTabel that displays all patients.
 *  </p>
 *
 *  @author Tobias Büschel
 *  @version 0.7
 */
public class Home extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection = null;
    private JPanel contentPane;
    private JTextField txtSearch;
    private JTable table;
    private JPanel mainPanel;

    /**
<<<<<<< HEAD
     * Create the frame.
=======
     * Creates the home frame.
>>>>>>> 8f89938e3b67df0e005b64ff9f79cb3e5ca5137f
     */
    public Home() {
    	DatabaseLogic dl = new DatabaseLogic();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setResizable(false);
        contentPane.setLayout(null);


        // ==================================== HEADER MENU =============================================
        /** MENU: Panel */
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 1000, 50);
        menuPanel.setLayout(null);
        menuPanel.setBackground(CustomColors.green);
        
        JLabel lblToday = new JLabel();
        DateFormat df = new SimpleDateFormat("E dd MMM");
        Date date = new Date();
        lblToday.setText(df.format(date));
        lblToday.setForeground(CustomColors.white);
        lblToday.setBackground(CustomColors.green);
        lblToday.setBounds(138, 0, 188, 50);
        lblToday.setVisible(true);
        menuPanel.add(lblToday);

        /** MENU: title */
        JLabel lblTitle = new JLabel("PRMS");
        lblTitle.setForeground(CustomColors.white);
        lblTitle.setBackground(CustomColors.green);
        lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        lblTitle.setBounds(37, 0, 70, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setOpaque(true);
        menuPanel.add(lblTitle);


        /** MENU: search field */
        txtSearch = new JTextField();
        txtSearch.setText("Search...");
        txtSearch.setBounds(370, 12, 176, 28);
        txtSearch.setColumns(10);
        menuPanel.add(txtSearch);


        /** MENU: button - new patient */
        JButton btnNewPatient = new JButton();
        btnNewPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Profile profile = new Profile();
                profile.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewPatient.setIcon(new ImageIcon("images/new_inverted.png"));
                btnNewPatient.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewPatient.setIcon(new ImageIcon("images/new.png"));
                btnNewPatient.setOpaque(false);
            }
        });
        btnNewPatient.setBackground(CustomColors.white);
        btnNewPatient.setIcon(new ImageIcon("images/new.png"));
        btnNewPatient.setBounds(612, -1, 50, 50);
        btnNewPatient.setBorderPainted(false);
        menuPanel.add(btnNewPatient);


        /** MENU: button - select all */
        JButton btnSelect = new JButton();
        btnSelect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSelect.setIcon(new ImageIcon("images/select_inverted.png"));
                btnSelect.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSelect.setIcon(new ImageIcon("images/select.png"));
                btnSelect.setOpaque(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRowCount() != table.getRowCount()) {
                    table.selectAll();
                } else {
                    table.clearSelection();
                }
            }
        });
        btnSelect.setBackground(CustomColors.white);
        btnSelect.setIcon(new ImageIcon("images/select.png"));
        btnSelect.setBounds(672, -1, 50, 50);
        btnSelect.setBorderPainted(false);
        menuPanel.add(btnSelect);


        /** MENU: button - import */
        JButton btnImport = new JButton();
        btnImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnImport.setIcon(new ImageIcon("images/import_inverted.png"));
                btnImport.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnImport.setIcon(new ImageIcon("images/import.png"));
                btnImport.setOpaque(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);

            }
        });
        btnImport.setBackground(CustomColors.white);
        btnImport.setIcon(new ImageIcon("images/import.png"));
        btnImport.setBounds(732, -1, 50, 50);
        btnImport.setBorderPainted(false);
        menuPanel.add(btnImport);

        /** MENU: button - export */
        JButton btnExport = new JButton();
        btnExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnExport.setIcon(new ImageIcon("images/export_inverted.png"));
                btnExport.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExport.setIcon(new ImageIcon("images/export.png"));
                btnExport.setOpaque(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
            }
        });
        btnExport.setBackground(CustomColors.white);
        btnExport.setIcon(new ImageIcon("images/export.png"));
        btnExport.setBounds(792, -1, 50, 50);
        btnExport.setBorderPainted(false);
        menuPanel.add(btnExport);

        /** MENU: button - delete */
        JButton btnDelete = new JButton();
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDelete.setIcon(new ImageIcon("images/delete_inverted.png"));
                btnDelete.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDelete.setIcon(new ImageIcon("images/delete.png"));
                btnDelete.setOpaque(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            	
                int reply = JOptionPane.showConfirmDialog(null, "Would you like to delete this patient?" , "Delete Patient" , JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                	
                		if (table.getRowCount() > 0) {
                			if (table.getSelectedRowCount() > 0) {
                				int selectedRow[] = table.getSelectedRows();
                				for (int i : selectedRow) {
                					try {
                						dl.openConnection();
	                                     String query = "delete from PatientInfo where patientID=?";
	                                     PreparedStatement stmt = dl.conn.prepareStatement(query);
	                                     
	                        			stmt.setString(1, (String) table.getModel().getValueAt(i, 0));
	                        			stmt.execute();

	                        			dl.conn.commit();
	                                     
	                        			stmt.execute();
	                        			stmt.close();
	                                     ((DefaultTableModel) table.getModel()).removeRow(i);
	                                 	dl.closeConnection();

                                 } catch (SQLException e1) {
                                     System.err.println("No connection with SQLite possible.");
                                     e1.printStackTrace();
                                 } 
                            }
                        }
                    }
                }
            else {
                JOptionPane.getRootFrame().dispose();
             }
           JOptionPane.showMessageDialog(null, "Successful deleted");
            }});

        btnDelete.setBackground(CustomColors.white);
        btnDelete.setIcon(new ImageIcon("images/delete.png"));
        btnDelete.setBounds(852, -1, 50, 50);
        btnDelete.setBorderPainted(false);
        menuPanel.add(btnDelete);

        /** MENU: button - logout */
        JButton btnLogout = new JButton();
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogout.setIcon(new ImageIcon("images/logout_inverted.png"));
                btnLogout.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogout.setIcon(new ImageIcon("images/logout.png"));
                btnLogout.setOpaque(false);
            }
        });
        btnLogout.setBackground(CustomColors.white);
        btnLogout.setIcon(new ImageIcon("images/logout.png"));
        btnLogout.setBounds(912, -1, 50, 50);
        btnLogout.setBorderPainted(false);
        menuPanel.add(btnLogout);

        contentPane.add(menuPanel);


        // ============================================ MAIN PANEL ============================================
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 50, 1000, 528);
        mainPanel.setBackground(CustomColors.grey);
        contentPane.add(mainPanel);
        mainPanel.setLayout(null);


        // ============================================ JTABLE =================================================
        /** creates the table model that stores the JTable columns & data */
        TableModel jTable1Model = new DefaultTableModel(
                new String[][]{},
                new String[]{"Patient ID", "First Name", "Last Name",
                        "Medical Condition", "Next Appointment",
                        "Phone Number", "Billing", "Comment"});

        table = new JTable();

        /** allows for double clicks on JTable item source:
         *  @link http://stackoverflow.com/questions/14852719/double-click-listener-on-jtable-in-java
         */
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {

                    int patientID = Integer.parseInt(table.getValueAt(row, 0).toString());
                    Profile profile = new Profile(patientID);
                    profile.setVisible(true);
                    dispose();
                }
            }
        });

        table.setModel(jTable1Model);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1Model);
        table.setRowSorter(sorter);

        table.setBounds(37, 20, 926, 477);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        mainPanel.add(table);

<<<<<<< HEAD
=======
        /** creates JScrollPane */
>>>>>>> 8f89938e3b67df0e005b64ff9f79cb3e5ca5137f
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(37, 20, 926, 477);
        scrollPane.setViewportView(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        /** populates JTabel
         *  @link source: https://www.youtube.com/watch?v=6cNYUc2PIag
         */
        try {
            String query = "select patientID, firstname, lastname, medicalCondition, nextAppointment, phoneNumber, billing, comment from PatientInfo";
            
            Connection connection = SQLiteConnector.dbConnector();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                model.addRow(new String[]{
                        rs.getString("patientID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("medicalCondition"),
                        rs.getString("nextAppointment"),
                        rs.getString("phoneNumber"),
                        rs.getString("billing"),
                        rs.getString("comment")
                });
            }
            table.sizeColumnsToFit(0);

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("No connection with SQLite possible.");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        /** adds a responsive RowFilter to the JTable */
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (txtSearch.getText() == "") {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
                }

            }

        });
        txtSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                txtSearch.selectAll();
            }
        });


        mainPanel.add(scrollPane);
        contentPane.setVisible(true);
        setContentPane(contentPane);
    }
}
