package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.SQLiteConnector;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Home extends JFrame {

    private JPanel contentPane;
    private Color green = new Color(0, 189, 154);
    private Color grey = new Color(222, 222, 222);
    private Color darkGrey = new Color(112, 112, 112);
    private Color white = new Color(255, 255, 255);
    private JTextField txtSearch;
    private JTable table;
    private JPanel mainPanel;
    Connection connection = null;

    /**
     * Create the frame.
     */
    public Home() {
        connection = SQLiteConnector.dbConnector();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setResizable(false);
        contentPane.setLayout(null);

        // MENU: Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 1000, 50);
        menuPanel.setLayout(null);
        menuPanel.setBackground(green);

        // MENU: title
        JLabel lblTitle = new JLabel("PRMS");
        lblTitle.setForeground(white);
        lblTitle.setBackground(green);
        lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        lblTitle.setBounds(10, 0, 70, 50);
        lblTitle.setHorizontalAlignment(lblTitle.CENTER);
        lblTitle.setOpaque(true);
        menuPanel.add(lblTitle);

        // MENU: search selector
        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(white);
        comboBox.setBackground(green);
        comboBox.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        comboBox.setBounds(225, 10, 134, 35);
        comboBox.setOpaque(true);
        menuPanel.add(comboBox);

        // MENU: search field
        txtSearch = new JTextField();
        txtSearch.setText("Search...");
        txtSearch.setBounds(370, 12, 176, 28);
        txtSearch.setColumns(10);
        menuPanel.add(txtSearch);


        // MENU: button - new patient
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
        btnNewPatient.setBackground(white);
        btnNewPatient.setIcon(new ImageIcon("images/new.png"));
        btnNewPatient.setBounds(640, 0, 50, 50);
        btnNewPatient.setBorderPainted(false);
        menuPanel.add(btnNewPatient);


        // MENU: button - select all
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
        });
        btnSelect.setBackground(white);
        btnSelect.setIcon(new ImageIcon("images/select.png"));
        btnSelect.setBounds(700, 0, 50, 50);
        btnSelect.setBorderPainted(false);
        menuPanel.add(btnSelect);


        // MENU: button - import
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
        });
        btnImport.setBackground(white);
        btnImport.setIcon(new ImageIcon("images/import.png"));
        btnImport.setBounds(760, 0, 50, 50);
        btnImport.setBorderPainted(false);
        menuPanel.add(btnImport);

        // MENU: button - export
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
        });
        btnExport.setBackground(white);
        btnExport.setIcon(new ImageIcon("images/export.png"));
        btnExport.setBounds(820, 0, 50, 50);
        btnExport.setBorderPainted(false);
        menuPanel.add(btnExport);

        // MENU: button - delete
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


                                           if (table.getRowCount() > 0) {
                                               if (table.getSelectedRowCount() > 0) {
                                                   int selectedRow[] = table.getSelectedRows();
                                                   for (int i : selectedRow) {

                                                       connection = SQLiteConnector.dbConnector();

                                                       try {
                                                           String query = "delete from PatientInfo where patientID=" + table.getModel().getValueAt(i, 0);
                                                           System.out.println(query);
                                                           PreparedStatement pst = connection.prepareStatement(query);
                                                           pst.executeUpdate();


                                                           pst.close();
                                                       } catch (SQLException e1) {
                                                           System.err.println("No connection with SQLite possible.");
                                                           e1.printStackTrace();
                                                       } finally {
                                                           if (connection != null) {
                                                               try {
                                                                   connection.close();
                                                               } catch (SQLException e1) {
                                                                   // TODO Auto-generated catch block
                                                                   e1.printStackTrace();
                                                               }
                                                           }
                                                       }

                                                       ((DefaultTableModel) table.getModel()).removeRow(i);

                                                   }

                                               }
                                           }
                                       }
                                   });

                btnDelete.setBackground(white);
                btnDelete.setIcon(new ImageIcon("images/delete.png"));
                btnDelete.setBounds(880, 0, 50, 50);
                btnDelete.setBorderPainted(false);
                menuPanel.add(btnDelete);

                // MENU: button - logout
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
                btnLogout.setBackground(white);
                btnLogout.setIcon(new ImageIcon("images/logout.png"));
                btnLogout.setBounds(940, 0, 50, 50);
                btnLogout.setBorderPainted(false);
                menuPanel.add(btnLogout);

                contentPane.add(menuPanel);


                // ============================================ Main Panel ============================================
                mainPanel = new JPanel();
                mainPanel.setBounds(0, 50, 1000, 528);
                mainPanel.setBackground(grey);
                contentPane.add(mainPanel);
                mainPanel.setLayout(null);

                // ============================================ JTABLE =================================================


                // creates the table model that stores the JTable columns & data
                TableModel jTable1Model = new DefaultTableModel(
                        new String[][]{},
                        new String[]{"Patient ID", "First Name", "Last Name",
                                "Medical Condition", "Next Appointment",
                                "Phone Number", "Billing", "Comment"});

                table = new JTable();
                table.setModel(jTable1Model);

                TableRowSorter<TableModel>sorter = new TableRowSorter<TableModel>(jTable1Model);
                table.setRowSorter(sorter);

                table.setBounds(37, 20, 926, 477);
                table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                table.setAutoCreateRowSorter(true);
                mainPanel.add(table);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(37, 20, 926, 477);
                scrollPane.setViewportView(table);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


                // populates JTabel source: https://www.youtube.com/watch?v=6cNYUc2PIag
                try {
                    String query = "select patientID, firstname, lastname, medicalCondition, nextAppointment, phoneNumber, billing, comment from PatientInfo";
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
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }


                // source:
                txtSearch.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {


                        String text = txtSearch.getText();
                        if(text.length() == 0){
                            sorter.setRowFilter(null);
                        }
                        else{
                            sorter.setRowFilter(RowFilter.regexFilter(text));
                        }

//                        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//                            public boolean include(Entry entry) {
//                                Integer population = (Integer) entry.getValue(1);
//                                return population.intValue() > 3;
//
//
//
//                        String query = txtSearch.getText().toLowerCase();
//                        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
//                        table.setRowSorter(sorter);
//                        sorter.setRowFilter(RowFilter.regexFilter(query));
                    }
                });


                mainPanel.add(scrollPane);


                contentPane.setVisible(true);
                setContentPane(contentPane);
            }
        }
