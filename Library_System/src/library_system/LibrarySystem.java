package library_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;

public class LibrarySystem {

	private JFrame frmVuLibraryManagement;
	private JTable dataTable;
	private JTextField idTxt;
	private JTextField titleTxt;
	private JTextField authorTxt;
	private JTextField yearTxt;
	
	private static final String DATABASE_PATH = "C://Users/mukis/OneDrive/Documents/NetBeansProjects/Library_System/src/library_system/VU_LIBRARY.accdb";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarySystem window = new LibrarySystem();
					window.frmVuLibraryManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibrarySystem() {
		initialize();
		try {
            // Populate initial data in the table
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVuLibraryManagement = new JFrame();
		frmVuLibraryManagement.setTitle("VU LIBRARY MANAGEMENT SYSTEM");
		frmVuLibraryManagement.setResizable(false);
		frmVuLibraryManagement.setBounds(100, 100, 1111, 674);
		frmVuLibraryManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVuLibraryManagement.getContentPane().setLayout(null);
		frmVuLibraryManagement.setLocationRelativeTo(frmVuLibraryManagement);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(167, 204, 922, 431);
		frmVuLibraryManagement.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		dataTable = new JTable();
		dataTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "TITLE", "AUTHOR", "YEAR"
			}
		));
		JScrollPane scrollPane = new JScrollPane(dataTable);
//		panel_2.add(dataTable);
		panel_2.add(scrollPane);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(10, 207, 147, 428);
		frmVuLibraryManagement.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton refreshBtn = new JButton("REFRESH TABLE");
		refreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		refreshBtn.setForeground(new Color(0, 0, 64));
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
	                    populateTable();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
			}
		});
		panel_2_1.add(refreshBtn);
		
		JButton deleteBtn = new JButton("DELETE BOOK");
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deleteBtn.setForeground(new Color(0, 0, 64));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    deleteSelectedRow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
			}
		});
		panel_2_1.add(deleteBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 1079, 159);
		frmVuLibraryManagement.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("VU LIBRARY SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_1.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("BOOK TITLE");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_3 = new JLabel("BOOK AUTHOR");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("YEAR");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_4.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		idTxt = new JTextField();
		idTxt.setForeground(new Color(0, 0, 64));
		idTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	               titleTxt.requestFocus();
			}
			}
		});
		idTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		idTxt.setColumns(10);
		panel.add(idTxt);
		
		titleTxt = new JTextField();
		titleTxt.setForeground(new Color(0, 0, 64));
		titleTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		               authorTxt.requestFocus();
				}
			}
		});
		titleTxt.setColumns(10);
		panel.add(titleTxt);
		
		authorTxt = new JTextField();
		authorTxt.setForeground(new Color(0, 0, 64));
		authorTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		               yearTxt.requestFocus();
				}
			}
		});
		authorTxt.setColumns(10);
		panel.add(authorTxt);
		
		yearTxt = new JTextField();
		yearTxt.setForeground(new Color(0, 0, 64));
		yearTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yearTxt.setColumns(10);
		panel.add(yearTxt);
		
		JButton addBtn = new JButton("ADD NEW BOOK");
		addBtn.setForeground(new Color(0, 0, 64));
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String id = idTxt.getText();
		            int book_id = Integer.parseInt(id); 
		            String title = titleTxt.getText();
		            String author = authorTxt.getText();
		            String year = yearTxt.getText();
		            
		            

		            AccessDatabaseInsert.insertData(DATABASE_PATH, book_id, title, author, year);
		            
		            idTxt.setText("");
		            titleTxt.setText("");
		            authorTxt.setText("");
		            yearTxt.setText("");
		            
		            populateTable();
		            JOptionPane.showMessageDialog(frmVuLibraryManagement, "Book added successfully!");
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		panel.add(addBtn);
	}


	/**
     * Populate the table with data from the database.
     *
     * @throws SQLException if a database access error occurs
     */
    private void populateTable() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            String url = "jdbc:ucanaccess://" + DATABASE_PATH;
            connection = DriverManager.getConnection(url);

            // SQL query
            String query = "SELECT ID, TITLE, AUTHOR, YEAR_ FROM books";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Clear existing table data
            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
            model.setRowCount(0);

            // Populate the table
            while (resultSet.next()) {
                Object[] rowData = {
                    resultSet.getInt("ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("AUTHOR"),
                    resultSet.getString("YEAR_")
                };
                model.addRow(rowData);
            }
        } finally {
            // Clean up
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    /**
     * Delete the selected row from the table and the database.
     *
     * @throws SQLException if a database access error occurs
     */
    private void deleteSelectedRow() throws SQLException {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            // No row is selected, do nothing
            return;
        }
        
        // Confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(frmVuLibraryManagement, "Are you sure you want to delete the selected book?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Get the ID of the selected row
        int id = (int) dataTable.getValueAt(selectedRow, 0);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Connect to the database
            String url = "jdbc:ucanaccess://" + DATABASE_PATH;
            connection = DriverManager.getConnection(url);

            // SQL query to delete the record
            String query = "DELETE FROM books WHERE ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            // Execute the delete
            preparedStatement.executeUpdate();

            // Remove the row from the table model
            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
            model.removeRow(selectedRow);
        } finally {
            // Clean up
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
