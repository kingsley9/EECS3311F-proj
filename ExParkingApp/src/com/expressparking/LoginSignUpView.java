package com.expressparking;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


//INITIAL/MAIN VIEW OF THE EXPRESS PARKING APP
/**
 * @wbp.parser.entryPoint
 */
public class LoginSignUpView {
	
	/*
	 * Components of the frame and map to store login info and list of parking spots
	 */
	
	protected JFrame frame;
	protected JTextField textFieldUsnU;
	protected JPasswordField passwordFieldU;
	protected JTextField textFieldUsnA;
	protected JPasswordField passwordFieldA;
	protected JTextField textFieldFNS;
	protected JTextField textFieldLNS;
	protected JTextField textFieldUsnS;
	protected JTextField textFieldEmail;
	protected JPasswordField textFieldPass;
	protected JTextField textFieldUsnO;
	protected JPasswordField passwordFieldO;
	protected CustomerView cv;
	protected AdminView av;
	protected OfficerView ov;
	
	/*
	 * Constructor
	 */
	public LoginSignUpView (AdminModel SysAdmin){
		/*
		 * Frame initialization
		 */
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setForeground(new Color(240, 255, 240));
		frame.setForeground(new Color(25, 25, 112));
		frame.setBackground(new Color(38, 86, 136));
		frame.getContentPane().setBackground(new Color(165, 157, 175 ));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
//		
//MAIN FRAME COMPONENTS
//		
		/*
		 * Layered pane initialization
		 */
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/logo.png"));
		ImageIcon logo2 = new ImageIcon(this.getClass().getResource("/logo2.png"));
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 304, 204);
		frame.getContentPane().add(layeredPane);
		

		/*
		 * Home page Panel Initialization
		 */
		JPanel HomePage = new JPanel();
		HomePage.setBounds(0, 0, 304, 204);
		layeredPane.add(HomePage);
		HomePage.setForeground(new Color(240, 255, 240));
		HomePage.setBackground(new Color(165, 157, 175));
		HomePage.setLayout(null);
		JLabel lblHome = new JLabel(logo);
		lblHome.setBounds(0, 35, 300, 204);
		HomePage.add(lblHome);
		
		
		/*
		 * Customer login panel initialization
		 */
		JPanel custLgin = new JPanel();
		custLgin.setBounds(0, 0, 304, 204);
		layeredPane.add(custLgin);
		custLgin.setLayout(null);
		custLgin.setBackground(new Color(165, 157, 175));
		
				textFieldUsnU = new JTextField();
				textFieldUsnU.setBounds(65, 64, 130, 26);
				textFieldUsnU.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
				custLgin.add(textFieldUsnU);
//				textFieldUsnU.setColumns(10);
				
						JLabel lblUsr = new JLabel("Username:");
						lblUsr.setForeground(new Color(255, 255, 255));
						
								lblUsr.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
								lblUsr.setBounds(6, 70, 61, 16);
								custLgin.add(lblUsr);
								
										passwordFieldU = new JPasswordField();
										passwordFieldU.setBounds(65, 100, 130, 26);
										passwordFieldU.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
										custLgin.add(passwordFieldU);
										
												JLabel lblPassw = new JLabel("Password:");
												lblPassw.setForeground(new Color(255, 255, 255));
												lblPassw.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
												lblPassw.setBounds(6, 106, 61, 16);
												custLgin.add(lblPassw);
												
