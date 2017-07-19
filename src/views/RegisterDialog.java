package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.UserBean;
import dao.UserDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import enums.UserTypes;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegisterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameRegTxtFld;
	private JTextField fullnameRegTxtFld;
	private JPasswordField passRegTxtFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterDialog dialog = new RegisterDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisterDialog() {
		setResizable(false);
		setTitle("Register New User");
		setBounds(100, 100, 397, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbl_userReg = new JLabel("USER REGISTRATION");
		lbl_userReg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_userReg.setFont(new Font("Century Gothic", Font.PLAIN, 27));
		lbl_userReg.setBounds(51, 26, 285, 42);
		contentPanel.add(lbl_userReg);
	
		JLabel lbl_username = new JLabel("Username");
		lbl_username.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_username.setBounds(67, 81, 87, 24);
		contentPanel.add(lbl_username);
	
		usernameRegTxtFld = new JTextField();
		usernameRegTxtFld.setFont(new Font("Calibri", Font.PLAIN, 17));
		usernameRegTxtFld.setColumns(10);
		usernameRegTxtFld.setBounds(67, 105, 253, 32);
		contentPanel.add(usernameRegTxtFld);
		
		JLabel lbl_fullname = new JLabel("Fullname");
		lbl_fullname.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_fullname.setBounds(67, 141, 87, 24);
		contentPanel.add(lbl_fullname);
	
		fullnameRegTxtFld = new JTextField();
		fullnameRegTxtFld.setFont(new Font("Calibri", Font.PLAIN, 17));
		fullnameRegTxtFld.setColumns(10);
		fullnameRegTxtFld.setBounds(67, 163, 253, 32);
		contentPanel.add(fullnameRegTxtFld);
	
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_password.setBounds(67, 199, 87, 24);
		contentPanel.add(lbl_password);
	
		passRegTxtFld = new JPasswordField();
		passRegTxtFld.setBounds(67, 225, 253, 32);
		contentPanel.add(passRegTxtFld);
	
		JLabel lbl_userType = new JLabel("User Type");
		lbl_userType.setFont(new Font("Calibri", Font.PLAIN, 16));
		lbl_userType.setBounds(67, 260, 87, 24);
		contentPanel.add(lbl_userType);
	
		JComboBox<UserTypes> userTypeCmbBox = new JComboBox<UserTypes>();
		userTypeCmbBox.setFont(new Font("Calibri", Font.PLAIN, 17));
		userTypeCmbBox.setBackground(Color.WHITE);
		userTypeCmbBox.setBounds(67, 284, 253, 32);
		userTypeCmbBox.addItem(null);
		userTypeCmbBox.addItem(UserTypes.STRING_ADMIN);
		userTypeCmbBox.addItem(UserTypes.STRING_USER);
		contentPanel.add(userTypeCmbBox);
		
		JLabel lblErrorReg = new JLabel("");
		lblErrorReg.setForeground(Color.RED);
		lblErrorReg.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblErrorReg.setBounds(67, 329, 253, 24);
		contentPanel.add(lblErrorReg);
		
		JButton btnRegister = new JButton("Register");
		
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBean user = new UserBean();
				UserDAO userDao = new UserDAO();
				String username = usernameRegTxtFld.getText();
				char[] input = passRegTxtFld.getPassword();
				String password = String.valueOf(input);
				String fullname = fullnameRegTxtFld.getText();
				int userType = userTypeCmbBox.getSelectedIndex();
				System.out.println(username + " " + password + " " + fullname + " " + userType);
				if(!username.equals("") && !password.equals("") && !fullname.equals("") && userType!=-1){
					user.setUsername(username);
					user.setPassword(password);
					user.setFullname(fullname);
					user.setUsertype(userType);
					user.isValid = false;
					user = userDao.addUserAccount(user);
					if(user.isValid){
						lblErrorReg.setForeground(Color.GREEN);
						lblErrorReg.setText("User successfully added.");
						dispose();
//						TODO CLOSE DIALOG
					}
					else{
						lblErrorReg.setForeground(Color.RED);
						lblErrorReg.setText("Username already exists.");
					}
				}
				else{
					lblErrorReg.setForeground(Color.RED);
					lblErrorReg.setText("Enter required fields.");
				}
			}
		});
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 17));
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setBounds(67, 359, 121, 42);
		contentPanel.add(btnRegister);
	
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 17));
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(199, 359, 121, 42);
		contentPanel.add(btnCancel);
		
	}
	
}
