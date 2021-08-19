package com.expressparking;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AdminView {
	//Fields 
	private String[] offIDs, pkSpArr;
	protected JFrame frame;
	protected JTextField textFieldAOffid;
	protected JTextField textFieldCnumber;
	protected JTextField textFieldPSpaceNumber;
	private JTextField textFieldAOffFN;
	private JTextField textFieldAOffLN;
	private JTextField textFieldAOffEmail;
	private JPasswordField textFieldAOffPass;
	private JComboBox<String> comboBoxOffiDList, comboBoxPaySt;
	protected LoginSignUpView mvc;
	
//Constructor to Initialize View
public AdminView(AdminModel sysAdmin) {
	//Image Objects
	ImageIcon logo2 = new ImageIcon(this.getClass().getResource("/logo2.png"));
	ImageIcon bk = new ImageIcon(this.getClass().getResource("/Bookings.png"));
	ImageIcon chk = new ImageIcon(this.getClass().getResource("/check.png"));
	ImageIcon edt = new ImageIcon(this.getClass().getResource("/edit.png"));
	ImageIcon desgn = new ImageIcon(this.getClass().getResource("/design2.png"));
	//New Frame Object
	frame = new JFrame();
	frame.setVisible(true);
	frame.getContentPane().setForeground(new Color(240, 255, 240));
	frame.setForeground(new Color(25, 25, 112));
	frame.setBackground(new Color(38, 86, 136));
	frame.getContentPane().setBackground(new Color(165, 157, 175 ));
	frame.setBounds(100, 100, 680, 350);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	/*
	 * Initialize layered pane and add to frame
	 */
	JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(0, 54, 530, 270);
	frame.getContentPane().add(layeredPane);
	
	/*
	 * Create new Welcome panel and add to layered pane
	 */
	JPanel welcomePanel = new JPanel();//welcome panel
	welcomePanel.setBounds(0, 0, 530, 270);
	welcomePanel.setBackground(new Color(165, 157, 175 ));
	layeredPane.add(welcomePanel);
	welcomePanel.setLayout(null);
	

	JLabel lblWelcome = new JLabel("Welcome, Administrator");
	lblWelcome.setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
	lblWelcome.setBounds(23, 8, 170, 14);
	welcomePanel.add(lblWelcome);
	
	JLabel lblPrompt = new JLabel("Please make a selection to your right...");
	lblPrompt.setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
	lblPrompt.setBounds(217, 38, 233, 14);
	welcomePanel.add(lblPrompt);
	
	JLabel lbldesgn = new JLabel(desgn);
	lbldesgn.setBounds(0, 0, 450, 65);
	welcomePanel.add(lbldesgn);
	
	/*
	 * Remove OFF Panel
	 */
	JPanel removeOFFPanel = new JPanel();//remove officer panel
	removeOFFPanel.setBounds(0, 0, 530, 270);
	removeOFFPanel.setBackground(new Color(165, 157, 175));
	layeredPane.add(removeOFFPanel);
	removeOFFPanel.setLayout(null);
	JLabel lblRemoveOff = new JLabel("REMOVE PARKING ENFORCEMENT OFFICER");
	lblRemoveOff.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
	lblRemoveOff.setForeground(new Color(0,0,0));
	lblRemoveOff.setBounds(300, 6, 220, 16);
	removeOFFPanel.add(lblRemoveOff);

	
	comboBoxOffiDList = new JComboBox<>();
	comboBoxOffiDList.setVisible(true);
	
	comboBoxOffiDList.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
	comboBoxOffiDList.setBounds(266, 46, 124, 27);
	
	removeOFFPanel.add(comboBoxOffiDList);
	
	JLabel lblMsgR = new JLabel();
	lblMsgR.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	lblMsgR.setBounds(166, 165, 130, 26);
	removeOFFPanel.add(lblMsgR);
	
	JLabel lblNewLabelSLCT = new JLabel("Select Officer ID:");
	lblNewLabelSLCT.setForeground(Color.WHITE);
	lblNewLabelSLCT.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
	lblNewLabelSLCT.setBounds(177, 45, 92, 27);
	removeOFFPanel.add(lblNewLabelSLCT);
	
	JButton btnResetR = new JButton("Reset");
	btnResetR.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textFieldAOffid.setText("");
			textFieldAOffFN.setText("");
			lblMsgR.setText("");
		}
	});
	btnResetR.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
	btnResetR.setBounds(17, 238, 30, 15);
	removeOFFPanel.add(btnResetR);
	
	JButton btnRemoveOff = new JButton("Remove PEO");
	btnRemoveOff.addActionListener(new ActionListener() {
		/*
		 * Remove Officer Button Action performed
		 */
		public void actionPerformed(ActionEvent e) {
		if(offIDs.length>0) {
				
			
		try {
		String offID = (String) comboBoxOffiDList.getSelectedItem();
		OfficerModel rem = new OfficerModel(offID, "", "", "", "");
		sysAdmin.removePEO(rem);
		 lblMsgR.setForeground(new Color(35, 250, 1));
		 lblMsgR.setText("Officer removed Successfully");
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
			}
		else {
				 lblMsgR.setForeground(new Color(237, 28, 7));
				 lblMsgR.setText("No Officer selected");
			}
				
		}
		
	});
	btnRemoveOff.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	btnRemoveOff.setBounds(300, 100, 75, 29);
	removeOFFPanel.add(btnRemoveOff);
	
	/*
	 * Change Payment Status Panel
	 */
	JPanel paidStatusPanel = new JPanel();//change payment status panel
	paidStatusPanel.setLayout(null);
	paidStatusPanel.setBackground(new Color(165, 157, 175));
	paidStatusPanel.setBounds(0, 0, 530, 270);
	layeredPane.add(paidStatusPanel);
	

	
	JLabel lblChangePay = new JLabel("CHANGE PAYMENT STATUS");
	lblChangePay.setForeground(Color.BLACK);
	lblChangePay.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
	lblChangePay.setBounds(10, 0, 145, 16);
	paidStatusPanel.add(lblChangePay);
	
	JLabel lblMsgPaySt = new JLabel();
	lblMsgPaySt.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	lblMsgPaySt.setBounds(136, 85, 212, 26);
	paidStatusPanel.add(lblMsgPaySt);
	
	comboBoxPaySt = new JComboBox<>();
	comboBoxPaySt.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
	comboBoxPaySt.setBounds(224, 46, 124, 27);
	paidStatusPanel.add(comboBoxPaySt);
	
	JLabel lblNewLabelSLCTcust = new JLabel("Select Park Spot ID:");
	lblNewLabelSLCTcust.setForeground(Color.WHITE);
	lblNewLabelSLCTcust.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
	lblNewLabelSLCTcust.setBounds(124, 46, 104, 27);
	paidStatusPanel.add(lblNewLabelSLCTcust);
	
	JButton btnResetR_1 = new JButton("Reset");
	btnResetR_1.addActionListener(new ActionListener() {
		/*
		 * reset button action performed
		 */
		public void actionPerformed(ActionEvent e) {
			lblMsgPaySt.setText("");
		}
	});
	btnResetR_1.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
	btnResetR_1.setBounds(10, 231, 30, 15);
	paidStatusPanel.add(btnResetR_1);
	
	JButton btnGgrantReq = new JButton("Confirm Payment");
	btnGgrantReq.addActionListener(new ActionListener() {
		//Confirm Payment Action performed
		public void actionPerformed(ActionEvent e) {
			
			String s = (String) comboBoxPaySt.getSelectedItem();
			  LocalDateTime currentTime= LocalDateTime.now();
			  String time = currentTime.toString();
			 
			  
			  try {
				  sysAdmin.setPaid(s,time);
				  lblMsgPaySt.setForeground(new Color(35, 250, 1));
				  lblMsgPaySt.setText("Payment Confirmed!");
			  }catch(Exception e1) {
				  e1.printStackTrace();
			  }
			
		}
	});
	btnGgrantReq.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	btnGgrantReq.setBounds(434, 217, 90, 29);
	paidStatusPanel.add(btnGgrantReq);
	
	
	
	/*
	 * Add new Officer Panel
	 */
	
	JPanel addOFFPanel = new JPanel();//add officer panel
	addOFFPanel.setBounds(0, 0, 530, 270);
	layeredPane.add(addOFFPanel);
	addOFFPanel.setLayout(null);
	addOFFPanel.setBackground(new Color(165, 157, 175));
	
	textFieldAOffid = new JTextField();
	textFieldAOffid.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
	textFieldAOffid.setBounds(166, 20, 130, 26);
	addOFFPanel.add(textFieldAOffid);
	//		textFieldBSpaceNumber.setColumns(10);
			
					JLabel lblAOffiD = new JLabel("Officer ID:");
					lblAOffiD.setHorizontalAlignment(SwingConstants.RIGHT);
					lblAOffiD.setForeground(new Color(255, 255, 255));
					lblAOffiD.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
					lblAOffiD.setBounds(116, 25, 51, 16);
					addOFFPanel.add(lblAOffiD);
					
							
							
								
									JLabel lblMsg = new JLabel();
									lblMsg.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
									lblMsg.setBounds(166, 165, 231, 16);
									addOFFPanel.add(lblMsg);
									JButton btnAddPeo = new JButton("Add PEO");
									btnAddPeo.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
									btnAddPeo.addActionListener(new ActionListener() {
										/*
										 * Add officer Action Performed
										 */
										public void actionPerformed(ActionEvent e) {
											String Uname = textFieldAOffid.getText();
											String Fname  = textFieldAOffFN.getText();
											String  Lname= textFieldAOffLN.getText();
											String email = textFieldAOffEmail.getText();
											String pass = String.valueOf(textFieldAOffPass.getPassword());
											
											
											  try {
													

													 if (!sysAdmin.hasPEO(Uname)) {
														 sysAdmin.addPEO(Uname,Fname,Lname,email,pass);
														 
														 

														 //reset fields and show message
														 textFieldAOffid.setText("");
														 textFieldAOffFN.setText("");
														 textFieldAOffLN.setText("");
														 textFieldAOffEmail.setText("");
															textFieldAOffPass.setText("");
															lblMsg.setForeground(new Color(35, 250, 1));
															lblMsg.setText("Officer added Successfully.");
														
													 }
													 else {
														//user name is taken
														 lblMsg.setForeground(new Color(237, 28, 7));
														 lblMsg.setText("OfficerID Already Taken");
													 }
											  }
											  catch(Exception e1) {
												  e1.printStackTrace();
											  }
											
										}
									});
									
									btnAddPeo.setBounds(220, 180, 75, 29);
									addOFFPanel.add(btnAddPeo);
									
											
											JButton btnResetAOff = new JButton("Reset");
											btnResetAOff.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													textFieldAOffid.setText("");
													textFieldAOffFN.setText("");
													textFieldAOffLN.setText("");
													textFieldAOffEmail.setText("");
													textFieldAOffPass.setText("");
													lblMsg.setText("");
												}
											});
											btnResetAOff.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
											btnResetAOff.setBounds(6, 249, 30, 15);
											addOFFPanel.add(btnResetAOff);
											
											JLabel lblOffFN = new JLabel("First name:");
											lblOffFN.setHorizontalAlignment(SwingConstants.RIGHT);
											lblOffFN.setForeground(Color.WHITE);
											lblOffFN.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
											lblOffFN.setBounds(116, 55, 51, 16);
											addOFFPanel.add(lblOffFN);
											
											textFieldAOffFN = new JTextField();
											textFieldAOffFN.setBounds(166, 50, 130, 26);
											textFieldAOffFN.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
											addOFFPanel.add(textFieldAOffFN);
											
											JLabel lblAddPEO = new JLabel("ADD PARKING ENFORCEMENT OFFICER");
											lblAddPEO.setForeground(Color.BLACK);
											lblAddPEO.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
											lblAddPEO.setBounds(320, 6, 200, 16);
											addOFFPanel.add(lblAddPEO);
											
											textFieldAOffLN = new JTextField();
											textFieldAOffLN.setBounds(166, 80, 130, 26);
											textFieldAOffLN.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
											addOFFPanel.add(textFieldAOffLN);
											
											textFieldAOffEmail = new JTextField();
											textFieldAOffEmail.setBounds(166, 110, 130, 26);
											textFieldAOffEmail.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
											addOFFPanel.add(textFieldAOffEmail);
											
											textFieldAOffPass = new JPasswordField();
											textFieldAOffPass.setBounds(166, 140, 130, 26);
											textFieldAOffPass.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
											addOFFPanel.add(textFieldAOffPass);
											
											JLabel lblAOffLN = new JLabel("Last Name:");
											lblAOffLN.setHorizontalAlignment(SwingConstants.RIGHT);
											lblAOffLN.setForeground(Color.WHITE);
											lblAOffLN.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
											lblAOffLN.setBounds(107, 85, 60, 16);
											addOFFPanel.add(lblAOffLN);
											
											JLabel lblAOffEmail = new JLabel("Email:");
											lblAOffEmail.setHorizontalAlignment(SwingConstants.RIGHT);
											lblAOffEmail.setForeground(Color.WHITE);
											lblAOffEmail.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
											lblAOffEmail.setBounds(116, 115, 51, 16);
											addOFFPanel.add(lblAOffEmail);
											
											JLabel lblAOffPassword = new JLabel("Password:");
											lblAOffPassword.setHorizontalAlignment(SwingConstants.RIGHT);
											lblAOffPassword.setForeground(Color.WHITE);
											lblAOffPassword.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
											lblAOffPassword.setBounds(116, 145, 51, 16);
											addOFFPanel.add(lblAOffPassword);
	
	

	
	/*
	 * Frame Buttons and checkBox initialization 
	 */
	
	JButton btnRemOff = new JButton("<html>REMOVE<br />OFFICER</html>");
	btnRemOff.setHorizontalAlignment(SwingConstants.LEFT);
	
	btnRemOff.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			/*
			 * Initialize Officer ID Array into checkbox
			 */
			try {
				 offIDs= sysAdmin.offIDAsArray();
				 comboBoxOffiDList.setModel(new DefaultComboBoxModel<>(offIDs));
				 
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			layeredPane.removeAll();
			layeredPane.add(removeOFFPanel);
			layeredPane.repaint();
			layeredPane.validate();
		}
	});
	
	btnRemOff.setOpaque(true); 
	btnRemOff.setForeground(Color.WHITE);
	btnRemOff.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	btnRemOff.setBorderPainted(false);
	btnRemOff.setBackground(Color.BLACK);
	btnRemOff.setIcon(bk);
	btnRemOff.setBounds(585, 115, 95, 29);
	frame.getContentPane().add(btnRemOff);
	
	JButton btnPayStatus = new JButton("<html>SET PAYMENT<br />STATUS</html>");
	btnPayStatus.setHorizontalAlignment(SwingConstants.LEFT);
	btnPayStatus.addActionListener(new ActionListener() {
		/*
		 * Initialize payment request array into checkbox
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				 pkSpArr=sysAdmin.payReqArray();
				 comboBoxPaySt.setModel(new DefaultComboBoxModel<>(pkSpArr));
				 
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			layeredPane.removeAll();
			layeredPane.add(paidStatusPanel);
			layeredPane.repaint();
			layeredPane.validate();
		}
	});
	btnPayStatus.setOpaque(true);
	btnPayStatus.setForeground(Color.WHITE);
	btnPayStatus.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
	btnPayStatus.setBorderPainted(false);
	btnPayStatus.setBackground(Color.BLACK);
	btnPayStatus.setIcon(chk);
	btnPayStatus.setBounds(585, 156, 95, 26);
	frame.getContentPane().add(btnPayStatus);
	
	JButton btnAddOff = new JButton("<html>ADD<br />OFFICER</html>");
	btnAddOff.setHorizontalAlignment(SwingConstants.LEFT);
	btnAddOff.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			layeredPane.removeAll();
			layeredPane.add(addOFFPanel);
			layeredPane.repaint();
			layeredPane.validate();
			
		}
	});
	btnAddOff.setOpaque(true);
	btnAddOff.setForeground(Color.WHITE);
	btnAddOff.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
	btnAddOff.setBorderPainted(false);
	btnAddOff.setBackground(Color.BLACK);
	btnAddOff.setIcon(edt);
	btnAddOff.setBounds(585, 74, 95, 29);  
	frame.getContentPane().add(btnAddOff);
	
	/*
	 * home icon for user login button, home button initialization, home button
	 * action performed.
	 */
	JButton btnHome = new JButton();
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			layeredPane.removeAll();
			layeredPane.add(welcomePanel);
			layeredPane.repaint();
			layeredPane.validate();
		

		}
	});
	
	btnHome.setBackground(Color.black);
	btnHome.setForeground(Color.white);
	btnHome.setOpaque(true);
	btnHome.setBorderPainted(false);

	ImageIcon icH = new ImageIcon(this.getClass().getResource("/home1.png"));
	btnHome.setIcon(icH);
	btnHome.setBounds(643, 236, 36, 35);
	/*
	 * Add home button to frame.
	 */
	frame.getContentPane().add(btnHome);
	
	JLabel lblNewLabel_1 = new JLabel("ADMIN PROFILE");
	lblNewLabel_1.setFont(new Font("Lao MN", Font.PLAIN, 12));
	lblNewLabel_1.setBounds(1, 0, 160, 14);
	frame.getContentPane().add(lblNewLabel_1);
	
	JLabel lblLogoC = new JLabel(logo2);
	lblLogoC.setBounds(536, 0, 144, 54);
	frame.getContentPane().add(lblLogoC);
	

	JButton btnLogOut = new JButton("Log Out");
	btnLogOut.addActionListener(new ActionListener() {
		/*
		 * Logout Button action performed
		 */
		public void actionPerformed(ActionEvent e) {
			int ans= JOptionPane.showConfirmDialog(null, "Are you sure you want to Log out?", "Log out", JOptionPane.YES_NO_OPTION);
			if(ans==0) {
				frame.dispose();
				 mvc=new LoginSignUpView(sysAdmin);
				
			}
			else {
				JOptionPane.getRootFrame().dispose();
			}
		}
	});
	btnLogOut.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
	btnLogOut.setBounds(610, 293, 75, 29);
	frame.getContentPane().add(btnLogOut);

	layeredPane.removeAll();
	layeredPane.add(welcomePanel);
	}
}
