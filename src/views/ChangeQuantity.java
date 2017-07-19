package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.ProductBean;
import dao.ProductDAO;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeQuantity extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ProductDAO prodDao = new ProductDAO();
	ProductBean prod = new ProductBean();
	JSpinner qtySpinner;
	int retQty;

	/**
	 * Create the dialog.
	 */
	public ChangeQuantity(int prodId, int origQty) {
		setTitle("Change Quantity");
		setBounds(100, 100, 304, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		int maxQty = 0;
		prod.setId(prodId);
		retQty = origQty;
		maxQty = prodDao.getProduct(prod).getQuantity();
		SpinnerModel sm = new SpinnerNumberModel(origQty, 0, maxQty, 1);
		qtySpinner = new JSpinner(sm);
		qtySpinner.setFont(new Font("Calibri", Font.PLAIN, 14));
		qtySpinner.setBounds(186, 29, 74, 31);
		contentPanel.add(qtySpinner);
		
		JLabel prodQtyLbl = new JLabel("Enter product quantity");
		prodQtyLbl.setFont(new Font("Calibri", Font.PLAIN, 15));
		prodQtyLbl.setBounds(25, 29, 149, 31);
		contentPanel.add(prodQtyLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						retQty = Integer.parseInt(qtySpinner.getValue().toString());
						setVisible(false);
						dispose();
					}
				});
				okButton.setFont(new Font("Calibri", Font.PLAIN, 15));
//				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Calibri", Font.PLAIN, 15));
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	int showDialog(){
		setModal(true);
		setVisible(true);
		setLocationRelativeTo(null);
		return retQty;
	}
}