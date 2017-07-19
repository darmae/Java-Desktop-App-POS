package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import beans.ProductBean;
import dao.ProductDAO;
import views.AdminFuncsPanel.ProductsTableModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierFuncsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private List<ProductBean> products;
	private JTable productsTable;
	private JTable transProdTbl;
	private List<TransactionProduct> toBuyList = new ArrayList<TransactionProduct>();
	JLabel grandtotalLbl;

	/**
	 * Create the panel.
	 */
	public CashierFuncsPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product List");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel.setBounds(42, 37, 189, 16);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 66, 436, 424);
		add(scrollPane);
		
		productsTable = new JTable(new ProductsTableModel());
		scrollPane.setViewportView(productsTable);
		productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsTable.setRowHeight(20);
		productsTable.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		JScrollPane tbList_Pane = new JScrollPane();
		tbList_Pane.setBounds(511, 67, 436, 338);
		add(tbList_Pane);
		
		transProdTbl = new JTable();
		tbList_Pane.setViewportView(transProdTbl);
		
		JLabel msgLabel = new JLabel("");
		msgLabel.setForeground(Color.RED);
		msgLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		msgLabel.setBounds(711, 418, 236, 25);
		add(msgLabel);
		
		JLabel lblGrandTotal = new JLabel("TOTAL");
		lblGrandTotal.setBackground(Color.WHITE);
		lblGrandTotal.setFont(new Font("Calibri", Font.BOLD, 20));
		lblGrandTotal.setBounds(694, 456, 124, 34);
		add(lblGrandTotal);
		
		JButton btnFinalizeOrder = new JButton("PLACE ORDER");
		btnFinalizeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(toBuyList.size()==0){
					msgLabel.setText("No products in order list.");
				}else{
					msgLabel.setText("");
					System.out.println("Order accepted.");
					double total = Double.parseDouble(grandtotalLbl.getText());
					FinalizeTransaction dialog = new FinalizeTransaction(total);
					double change; 
					change= dialog.showDialog();
					System.out.println("Change is: " + change);
					if(change!=-1){
						JOptionPane.showMessageDialog(null, "Order completed. Change due: "+change, "Notification", JOptionPane.PLAIN_MESSAGE);
						for (TransactionProduct t : toBuyList) {
							int id = t.tProd.getId();
							ProductDAO pDao = new ProductDAO();
							pDao.decrementQty(id);
						}
						toBuyList.clear();
						createTransProdModel();
						productsTable.setModel(new ProductsTableModel());
					}
				}
			}
		});
		btnFinalizeOrder.setBackground(new Color(240, 255, 240));
		btnFinalizeOrder.setFont(new Font("Calibri", Font.BOLD, 15));
		btnFinalizeOrder.setBounds(511, 456, 131, 34);
		add(btnFinalizeOrder);
		
		grandtotalLbl = new JLabel("0.00");
		grandtotalLbl.setBackground(Color.LIGHT_GRAY);
		grandtotalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		grandtotalLbl.setFont(new Font("Calibri", Font.BOLD, 20));
		grandtotalLbl.setBounds(817, 456, 124, 33);
		add(grandtotalLbl);
		
		JLabel lblOrderList = new JLabel("Order List");
		lblOrderList.setFont(new Font("Calibri", Font.BOLD, 17));
		lblOrderList.setBounds(511, 38, 189, 16);
		add(lblOrderList);
		
		JButton btnQty = new JButton("Change quantity");
		btnQty.setEnabled(false);
		btnQty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ndx = transProdTbl.getSelectedRow();
				int q = (int)transProdTbl.getValueAt(transProdTbl.getSelectedRow(), 2);
				int id = toBuyList.get(ndx).tProd.getId();
				ChangeQuantity dialog = new ChangeQuantity(id, q);
				int retQty; 
				retQty= dialog.showDialog();
				System.out.println("newqty: " + retQty);
				toBuyList.get(ndx).quantity = retQty;
				toBuyList.get(ndx).subtotal = retQty * toBuyList.get(ndx).tProd.getPrice();
				createTransProdModel();
			}
		});
		btnQty.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnQty.setBackground(new Color(240, 255, 240));
		btnQty.setBounds(511, 418, 131, 34);
		add(btnQty);
		
		
		
		productsTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		    	int id = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
		        if (me.getClickCount() == 2) {
		            System.out.println("Double clicked " + id);
		            ProductBean newProd = new ProductBean();
		            ProductDAO prodDao = new ProductDAO();
		            newProd.setId(id);
		            newProd = prodDao.getProduct(newProd);
		            int x;
		            for(x=0; x<toBuyList.size() && toBuyList.get(x).tProd.getId()!=newProd.getId(); x++) { }
		            if(x==toBuyList.size()){
		            	toBuyList.add(new TransactionProduct(newProd, 1));
		            	
		            } else{
		            	toBuyList.get(x).subtotal = (++toBuyList.get(x).quantity) * toBuyList.get(x).tProd.getPrice();
		            }
		            createTransProdModel();
		            System.out.println("size: " + toBuyList.size());
		        }
		    }
		});
		
		transProdTbl.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
//		    	int id = (int)transProdTbl.getValueAt(transProdTbl.getSelectedRow(), 0);
		        if (me.getClickCount() == 2) {
//		            System.out.println("transprodtbl clicked " + id);
		            toBuyList.remove(transProdTbl.getSelectedRow());
		            createTransProdModel();
		            System.out.println("size: " + toBuyList.size());
		        }
		    }
		});
		
		transProdTbl.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		        if (!e.getValueIsAdjusting())
		        {
		            boolean rowsAreSelected = transProdTbl.getSelectedRowCount() > 0;
		            btnQty.setEnabled(rowsAreSelected);
		        }
		    }
		});

	}
	
	public void getProductList(){
		ProductDAO prodDao = new ProductDAO();
		products = new ArrayList<ProductBean>();
		products = prodDao.getAllProducts("n");
	}
	
	public void createTransProdModel(){
		transProdTbl.setModel(new TransTableModel(toBuyList));
		double grandTotal = 0;
		for (TransactionProduct t : toBuyList) {
			grandTotal = grandTotal + t.subtotal;			
		}
		grandtotalLbl.setText(""+grandTotal);
	}
	
	class TransTableModel extends AbstractTableModel {
		
		String colNames[] = { "Product", "Price", "Quantity", "Subtotal"};
		Class<?> colClasses[] = { String.class, double.class, int.class, double.class};
		
		TransTableModel(List<TransactionProduct> toBuyList){
			
		}
		
		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public int getRowCount() {
			return toBuyList.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
	            return toBuyList.get(rowIndex).tProd.getName();
	        }
	        if (columnIndex == 1) {
	            return toBuyList.get(rowIndex).tProd.getPrice();
	        }
	        if (columnIndex == 2) {
	            return toBuyList.get(rowIndex).quantity;
	        }
	        if (columnIndex == 3) {
	            return toBuyList.get(rowIndex).subtotal;
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
	
	class TransactionProduct {
		ProductBean tProd;
		int quantity;
		double subtotal;
		
		TransactionProduct(ProductBean t, int q){
			this.tProd = t;
			this.quantity = q;
			this.subtotal = q * t.getPrice();
		}
	}
	
	class ProductsTableModel extends AbstractTableModel {
		
		String colNames[] = { "Product ID", "Product Name", "Price"};
		Class<?> colClasses[] = { int.class, String.class, double.class};
		
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
