package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.UserBean;
import dao.UserDAO;
import enums.UserTypes;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField usernameRegisterField;
	private JTextField fullnameRegisterField;
	private JTextField usernameLoginField;
	private JPasswordField passwordReg;
	private JPasswordField passwordLogin;
	private JLabel lblWelcome;
	private UserBean currUser;
	private AdminFuncsPanel adfuncsPanel;
	private CashierFuncsPanel cashFuncsPanel;
	private JPanel userPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {			
		
		setResizable(false);
		setTitle("POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 702);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setBackground(Color.WHITE);
		primaryPanel.setBounds(0, 0, 1074, 667);
		contentPane.add(primaryPanel);
		primaryPanel.setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		primaryPanel.add(loginPanel, "name_110832286356866");
		loginPanel.setLayout(null);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
		labelUsername.setBounds(400, 245, 87, 24);
		loginPanel.add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		labelPassword.setBounds(400, 302, 87, 24);
		loginPanel.add(labelPassword);
		
		usernameLoginField = new JTextField();
		usernameLoginField.setFont(new Font("Calibri", Font.PLAIN, 17));
		usernameLoginField.setColumns(10);
		usernameLoginField.setBounds(400, 270, 253, 32);
		loginPanel.add(usernameLoginField);
		
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		labelLogin.setBounds(474, 165, 115, 42);
		loginPanel.add(labelLogin);
		
		JLabel lblErrorLogin = new JLabel("");
		lblErrorLogin.setForeground(Color.RED);
		lblErrorLogin.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblErrorLogin.setBounds(400, 371, 253, 24);
		loginPanel.add(lblErrorLogin);
		
		JPanel registrationPanel = new JPanel();
		JPanel homePanel = new JPanel();
		JComboBox<UserTypes> usertypecomboBox = new JComboBox<UserTypes>();
		JLabel lblErrorReg = new JLabel("");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBean user = new UserBean();
				UserDAO userDao = new UserDAO();
				String username = usernameLoginField.getText();
				char[] input = passwordLogin.getPassword();
				String password = String.valueOf(input);
				System.out.println(username + " " + password);
				if(!username.equals("") && !password.equals("")){
					user.setUsername(username);
					user.setPassword(password);
					user = userDao.getUserDetails(user);
					if(user.isValid){
						currUser = user;
						primaryPanel.removeAll();
						primaryPanel.repaint();
						primaryPanel.revalidate();
						primaryPanel.add(homePanel);
						primaryPanel.repaint();
						primaryPanel.revalidate();
						setWelcomeLabel(); 
						setUserPanel();
					}
					else{
						lblErrorLogin.setText("Invalid username/password.");
					}
				}
				else{
					lblErrorLogin.setText("Enter required fields.");
				}
			}
		});
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 17));
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(400, 401, 253, 42);
		loginPanel.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				primaryPanel.removeAll();
				primaryPanel.repaint();
				primaryPanel.revalidate();
				primaryPanel.add(registrationPanel);
				primaryPanel.repaint();
				primaryPanel.revalidate();
				usernameRegisterField.setText("");
				passwordReg.setText("");
				fullnameRegisterField.setText("");
				usertypecomboBox.setSelectedIndex(-1);
				lblErrorReg.setText("");
			}
		});
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 17));
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setBounds(532, 401, 121, 42);
//		loginPanel.add(btnRegister);
		
		passwordLogin = new JPasswordField();
		passwordLogin.setBounds(400, 326, 253, 32);
		loginPanel.add(passwordLogin);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1074, 42);
		loginPanel.add(panel);
		
		JLabel label = new JLabel("POINT OF SALE APPLICATION");
		label.setFont(new Font("Century Gothic", Font.BOLD, 17));
		label.setBounds(22, 13, 253, 16);
		panel.add(label);
		
		
		registrationPanel.setBackground(Color.WHITE);
		primaryPanel.add(registrationPanel, "name_110872926457119");
		registrationPanel.setLayout(null);
		
		usernameRegisterField = new JTextField();
		usernameRegisterField.setFont(new Font("Calibri", Font.PLAIN, 17));
		usernameRegisterField.setBounds(403, 213, 253, 32);
		registrationPanel.add(usernameRegisterField);
		usernameRegisterField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblUsername.setBounds(403, 189, 87, 24);
		registrationPanel.add(lblUsername);
		
		JLabel lblRegister = new JLabel("USER REGISTRATION");
		lblRegister.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		lblRegister.setBounds(390, 116, 290, 42);
		registrationPanel.add(lblRegister);
		
		fullnameRegisterField = new JTextField();
		fullnameRegisterField.setFont(new Font("Calibri", Font.PLAIN, 17));
		fullnameRegisterField.setColumns(10);
		fullnameRegisterField.setBounds(403, 271, 253, 32);
		registrationPanel.add(fullnameRegisterField);
		
		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblFullname.setBounds(403, 249, 87, 24);
		registrationPanel.add(lblFullname);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(403, 307, 87, 24);
		registrationPanel.add(lblPassword);
		
		JButton btnRegisterReg = new JButton("Register");
		btnRegisterReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBean user = new UserBean();
				UserDAO userDao = new UserDAO();
				String username = usernameRegisterField.getText();
				char[] input = passwordReg.getPassword();
				String password = String.valueOf(input);
				String fullname = fullnameRegisterField.getText();
				int userType = usertypecomboBox.getSelectedIndex();
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
					}
					else{
						lblErrorReg.setForeground(Color.RED);
						lblErrorReg.setText("Error in adding user.");
					}
				}
				else{
					lblErrorReg.setForeground(Color.RED);
					lblErrorReg.setText("Enter required fields.");
				}
			}
		});
		btnRegisterReg.setBackground(Color.LIGHT_GRAY);
		btnRegisterReg.setFont(new Font("Calibri", Font.BOLD, 17));
		btnRegisterReg.setBounds(403, 467, 121, 42);
		registrationPanel.add(btnRegisterReg);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblUserType.setBounds(403, 368, 87, 24);
		registrationPanel.add(lblUserType);
		
		usertypecomboBox.setFont(new Font("Calibri", Font.PLAIN, 17));
		usertypecomboBox.setBackground(Color.WHITE);
		usertypecomboBox.setBounds(403, 392, 253, 32);
		usertypecomboBox.addItem(null);
		usertypecomboBox.addItem(UserTypes.STRING_ADMIN);
		usertypecomboBox.addItem(UserTypes.STRING_USER);
		
		
		registrationPanel.add(usertypecomboBox);
		
		
		lblErrorReg.setForeground(Color.RED);
		lblErrorReg.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblErrorReg.setBounds(403, 437, 253, 24);
		registrationPanel.add(lblErrorReg);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				primaryPanel.removeAll();
				primaryPanel.repaint();
				primaryPanel.revalidate();
				primaryPanel.add(loginPanel);
				primaryPanel.repaint();
				primaryPanel.revalidate();
				usernameLoginField.setText("");
				passwordLogin.setText("");
				lblErrorLogin.setText("");
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 17));
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(535, 467, 121, 42);
		registrationPanel.add(btnCancel);
		
		passwordReg = new JPasswordField();
		passwordReg.setBounds(403, 333, 253, 32);
		registrationPanel.add(passwordReg);
		
		
		homePanel.setBackground(Color.WHITE);
		primaryPanel.add(homePanel, "name_110947853700824");
		homePanel.setLayout(null);
		
		userPanel = new JPanel();
		userPanel.setBackground(Color.WHITE);
		userPanel.setBounds(12, 55, 1050, 575);
		homePanel.add(userPanel);
		userPanel.setLayout(new CardLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(Color.LIGHT_GRAY);
		headerPanel.setBounds(0, 0, 1074, 42);
		homePanel.add(headerPanel);
		
		JLabel lblPOSheader = new JLabel("POINT OF SALE APPLICATION");
		lblPOSheader.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblPOSheader.setBounds(22, 13, 253, 16);
		headerPanel.add(lblPOSheader);
		
		lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWelcome.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lblWelcome.setBounds(565, 13, 394, 16);
		headerPanel.add(lblWelcome);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				primaryPanel.removeAll();
				primaryPanel.repaint();
				primaryPanel.revalidate();
				primaryPanel.add(loginPanel);
				primaryPanel.repaint();
				primaryPanel.revalidate();
				usernameLoginField.setText("");
				passwordLogin.setText("");
				lblErrorLogin.setText("");
			}
		});
		logoutBtn.setFont(new Font("Calibri", Font.PLAIN, 17));
		logoutBtn.setBackground(Color.LIGHT_GRAY);
		logoutBtn.setBounds(971, 7, 91, 27);
		headerPanel.add(logoutBtn);
	}
	
	public void setWelcomeLabel(){
		lblWelcome.setText("Welcome, " + currUser.getUsername());
	}
	
	public void setUserPanel(){
		System.out.println("set user panel");
		userPanel.removeAll();
		userPanel.repaint();
		userPanel.revalidate();
		System.out.println("type: " + currUser.getUsertype());
		if(currUser.getUsertype()==1){ 
			adfuncsPanel = new AdminFuncsPanel();
			userPanel.add(adfuncsPanel);
		}
		else{
			cashFuncsPanel = new CashierFuncsPanel();
			userPanel.add(cashFuncsPanel); 
		}
		userPanel.repaint();
		userPanel.revalidate();
	}
}