												JLabel lblMsg = new JLabel();
												lblMsg.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
												lblMsg.setBounds(79, 140, 130, 26);
												custLgin.add(lblMsg);
												/*
												 * User Login action performed
												 */
														JButton btnLogin = new JButton("Log in");
														btnLogin.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
														/*
														 * Action Listener for login button
														 */
														btnLogin.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																  String usrName = textFieldUsnU.getText();
																  String pass = String.valueOf(passwordFieldU.getPassword());
																  try {
																  CustomerModel C = new CustomerModel(usrName, "","","",pass);
																//check if Customer is in the database
																  	if(C.isInDb()) {
																  		//authenticate entered credentials
																  		if(C.authenticate(usrName, pass)) {
																		  //Customer authenticated
																		  lblMsg.setForeground(new Color(35, 250, 1));
																		  lblMsg.setText("Login Successful");
																		  //dispose frame and change to customer profile page
																		  frame.dispose();
																		   cv = new CustomerView(C, SysAdmin);
																		
																	  	}
																  		else {
																		  //Authentication failed
																		  lblMsg.setForeground(new Color(237, 28, 7));
																		  lblMsg.setText("Incorrect Password");
																		  
																  		}
																	  
																  	}
																  	else {
																  		//incorect username
																	  lblMsg.setForeground(new Color(237, 28, 7));
																	  lblMsg.setText("Incorrect username or password");
																	  
																  		}
																  }
																  catch(Exception e1) {
																	  e1.printStackTrace();
																  }
																
															}
														});
														
														btnLogin.setBounds(219, 165, 75, 29);
														custLgin.add(btnLogin);
														
																JLabel lblLogo2 = new JLabel(logo2);
																lblLogo2.setBounds(0, 0, 131, 42);
																custLgin.add(lblLogo2);
																/*
																 * User reset action performed
																 */
																JButton btnResetUsr = new JButton("Reset");
																btnResetUsr.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		textFieldUsnU.setText("");
																		passwordFieldU.setText("");
																		lblMsg.setText("");
																	}
																});
																btnResetUsr.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
																btnResetUsr.setBounds(13, 175, 30, 15);
																custLgin.add(btnResetUsr);
																
																JLabel lblCustomer = new JLabel("Customer Login");
																lblCustomer.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
																lblCustomer.setBounds(237, 16, 61, 16);
																custLgin.add(lblCustomer);
																
		/*
		 * Sign up page panel Initialization			
		 */

		JPanel Regstr = new JPanel();
		Regstr.setBounds(0, 0, 304, 204);
		layeredPane.add(Regstr);
		Regstr.setBackground(new Color(165, 157, 175));
		Regstr.setLayout(null);

		JLabel lblmsg = new JLabel("Please enter the following information.");
		lblmsg.setForeground(new Color(255, 255, 255));
		lblmsg.setBounds(24, 7, 173, 11);
		lblmsg.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		Regstr.add(lblmsg);

		JLabel lblFirstN = new JLabel("First Name:*");
		lblFirstN.setBounds(78, 30, 64, 13);
		lblFirstN.setForeground(new Color(255, 255, 255));
		lblFirstN.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		Regstr.add(lblFirstN);

		textFieldFNS = new JTextField();
		textFieldFNS.setBounds(201, 26, 103, 26);
		textFieldFNS.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		Regstr.add(textFieldFNS);
		textFieldFNS.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:*");
		lblLastName.setBounds(78, 60, 63, 13);
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		Regstr.add(lblLastName);

		textFieldLNS = new JTextField();
		textFieldLNS.setBounds(201, 56, 103, 26);
		textFieldLNS.setColumns(10);
		textFieldLNS.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		Regstr.add(textFieldLNS);

		JLabel lblUserN1 = new JLabel("Username:*");
		lblUserN1.setBounds(78, 90, 63, 13);
		lblUserN1.setForeground(new Color(255, 255, 255));
		lblUserN1.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		Regstr.add(lblUserN1);

		textFieldUsnS = new JTextField();
		textFieldUsnS.setBounds(201, 86, 103, 26);
		textFieldUsnS.setColumns(10);
		textFieldUsnS.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		Regstr.add(textFieldUsnS);

		JLabel lblPass1 = new JLabel("Email:*");
		lblPass1.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblPass1.setBounds(78, 120, 69, 16);
		lblPass1.setForeground(new Color(255, 255, 255));
		Regstr.add(lblPass1);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(201, 116, 103, 26);
		textFieldEmail.setColumns(10);
		textFieldEmail.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		Regstr.add(textFieldEmail);

		JLabel lblPass2 = new JLabel("Password:*");
		lblPass2.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblPass2.setBounds(78, 150, 123, 16);
		lblPass2.setForeground(new Color(255, 255, 255));
		Regstr.add(lblPass2);

		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(201, 146, 103, 26);
	//	textFieldPass.setColumns(10);
		textFieldPass.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		
		Regstr.add(textFieldPass);
		
		

		JLabel lblMsgSU = new JLabel();
		lblMsgSU.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblMsgSU.setBounds(5, 165, 240, 26);
		Regstr.add(lblMsgSU);
		
	
