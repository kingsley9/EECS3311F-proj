package com.expressparking;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class OfficerManageView {
	DefaultTableModel  model;
	protected JFrame frame;
	protected JTextField textFieldASpaceNumber;
	private String[] pkSpArr;
	protected JComboBox<String> comboBoxPkSpotList;
	protected OfficerView oHome;
	protected JTextField textFieldCnumber;
	
	protected JTextField textFieldPSpaceNumber;
	//private JTextField textFieldALoc;
	private JTable tableDetails;
	
public OfficerManageView(OfficerModel off, AdminModel sysAdmin) {
		
	
	
		ImageIcon logo2 = new ImageIcon(this.getClass().getResource("/logo2.png"));
		ImageIcon bk = new ImageIcon(this.getClass().getResource("/Bookings.png"));
//		ImageIcon can = new ImageIcon(this.getClass().getResource("/cancel.png"));
//		ImageIcon chk = new ImageIcon(this.getClass().getResource("/check.png"));
		ImageIcon edt = new ImageIcon(this.getClass().getResource("/edit.png"));
		ImageIcon desgn = new ImageIcon(this.getClass().getResource("/design2.png"));
		String Fname = off.getlName();
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
		model = new DefaultTableModel();
		Object[] column = {"Parking Space Number", "Expiry Time", "Payment Status"};
		model.setColumnIdentifiers(column);
		
		JPanel cancelReqPanel = new JPanel();
		cancelReqPanel.setLayout(null);
		cancelReqPanel.setBackground(new Color(165, 157, 175));
		cancelReqPanel.setBounds(0, 0, 530, 270);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(165, 157, 175));
		scrollPane.setBounds(0, 0, 530, 41);
		cancelReqPanel.add(scrollPane);
		
		
		tableDetails = new JTable();
		tableDetails.setBackground(new Color(165, 157, 175));
		tableDetails.setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
		tableDetails.setModel(model);
		tableDetails.getTableHeader().setReorderingAllowed(false);
		
		
		
		
		
		
		
		
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 530, 270);
		welcomePanel.setBackground(new Color(165, 157, 175 ));
		layeredPane.add(welcomePanel);
		welcomePanel.setLayout(null);
		

		JLabel lblWelcome = new JLabel("Welcome, Officer "+Fname);
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
		
		
		
		JPanel addSpacePanel = new JPanel();
		addSpacePanel.setBounds(0, 0, 530, 270);
		layeredPane.add(addSpacePanel);
		addSpacePanel.setLayout(null);
		addSpacePanel.setBackground(new Color(165, 157, 175));
		
		textFieldASpaceNumber = new JTextField();
		textFieldASpaceNumber.setBounds(166, 19, 130, 26);
		textFieldASpaceNumber.setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
		addSpacePanel.add(textFieldASpaceNumber);
		//		textFieldBSpaceNumber.setColumns(10);
				
						JLabel Aspace = new JLabel("Enter Parking Space Number:");
						Aspace.setForeground(new Color(255, 255, 255));
						
						Aspace.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
						Aspace.setBounds(30, 26, 137, 16);
						addSpacePanel.add(Aspace);
						
								
								
									
										JLabel lblMsg = new JLabel();
										lblMsg.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
										lblMsg.setBounds(156, 127, 130, 26);
										addSpacePanel.add(lblMsg);
										JButton btnAspace = new JButton("Add Space");
										btnAspace.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
										btnAspace.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String p =textFieldASpaceNumber.getText();
												
												  try {
													  
													  if(!off.pDBhasSpot(p)) {
														  off.addPkSpot(p);
														  textFieldASpaceNumber.setText("");
														  lblMsg.setForeground(new Color(35, 250, 1));
														  
														  lblMsg.setText("Parking Space Successfully added!");
														
													 }
													 
													
														
														else {
															//customer already has booking
															 lblMsg.setForeground(new Color(237, 28, 7));
															 lblMsg.setText("Parking Space ID already exists");
														
															
														}
												
												  }
												  catch(Exception e1) {
													  e1.printStackTrace();
												  }
											
												
											}
										});
										
										btnAspace.setBounds(220, 65, 75, 29);
										addSpacePanel.add(btnAspace);
										
												
												JButton btnResetA = new JButton("Reset");
												btnResetA.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														textFieldASpaceNumber.setText("");
														
														lblMsg.setText("");
													}
												});
												btnResetA.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
												btnResetA.setBounds(6, 249, 30, 15);
												addSpacePanel.add(btnResetA);
												
												
												JLabel lblAddSpace = new JLabel("ADD SPACE");
												lblAddSpace.setForeground(Color.BLACK);
												lblAddSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
												lblAddSpace.setBounds(432, 6, 92, 16);
												addSpacePanel.add(lblAddSpace);
		
		
		
		
		
		
		JPanel removePanel = new JPanel();//My bookings Panel
		removePanel.setBounds(0, 0, 530, 270);
		removePanel.setBackground(new Color(165, 157, 175));
		layeredPane.add(removePanel);
		removePanel.setLayout(null);
		JLabel lblRemoveSpce = new JLabel("REMOVE SPACE");
		lblRemoveSpce.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		lblRemoveSpce.setForeground(new Color(0,0,0));
		lblRemoveSpce.setBounds(432, 6, 92, 16);
		removePanel.add(lblRemoveSpce);
		
		comboBoxPkSpotList = new JComboBox<>();
		comboBoxPkSpotList.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		comboBoxPkSpotList.setBounds(266, 46, 124, 27);
		removePanel.add(comboBoxPkSpotList);
		
		JLabel lblNewLabelSLCT = new JLabel("Select Parking Space:");
		lblNewLabelSLCT.setForeground(Color.WHITE);
		lblNewLabelSLCT.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelSLCT.setBounds(138, 46, 116, 27);
		removePanel.add(lblNewLabelSLCT);
		
		JButton btnResetR = new JButton("Reset");
		btnResetR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldASpaceNumber.setText("");
			
			
				
			}
		});
		btnResetR.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetR.setBounds(17, 238, 30, 15);
		removePanel.add(btnResetR);
		
		JButton btnRremove = new JButton("Remove Space");
		btnRremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psNum = (String) comboBoxPkSpotList.getSelectedItem();
				try {
				off.remPkSpot(psNum);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRremove.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnRremove.setBounds(300, 100, 83, 29);
		removePanel.add(btnRremove);
		
		JPanel grantReqPanel = new JPanel();
		grantReqPanel.setLayout(null);
		grantReqPanel.setBackground(new Color(165, 157, 175));
		grantReqPanel.setBounds(0, 0, 530, 270);
		layeredPane.add(grantReqPanel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(165, 157, 175));
		scrollPane_1.setBounds(6, 0, 530, 41);
		grantReqPanel.add(scrollPane_1);
		
		JButton btnGgrantReq = new JButton("Grant");
		btnGgrantReq.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnGgrantReq.setBounds(465, 53, 59, 29);
		grantReqPanel.add(btnGgrantReq);
		
		JButton btnBackG = new JButton("Back");
		btnBackG.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnBackG.setBounds(16, 53, 59, 29);
		grantReqPanel.add(btnBackG);
		scrollPane.setViewportView(tableDetails);
		layeredPane.add(cancelReqPanel);
		
		JButton btnCancelRe = new JButton("Cancel");
		btnCancelRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCancelRe.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnCancelRe.setBounds(465, 53, 59, 29);
		cancelReqPanel.add(btnCancelRe);
		
		JButton btnBackC = new JButton("Back");
		btnBackC.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnBackC.setBounds(16, 53, 59, 29);
		cancelReqPanel.add(btnBackC);
		
		
		/*
		 * Frame Buttons and labels for logo
		 */
		
		JButton btnRemSpace = new JButton("REMOVE SPACE");
		btnRemSpace.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnRemSpace.addActionListener(new ActionListener() {
			/*
			 * Remove space action listener to initialize checkbox
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					
					 pkSpArr= off.pkSpArray();
					 comboBoxPkSpotList.setModel(new DefaultComboBoxModel<>(pkSpArr));
					 
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				layeredPane.removeAll();
				layeredPane.add(removePanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		
		btnRemSpace.setOpaque(true); 
		btnRemSpace.setForeground(Color.WHITE);
		btnRemSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnRemSpace.setBorderPainted(false);
		btnRemSpace.setBackground(Color.BLACK);
		btnRemSpace.setIcon(bk);
		btnRemSpace.setBounds(585, 115, 100, 29);
		frame.getContentPane().add(btnRemSpace);
		
//		JButton btnGrantReq = new JButton("GRANT REQUEST");
//		btnGrantReq.setHorizontalAlignment(SwingConstants.LEFT);
//		btnGrantReq.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				layeredPane.removeAll();
//				layeredPane.add(grantReqPanel);
//				layeredPane.repaint();
//				layeredPane.validate();
//			}
//		});
//		btnGrantReq.setOpaque(true);
//		btnGrantReq.setForeground(Color.WHITE);
//		btnGrantReq.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
//		btnGrantReq.setBorderPainted(false);
//		btnGrantReq.setBackground(Color.BLACK);
//		btnGrantReq.setIcon(chk);
//		btnGrantReq.setBounds(585, 156, 104, 26);
//		frame.getContentPane().add(btnGrantReq);
//		
//		JButton btnCancelReq = new JButton("CANCEL REQUEST");
//		btnCancelReq.setHorizontalAlignment(SwingConstants.LEFT);
//		btnCancelReq.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				layeredPane.removeAll();
//				layeredPane.add(cancelReqPanel);
//				layeredPane.repaint();
//				layeredPane.validate();
//			}
//		});
//		btnCancelReq.setOpaque(true);
//		btnCancelReq.setForeground(Color.WHITE);
//		btnCancelReq.setFont(new Font("Proxima Nova A", Font.PLAIN, 7));
//		btnCancelReq.setBorderPainted(false);
//		btnCancelReq.setBackground(Color.BLACK);
//		btnCancelReq.setIcon(can);
//		btnCancelReq.setBounds(585, 195, 104, 29);
//		frame.getContentPane().add(btnCancelReq);
		
		JButton btnAddSpace = new JButton("ADD SPACE");
		btnAddSpace.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(addSpacePanel);
				layeredPane.repaint();
				layeredPane.validate();
				
			}
		});
		btnAddSpace.setOpaque(true);
		btnAddSpace.setForeground(Color.WHITE);
		btnAddSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnAddSpace.setBorderPainted(false);
		btnAddSpace.setBackground(Color.BLACK);
		btnAddSpace.setIcon(edt);
		btnAddSpace.setBounds(585, 74, 95, 29);  
		frame.getContentPane().add(btnAddSpace);
		
		/*
		 * home icon for user login button, home button initialization, home button
		 * action performed.
		 */
		JButton btnHome = new JButton();
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			 oHome= new OfficerView(off, sysAdmin);
			

			}
		});
		// btnHome.setBackground(new Color(11, 47, 100));
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
		
		JLabel lblNewLabel_1 = new JLabel("MANAGE PARKING SPACES");
		lblNewLabel_1.setFont(new Font("Lao MN", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(1, 0, 160, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblLogoC = new JLabel(logo2);
		lblLogoC.setBounds(536, 0, 144, 54);
		frame.getContentPane().add(lblLogoC);
		

	
	
		layeredPane.removeAll();
		layeredPane.add(welcomePanel);
		
	}
}

