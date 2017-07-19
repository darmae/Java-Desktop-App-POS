package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.ProductBean;
import dao.ProductDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class ProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField prodNametxtfld;
	private JTextField prodPriceTxtfld;
	private JLabel lblProdName;
	JLabel lblAddEditProd;
	JButton btnAddEditProd;
	ProductBean product = new ProductBean();
	private JLabel lblQuantity;
	JSpinner qtySpinner;

	/**
	 * Create the dialog.
	 */
	public ProductDialog(){
		initProdDialog();
	}
	
	public void initProdDialog(){
		setResizable(false);
		setBounds(100, 100, 353, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		qtySpinner = new JSpinner();
		qtySpinner.setFont(new Font("Calibri", Font.PLAIN, 17));
		qtySpinner.setBounds(45, 254, 90, 32);
		contentPanel.add(qtySpinner);
		
		lblProdName = new JLabel("Product Name");
		lblProdName.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblProdName.setBounds(45, 108, 160, 24);
		contentPanel.add(lblProdName);
		
		prodNametxtfld = new JTextField();
		prodNametxtfld.setFont(new Font("Calibri", Font.PLAIN, 17));
		prodNametxtfld.setColumns(10);
		prodNametxtfld.setBounds(45, 132, 253, 32);
		contentPanel.add(prodNametxtfld);
		
		JLabel lblProdprice = new JLabel("Product Price");
		lblProdprice.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblProdprice.setBounds(45, 168, 132, 24);
		contentPanel.add(lblProdprice);
		
		prodPriceTxtfld = new JTextField();
		prodPriceTxtfld.setFont(new Font("Calibri", Font.PLAIN, 17));
		prodPriceTxtfld.setColumns(10);
		prodPriceTxtfld.setBounds(45, 190, 253, 32);
		contentPanel.add(prodPriceTxtfld);
		
		JLabel lblErrorMsg = new JLabel("");
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblErrorMsg.setBounds(45, 299, 253, 24);
		contentPanel.add(lblErrorMsg);
		
		btnAddEditProd = new JButton("Add Product");
		btnAddEditProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				int s = Integer.parseInt(qtySpinner.getValue().toString());
				
				if(!prodPriceTxtfld.getText().equals("") && !prodNametxtfld.getText().equals("") && !qtySpinner.getValue().toString().equals("")){
					System.out.println("spinner " + qtySpinner.getValue().toString());
					product.setName(prodNametxtfld.getText()); 
					product.setPrice(Double.parseDouble(prodPriceTxtfld.getText()));
					product.setQuantity(Integer.parseInt(qtySpinner.getValue().toString()));
					ProductDAO prodDao = new ProductDAO();
					System.out.println("lbl: " + lblAddEditProd.getText());
					if(lblAddEditProd.getText().equals("ADD PRODUCT")){
						prodDao.addProduct(product);
					}
					else{
						prodDao.editProduct(product);
					}
					if(product.isValid){
						lblErrorMsg.setText("Successfully added new product.");
						lblErrorMsg.setForeground(Color.GREEN);
						dispose();
					}else{
						lblErrorMsg.setText("Error in adding new product.");
						lblErrorMsg.setForeground(Color.RED);
					}
				}
				else{
					lblErrorMsg.setText("Enter required fields.");
					lblErrorMsg.setForeground(Color.RED);
				}
				
			}
		});
		btnAddEditProd.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAddEditProd.setBackground(Color.LIGHT_GRAY);
		btnAddEditProd.setBounds(45, 325, 121, 42);
		contentPanel.add(btnAddEditProd);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("Calibri", Font.BOLD, 16));
		cancelBtn.setBackground(Color.LIGHT_GRAY);
		cancelBtn.setActionCommand("Cancel");
		cancelBtn.setBounds(177, 325, 121, 42);
		contentPanel.add(cancelBtn);
		
		lblAddEditProd = new JLabel("ADD PRODUCT");
		lblAddEditProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEditProd.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		lblAddEditProd.setBounds(45, 37, 253, 42);
		contentPanel.add(lblAddEditProd);
		
		lblQuantity = new JLabel("No. of stock");
		lblQuantity.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblQuantity.setBounds(45, 231, 132, 24);
		contentPanel.add(lblQuantity);
		
	}
	
	public ProductDialog(ProductBean prod){
		initProdDialog();
		ProductDAO prodDao = new ProductDAO();
		product = prodDao.getProduct(prod);
		prodPriceTxtfld.setText(""+product.getPrice());
		prodNametxtfld.setText(product.getName());
		qtySpinner.setValue(prod.getQuantity());
	}
}