/*
 * Signup button to add to user into database
 *  Sign up button action performed
 */
		JButton btnToSignup = new JButton("Sign Up");
		btnToSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String Fname = textFieldFNS.getText();
					String Lname = textFieldLNS.getText();
					String Uname = textFieldUsnS.getText();
					String pass = String.valueOf(textFieldPass.getPassword());
					String email = textFieldEmail.getText();
					try {
						//create new cutomer object using details 
					 CustomerModel C = new CustomerModel(Uname, Fname,Lname,email,pass);
					 if (!C.isInDb()) {
						 C.register();
						 

						 //reset fields and show message
							textFieldFNS.setText("");
							textFieldLNS.setText("");
							textFieldUsnS.setText("");
							textFieldEmail.setText("");
							textFieldPass.setText("");
						 lblMsgSU.setForeground(new Color(35, 250, 1));
						 lblMsgSU.setText("Account created Successfully, proceed to Customer Login.");
						
					 }
					 else {
						//user name is taken
						 lblMsgSU.setForeground(new Color(237, 28, 7));
						 lblMsgSU.setText("Username Already Taken");
					 }
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				
			}
		});
		
		btnToSignup.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		btnToSignup.setBounds(new Rectangle(229, 177, 75, 29));
		Regstr.add(btnToSignup);
		/*
		 * Signup reset action performed
		 */
		JButton btnResetSu = new JButton("Reset");
		btnResetSu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFNS.setText("");
				textFieldLNS.setText("");
				textFieldUsnS.setText("");
				textFieldEmail.setText("");
				textFieldPass.setText("");
				lblMsgSU.setText("");
				
			}
		});
		btnResetSu.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetSu.setBounds(262, 6, 30, 15);
		Regstr.add(btnResetSu);
/*
 * Admin login panel
 */
		JPanel AdmLgin = new JPanel();
		AdmLgin.setBounds(0, 0, 304, 204);
		layeredPane.add(AdmLgin);
		AdmLgin.setBackground(new Color(165, 157, 175));
		AdmLgin.setLayout(null);

		textFieldUsnA = new JTextField();
		//textFieldUsnA.setColumns(10);
		textFieldUsnA.setBounds(65, 64, 130, 26);
		textFieldUsnA.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		AdmLgin.add(textFieldUsnA);

		JLabel lblUsr_1 = new JLabel("Username:");
		lblUsr_1.setForeground(Color.WHITE);
		lblUsr_1.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblUsr_1.setBounds(6, 70, 61, 16);
		AdmLgin.add(lblUsr_1);

		passwordFieldA = new JPasswordField();
		passwordFieldA.setBounds(65, 100, 130, 26);
		passwordFieldA.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		AdmLgin.add(passwordFieldA);

		JLabel lblPassw_1 = new JLabel("Password:");
		lblPassw_1.setForeground(Color.WHITE);
		lblPassw_1.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblPassw_1.setBounds(6, 106, 61, 16);
		AdmLgin.add(lblPassw_1);
		
		JLabel lblMsgA = new JLabel();
		lblMsgA.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblMsgA.setBounds(79, 140, 130, 26);
		AdmLgin.add(lblMsgA);
