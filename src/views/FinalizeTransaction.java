package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalizeTransaction extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField amountfld;
	double change;

	/**
	 * Create the dialog.
	 */
	public FinalizeTransaction(double total) {
		setTitle("Finalize Order");
		setBounds(100, 100, 363, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEnterAmount = new JLabel("Enter amount");
			lblEnterAmount.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblEnterAmount.setBounds(29, 36, 128, 26);
			contentPanel.add(lblEnterAmount);
		}
		
		amountfld = new JTextField();
		amountfld.setFont(new Font("Calibri", Font.PLAIN, 16));
		amountfld.setBounds(156, 37, 128, 26);
		contentPanel.add(amountfld);
		amountfld.setColumns(10);
		
		JLabel errMsgLbl = new JLabel("");
		errMsgLbl.setForeground(Color.RED);
		errMsgLbl.setFont(new Font("Calibri", Font.PLAIN, 16));
		errMsgLbl.setBounds(29, 72, 255, 26);
		contentPanel.add(errMsgLbl);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double amount = Double.parseDouble(amountfld.getText());
						if(amount>=total){
							change = amount - total;
							setVisible(false);
							dispose();
						}else{
							errMsgLbl.setText("Amount entered is insufficient.");
						}
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
						change=-1;
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Calibri", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	double showDialog(){
		setModal(true);
		setVisible(true);
		setLocationRelativeTo(null);
		return change;
	}
}
