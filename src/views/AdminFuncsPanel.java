package views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import beans.ProductBean;
import beans.UserBean;
import dao.ProductDAO;
import dao.UserDAO;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminFuncsPanel extends JPanel {
	private List<ProductBean> products;
	private List<UserBean> users;
	private JTable productsTable;
	private JTextField searchField;
	private JTable usersTable;
	private ProductsTableModel prodTblModel;
	private UsersTableModel usersTblModel;

	/**
	 * Create the panel.
	 */
	public AdminFuncsPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTabbedPane AdminProdPane = new JTabbedPane(JTabbedPane.TOP);
		AdminProdPane.setBounds(12, 13, 1001, 520);
		add(AdminProdPane);
		
		JPanel AdminProdsTab = new JPanel();
		AdminProdsTab.setBackground(Color.WHITE);
		AdminProdPane.addTab("Manage Products", null, AdminProdsTab, null);
		AdminProdsTab.setLayout(null);
		
		JButton btnAddProduct = new JButton("Add");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductDialog dialog = new ProductDialog();
				dialog.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosed(WindowEvent event) {
			        	System.out.println("closed dialog");
			        	productsTable.setModel(new ProductsTableModel());
			        }
			    });
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("Add product");
				dialog.lblAddEditProd.setText("ADD PRODUCT");
				dialog.btnAddEditProd.setText("Add");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		btnAddProduct.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnAddProduct.setBounds(828, 72, 78, 39);
		AdminProdsTab.add(btnAddProduct);
		
		JScrollPane prodScrollPane = new JScrollPane();
		prodScrollPane.setBounds(27, 56, 768, 400);
		AdminProdsTab.add(prodScrollPane);
		
		productsTable = new JTable(new ProductsTableModel());
		productsTable.setFont(new Font("Calibri", Font.PLAIN, 17));
		productsTable.setRowHeight(20);
		productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prodScrollPane.setViewportView(productsTable);
//		productsTable.isCellEditable(row, column)
		
		JButton btnEditProduct = new JButton("Edit");
		JButton btnDeleteProduct = new JButton("Delete");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductBean prod = new ProductBean();
				Boolean res = false; 
				int id = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
				System.out.println("id to delete " + id);
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete product?","Delete Product",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					ProductDAO prodDao = new ProductDAO();
					System.out.println("YES");
					res = prodDao.deleteProduct(id);
					if(!res){
						System.out.println("Error in deletion.");
					}
				}
				productsTable.setModel(new ProductsTableModel());
			}
		});
		
		productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		        if (!e.getValueIsAdjusting())
		        {
		            boolean rowsAreSelected = productsTable.getSelectedRowCount() > 0;
		            btnEditProduct.setEnabled(rowsAreSelected);
		            btnDeleteProduct.setEnabled(rowsAreSelected);
		        }
		    }
		});
		
		btnEditProduct.setEnabled(false);
		btnEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO get row value
				ProductBean prod = new ProductBean();
				int id = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
				prod.setId(id);
				ProductDialog dialog = new ProductDialog(prod); 
				dialog.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosed(WindowEvent event) {
			        	System.out.println("closed dialog");
			        	productsTable.setModel(new ProductsTableModel());
			        }
			    });
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("Edit product");
				dialog.lblAddEditProd.setText("EDIT PRODUCT");
				dialog.btnAddEditProd.setText("Edit");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		btnEditProduct.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnEditProduct.setBounds(828, 124, 78, 39);
		AdminProdsTab.add(btnEditProduct);
		
		
		btnDeleteProduct.setEnabled(false);
		btnDeleteProduct.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnDeleteProduct.setBounds(828, 182, 78, 39);
		AdminProdsTab.add(btnDeleteProduct);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Calibri", Font.PLAIN, 16));
		searchField.setBounds(132, 23, 215, 22);
		AdminProdsTab.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search product");
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSearch.setBounds(29, 27, 108, 16);
		AdminProdsTab.add(lblSearch);
		
		
		JPanel AdminUsersTab = new JPanel();
		AdminUsersTab.setBackground(Color.WHITE);
		AdminProdPane.addTab("Manage Users", null, AdminUsersTab, null);
		AdminUsersTab.setLayout(null);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterDialog dialog = new RegisterDialog();
				dialog.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosed(WindowEvent event) {
			        	System.out.println("closed dialog");
			        	usersTable.setModel(new UsersTableModel());
			        }
			    });
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		btnRegister.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnRegister.setBounds(828, 72, 97, 39);
		AdminUsersTab.add(btnRegister);
		
		JScrollPane usersScrollPane = new JScrollPane();
		usersScrollPane.setBounds(27, 56, 768, 370);
		AdminUsersTab.add(usersScrollPane);
		
		usersTable = new JTable(new UsersTableModel());
		usersTable.setFont(new Font("Calibri", Font.PLAIN, 17));
		usersTable.setRowHeight(20);
		usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersScrollPane.setViewportView(usersTable);

	}
	
	public void getProductList(){
		ProductDAO prodDao = new ProductDAO();
		products = new ArrayList<ProductBean>();
		products = prodDao.getAllProducts("all");
	}
	
	public void getUsersList(){
		UserDAO userDao = new UserDAO();
		users = new ArrayList<UserBean>();
		users = userDao.getAllUsers();
	}
	
	class ProductsTableModel extends AbstractTableModel {
		
		String colNames[] = { "Product ID", "Product Name", "Price", "No. of stocks"};
		Class<?> colClasses[] = { int.class, String.class, double.class, int.class};
		
		ProductsTableModel(){
			getProductList();
		}
		
		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public int getRowCount() {
			return products.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
	            return products.get(rowIndex).getId();
	        }
	        if (columnIndex == 1) {
	            return products.get(rowIndex).getName();
	        }
	        if (columnIndex == 2) {
	            return products.get(rowIndex).getPrice();
	        }
	        if (columnIndex == 3) {
	            return products.get(rowIndex).getQuantity();
	        }
	        return null;
		}
		
		public String getColumnName(int columnIndex) {
	        return colNames[columnIndex];
	    }

	    public Class<?> getColumnClass(int columnIndex) {
	        return colClasses[columnIndex];
	    }

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }
	}
	
	class UsersTableModel extends AbstractTableModel {
		
		String colNames[] = { "User ID", "Username", "Fullname", "User type" };
		Class<?> colClasses[] = { int.class, String.class, String.class, String.class };
		
		UsersTableModel(){
			getUsersList();
		}
		
		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public int getRowCount() {
			return users.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
	            return users.get(rowIndex).getId();
	        }
	        if (columnIndex == 1) {
	            return users.get(rowIndex).getUsername();
	        }
	        if (columnIndex == 2) {
	            return users.get(rowIndex).getFullname();
	        }
	        if (columnIndex == 3) {
	            String ret="Cashier";
	            if(users.get(rowIndex).getUsertype()==1){
	            	ret="Administrator";
	            }
	            return ret;
	        }
	        return null;
		}
		
		public String getColumnName(int columnIndex) {
	        return colNames[columnIndex];
	    }

	    public Class<?> getColumnClass(int columnIndex) {
	        return colClasses[columnIndex];
	    }

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }
	}
}