/*
 * Admin login button action performed
 * 
 */
		JButton btnAdmintologin = new JButton("Log in");
		btnAdmintologin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String usrName = textFieldUsnA.getText();
				  String pass = String.valueOf(passwordFieldA.getPassword());
				//Validate credentials
				  if(usrName.equals(SysAdmin.getuName())) {
					  if(pass.equals(SysAdmin.getPass())){
						  //display login successful and change frame if validated
						  lblMsgA.setForeground(new Color(35, 250, 1));
						  lblMsgA.setText("Login Successful");
						  frame.dispose();
						  //change to admin profile page		  
						   av = new AdminView(SysAdmin);
						  
					  }
					  //display incorrect username of password
					  lblMsgA.setForeground(new Color(237, 28, 7));
					  lblMsgA.setText("Incorrect Username or Password");
					  
					 
				  }
				  else {
					  //Username or password is incorrect
					  lblMsgA.setForeground(new Color(237, 28, 7));
					  lblMsgA.setText("Incorrect Username or Password");
				  }
			}
		});
		btnAdmintologin.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		btnAdmintologin.setBounds(219, 165, 75, 29);
		AdmLgin.add(btnAdmintologin);

		JLabel lblLogo2_1 = new JLabel(logo2);
		lblLogo2_1.setBounds(0, 0, 131, 42);
		AdmLgin.add(lblLogo2_1);

		JLabel lblAdmins = new JLabel("Administrator Login");
		lblAdmins.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblAdmins.setBounds(228, 16, 75, 16);
		AdmLgin.add(lblAdmins);
		
		
		/*
		 * Admin reset button action performed
		 */
		JButton btnResetAd = new JButton("Reset");
		btnResetAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsnA.setText("");
				passwordFieldA.setText("");
				lblMsgA.setText("");
			}
		});
		btnResetAd.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetAd.setBounds(13, 175, 30, 15);
		AdmLgin.add(btnResetAd);
		
/*
 * Officer Log in panel
 * 
 */
		JPanel OfficerLgin = new JPanel();
		OfficerLgin.setBounds(0, 0, 304, 204);
		layeredPane.add(OfficerLgin);
		OfficerLgin.setBackground(new Color(165, 157, 175));
		OfficerLgin.setLayout(null);
		
		textFieldUsnO = new JTextField();
		//textFieldUsnO.setColumns(10);
		textFieldUsnO.setBounds(65, 64, 130, 26);
		textFieldUsnO.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		OfficerLgin.add(textFieldUsnO);
		
		JLabel lblUsrO = new JLabel("OfficerID:");
		lblUsrO.setForeground(Color.WHITE);
		lblUsrO.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblUsrO.setBounds(6, 70, 61, 16);
		OfficerLgin.add(lblUsrO);
		
		passwordFieldO = new JPasswordField();
		passwordFieldO.setBounds(65, 100, 130, 26);
		passwordFieldO.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		OfficerLgin.add(passwordFieldO);
		
		JLabel lblPasswO = new JLabel("Password:");
		lblPasswO.setForeground(Color.WHITE);
		lblPasswO.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblPasswO.setBounds(6, 106, 61, 16);
		OfficerLgin.add(lblPasswO);
		
		JButton btnOfficertologin = new JButton("Log in");
		btnOfficertologin.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		btnOfficertologin.setBounds(219, 165, 75, 29);
		OfficerLgin.add(btnOfficertologin);
		
		JLabel lblMsgO = new JLabel();
		lblMsgO.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblMsgO.setBounds(79, 140, 130, 26);
		OfficerLgin.add(lblMsgO);
		
		btnOfficertologin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String usrName = textFieldUsnO.getText();
				  String pass = String.valueOf(passwordFieldO.getPassword());
				  
					//check if Officer is in the database
					  try {
						  OfficerModel O = new OfficerModel(usrName,"","","",pass);
						if(SysAdmin.hasPEO(usrName)) {
							  if(O.authenticate(usrName, pass)) {
								  //Officer authenticated
								  lblMsgO.setForeground(new Color(35, 250, 1));
								  lblMsgO.setText("Login Successful");
								  frame.dispose();
								  //Change to officer profile page
								   ov = new OfficerView(O,SysAdmin);
							  }
							  //Validation failed
							  lblMsgO.setForeground(new Color(237, 28, 7));
							  lblMsgO.setText("Incorrect Password");
							  
							
							  
						  }
						  else {
							  //Username is incorrect
							  lblMsgO.setForeground(new Color(237, 28, 7));
							  lblMsgO.setText("Incorrect Username");
						  }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		JLabel lblLogo2O = new JLabel(logo2);
		lblLogo2O.setBounds(0, 0, 131, 42);
		OfficerLgin.add(lblLogo2O);
		
		JLabel lblOfficer = new JLabel("P.E. Officer Login");
		lblOfficer.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblOfficer.setBounds(237, 16, 65, 16);
		OfficerLgin.add(lblOfficer);
		
		JButton btnResetO = new JButton("Reset");
		btnResetO.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetO.setBounds(13, 175, 30, 15);
		OfficerLgin.add(btnResetO);
		btnResetO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsnO.setText("");
				passwordFieldO.setText("");
			}
		});
		
//								
//MAIN FRAME BUTTONS AND ACTIONS
//								

		/*
		 * Button to show User Log in panel
		 * User icon for user login button, user button initialization, user button
		 * action performed.
		 */

		
	

		ImageIcon icUs = new ImageIcon(this.getClass().getResource("/user.png"));
		JButton btnCustomer = new JButton("Customer ");
		btnCustomer.setToolTipText("\n");
		//.setVerticalAlignment(SwingConstants.TOP);
		btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomer.setBorder(UIManager.getBorder("Button.border"));
		btnCustomer.setBackground(Color.black);
		btnCustomer.setForeground(Color.white);
		btnCustomer.setOpaque(true);
		btnCustomer.setBorderPainted(false);

		btnCustomer.setIcon(icUs);

		btnCustomer.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnCustomer.setBounds(366, 62, 84, 29);
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(custLgin);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		/*
		 * Add user button to frame.
		 */

		frame.getContentPane().add(btnCustomer);
		/*
		 * Admin icon for user login button, Admin button initialization, Admin button
		 * action performed.
		 */
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(AdmLgin);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		ImageIcon icAd = new ImageIcon(this.getClass().getResource("/user.png"));

		btnAdmin.setBackground(Color.black);
		btnAdmin.setForeground(Color.white);
		btnAdmin.setOpaque(true);
		btnAdmin.setBorderPainted(false);
		btnAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdmin.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnAdmin.setBounds(366, 144, 84, 29);
		btnAdmin.setIcon(icAd);
		/*
		 * Add admin button to frame
		 */
		frame.getContentPane().add(btnAdmin);
		/*
		 * signup icon for user login button, signup button initialization, signup
		 * button action performed.
		 */
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Regstr);
				layeredPane.repaint();
				layeredPane.validate();
			}

		});
		ImageIcon icSu = new ImageIcon(this.getClass().getResource("/signup.png"));
		btnSignUp.setHorizontalAlignment(SwingConstants.LEFT);
		btnSignUp.setBackground(Color.black);
		btnSignUp.setForeground(Color.white);
		btnSignUp.setOpaque(true);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setIcon(icSu);
		btnSignUp.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnSignUp.setBounds(366, 243, 84, 29);

		/*
		 * Add signup button to frame.
		 */
		frame.getContentPane().add(btnSignUp);

		/*
		 * home icon for user login button, home button initialization, home button
		 * action performed.
		 */
		JButton btnHome = new JButton();
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(HomePage);
				layeredPane.repaint();
				layeredPane.validate();

			}
		});
		// btnHome.setBackground(new Color(11, 47, 100));
		btnHome.setBackground(Color.black);
		btnHome.setForeground(Color.white);
		btnHome.setOpaque(true);
		btnHome.setBorderPainted(false);

		ImageIcon icH = new ImageIcon(this.getClass().getResource("/home1.png"));
		btnHome.setIcon(icH);
		btnHome.setBounds(0, 243, 36, 35);
		/*
		 * Add home button to frame.
		 */
		frame.getContentPane().add(btnHome);
		
		JButton btnOfficer = new JButton("Officer");
		btnOfficer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(OfficerLgin);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		btnOfficer.setOpaque(true); 
		btnOfficer.setForeground(Color.WHITE);
		btnOfficer.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnOfficer.setBorderPainted(false);
		btnOfficer.setBackground(Color.BLACK);
		btnOfficer.setIcon(icUs);
		btnOfficer.setHorizontalAlignment(SwingConstants.LEFT);
		btnOfficer.setBounds(366, 103, 84, 29);
		frame.getContentPane().add(btnOfficer);
		
		layeredPane.removeAll();
		layeredPane.add(HomePage);
	}

}

