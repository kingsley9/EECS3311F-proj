package com.expressparking;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;


public class CustomerView {
	/*
	 * Fields
	 */
	DefaultTableModel  model;
	protected JFrame frame;
	protected JTextField textFieldBSpaceNumber;
	private final double RATE=10.53;//Rate at which to calculate parking cost per hour
	private double total;
	protected JTextField textFieldCnumber;
	protected LoginSignUpView mvc;
	protected JTextField textFieldPSpaceNumber;
	private JTextField textFieldBLicNum;
	private JTextField textFieldBTime;
	private JTable tableDetails;
	private JComboBox<String> comboBoxParkListC,comboBoxBookList,comboBoxParkList, comboBoxPymtMthd, comboBoxPayPkSpace;
	private String[] pkSpArr;
	/*
	 * Contructor to initialize view
	 */
	public CustomerView(CustomerModel cust, AdminModel sysAdmin) {
		
		ImageIcon logo2 = new ImageIcon(this.getClass().getResource("/logo2.png"));
		ImageIcon bk = new ImageIcon(this.getClass().getResource("/Bookings.png"));
		ImageIcon can = new ImageIcon(this.getClass().getResource("/cancel.png"));
		ImageIcon chk = new ImageIcon(this.getClass().getResource("/check.png"));
		ImageIcon edt = new ImageIcon(this.getClass().getResource("/edit.png"));
		ImageIcon desgn = new ImageIcon(this.getClass().getResource("/design2.png"));
		String Fname = cust.getfName();
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
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 530, 270);
		welcomePanel.setBackground(new Color(165, 157, 175 ));
		layeredPane.add(welcomePanel);
		welcomePanel.setLayout(null);
		

		JLabel lblWelcome = new JLabel("Welcome, "+ Fname);
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
		 * View Space details panel
		 */
		JPanel spaceDetailsPanel = new JPanel();
		spaceDetailsPanel.setLayout(null);
		spaceDetailsPanel.setBackground(new Color(165, 157, 175));
		spaceDetailsPanel.setBounds(0, 0, 530, 270);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(165, 157, 175));
		scrollPane.setBounds(0, 0, 530, 41);
		spaceDetailsPanel.add(scrollPane);
		
		
		tableDetails = new JTable();
		tableDetails.setBackground(new Color(165, 157, 175));
		tableDetails.setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
		
		tableDetails.getTableHeader().setReorderingAllowed(false);
	
		
		
		
		/*
		 * Book new Space panel
		 */
		JPanel bookSpacePanel = new JPanel();
		bookSpacePanel.setBounds(0, 0, 530, 270);
		layeredPane.add(bookSpacePanel);
		bookSpacePanel.setLayout(null);
		bookSpacePanel.setBackground(new Color(165, 157, 175));
		
		comboBoxParkList = new JComboBox<>();
		comboBoxParkList.setBounds(166, 18, 124, 27);
		comboBoxParkList.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		bookSpacePanel.add(comboBoxParkList);
		
		JLabel lblNewLabelSLCTP = new JLabel("Select Parking Space:");
		lblNewLabelSLCTP.setForeground(Color.WHITE);
		lblNewLabelSLCTP.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelSLCTP.setBounds(51, 18, 116, 27);
		bookSpacePanel.add(lblNewLabelSLCTP);
						
								
								
									
										JLabel lblMsg = new JLabel();
										lblMsg.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
										lblMsg.setBounds(156, 127, 130, 26);
										bookSpacePanel.add(lblMsg);
										JButton btnBspace = new JButton("Book Space");
										btnBspace.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
										btnBspace.addActionListener(new ActionListener() {
											//add booking button action performed
											public void actionPerformed(ActionEvent e) {
												
												String psNum = (String) comboBoxParkList.getSelectedItem();
												
												 int hrs=Integer.parseInt(textFieldBTime.getText());
												  LocalDateTime currentTime= LocalDateTime.now();
												  currentTime.plusHours(hrs);
												  String time = currentTime.toString();
												  String lic = textFieldBLicNum.getText();
												  
												  try {
													  
													  if (cust.addBooking(psNum, time, lic, String.valueOf(hrs))) {
														
															 //reset fields and show message
															textFieldBLicNum.setText("");
															textFieldBTime.setText("");
															
																lblMsg.setForeground(new Color(35, 250, 1));
																lblMsg.setText("Booking Successfully added!");
															
														 }
														 else {
															//customer booking list full
															 lblMsg.setForeground(new Color(237, 28, 7));
															 lblMsg.setText("Booking list is full. Proceed to payment.");
														 }
												
												  }
												  catch(Exception e1) {
													  e1.printStackTrace();
												  }
												
											}
										});
										
										btnBspace.setBounds(221, 113, 75, 29);
										bookSpacePanel.add(btnBspace);
										
												
												JButton btnResetB = new JButton("Reset");
												btnResetB.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														comboBoxParkList.setSelectedIndex(0);
														textFieldBLicNum.setText("");
														textFieldBTime.setText("");
														lblMsg.setText("");
														 try {
																pkSpArr=cust.pkSpArray();
																comboBoxParkList.setModel(new DefaultComboBoxModel<>(pkSpArr));
															} catch (Exception e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
													}
												});
												btnResetB.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
												btnResetB.setBounds(6, 249, 30, 15);
												bookSpacePanel.add(btnResetB);
												
												JLabel lblBookingDurationhrs = new JLabel("Booking duration (Hrs):");
												lblBookingDurationhrs.setForeground(Color.WHITE);
												lblBookingDurationhrs.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
												lblBookingDurationhrs.setBounds(62, 82, 105, 16);
												bookSpacePanel.add(lblBookingDurationhrs);
												
												JLabel lblEnterLicen = new JLabel("Enter License Plate No.:");
												lblEnterLicen.setForeground(Color.WHITE);
												lblEnterLicen.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
												lblEnterLicen.setBounds(56, 55, 111, 16);
												bookSpacePanel.add(lblEnterLicen);
												
												textFieldBLicNum = new JTextField();
												textFieldBLicNum.setBounds(166, 47, 130, 26);
												textFieldBLicNum.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
												bookSpacePanel.add(textFieldBLicNum);
												
												textFieldBTime = new JTextField();
												textFieldBTime.setBounds(166, 75, 130, 26);
												textFieldBTime.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
												bookSpacePanel.add(textFieldBTime);
		
		
		
		
		
		
		JPanel myBookingsPanel = new JPanel();//My bookings Panel
		myBookingsPanel.setBounds(0, 0, 530, 270);
		myBookingsPanel.setBackground(new Color(165, 157, 175));
		layeredPane.add(myBookingsPanel);
		myBookingsPanel.setLayout(null);
		JLabel lblMyBookings = new JLabel("MY BOOKINGS");
		lblMyBookings.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		lblMyBookings.setForeground(new Color(0,0,0));
		lblMyBookings.setBounds(432, 6, 92, 16);
		myBookingsPanel.add(lblMyBookings);
		
		comboBoxBookList = new JComboBox<String>();
		comboBoxBookList.setBounds(266, 46, 124, 27);
		comboBoxBookList.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));

		myBookingsPanel.add(comboBoxBookList);
		
		JLabel lblNewLabelSLCT = new JLabel("Select Parking Space:");
		lblNewLabelSLCT.setForeground(Color.WHITE);
		lblNewLabelSLCT.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelSLCT.setBounds(138, 46, 116, 27);
		myBookingsPanel.add(lblNewLabelSLCT);
		
//		JButton btnResetM = new JButton("Reset");
//		btnResetM.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				
//			}
//		});
//		btnResetM.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
//		btnResetM.setBounds(17, 238, 30, 15);
//		myBookingsPanel.add(btnResetM);
		
		JButton btnMViewDetails = new JButton("View Details");
		btnMViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel();
				Object[] column = {"Parking Space Number", "Expiry Time", "License Plate"};
				model.setColumnIdentifiers(column);
				try {
			
				String psNum = (String) comboBoxBookList.getSelectedItem();
				ArrayList<String[]> deets =cust.getDetails();
				for(String []str : deets) {
					if(str[1].equals(psNum)) {
						Object[] row = {str[1], str[2], str[3]};
						model.addRow(row);
					}
				}
				tableDetails.setModel(model);
				tableDetails.setEnabled(false);
				layeredPane.removeAll();
			    layeredPane.add(spaceDetailsPanel);
				layeredPane.repaint();
				layeredPane.validate();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
					
			}
		});
		btnMViewDetails.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnMViewDetails.setBounds(300, 100, 75, 29);
		myBookingsPanel.add(btnMViewDetails);
		scrollPane.setViewportView(tableDetails);
		layeredPane.add(spaceDetailsPanel);
		
		JButton btnMBackToSelect = new JButton("Back");
		btnMBackToSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxBookList.setSelectedIndex(0);
				layeredPane.removeAll();
			    layeredPane.add(myBookingsPanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		btnMBackToSelect.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnMBackToSelect.setBounds(465, 53, 59, 29);
		spaceDetailsPanel.add(btnMBackToSelect);
	
		
	
		JPanel cancelPanel = new JPanel(); //Cancel panel 
		cancelPanel.setLayout(null);
		cancelPanel.setBounds(0, 0, 530, 270);
		cancelPanel.setBackground(new Color(165, 157, 175));
		layeredPane.add(cancelPanel);
		
		comboBoxParkListC = new JComboBox<>();
		comboBoxParkListC.setBounds(166, 18, 124, 27);
		comboBoxParkListC.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		cancelPanel.add(comboBoxParkListC);
		
		
		JLabel lblNewLabelSLCTC = new JLabel("Select Parking Space:");
		lblNewLabelSLCTC.setForeground(Color.WHITE);
		lblNewLabelSLCTC.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelSLCTC.setBounds(51, 18, 116, 27);
		cancelPanel.add(lblNewLabelSLCTC);
		
		JLabel lblMsg_1 = new JLabel();
		lblMsg_1.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblMsg_1.setBounds(100, 57, 130, 26);
		cancelPanel.add(lblMsg_1);
		
		JButton btnCcancelSpace = new JButton("Cancel Space");
		btnCcancelSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String psNum = (String) comboBoxParkListC.getSelectedItem();
					cust.removeBooking(psNum);
					lblMsg_1.setForeground(new Color(35, 250, 1));
					lblMsg_1.setText("Booking Successfully Canceled!");
					
				}
				catch(Exception e1) {
					
				}
				
			}
		});
		btnCcancelSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnCcancelSpace.setBounds(221, 60, 75, 29);
		cancelPanel.add(btnCcancelSpace);
		
		JButton btnResetC = new JButton("Refresh");
		btnResetC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsg_1.setText("");
				 try {
					pkSpArr=cust.myBkArray();
					 comboBoxParkListC.setModel(new DefaultComboBoxModel<>(pkSpArr));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnResetC.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetC.setBounds(17, 238, 34, 15);
		cancelPanel.add(btnResetC);
		
		
		
		JLabel lblCancellations = new JLabel("CANCELLATIONS");
		lblCancellations.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		lblCancellations.setForeground(new Color(237, 28, 7));
		lblCancellations.setBounds(432, 6, 92, 16);
		cancelPanel.add(lblCancellations);
		
		/*
		 * Pay page panel
		 */
		JPanel payPanel = new JPanel();
		payPanel.setLayout(null);
		payPanel.setBounds(0, 0, 530, 270);
		payPanel.setBackground(new Color(165, 157, 175));
		payPanel.setLayout(null);
		
		JLabel lblPurchaseSpace = new JLabel("PURCHASE SPACE");
		lblPurchaseSpace.setBounds(432, 5, 92, 16);
		lblPurchaseSpace.setForeground(Color.BLACK);
		lblPurchaseSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		payPanel.add(lblPurchaseSpace);
		
		comboBoxPymtMthd = new JComboBox<String>();
		comboBoxPymtMthd.setBounds(209, 75, 124, 27);
		comboBoxPymtMthd.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		payPanel.add(comboBoxPymtMthd);
		
		JLabel lblSelectPaymentMethod = new JLabel("Select Payment Method:");
		lblSelectPaymentMethod.setBounds(85, 75, 126, 27);
		lblSelectPaymentMethod.setForeground(Color.WHITE);
		lblSelectPaymentMethod.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		payPanel.add(lblSelectPaymentMethod);
		
		
		
		JLabel lblNewLabelTotalToPay = new JLabel("Total To Pay:");
		lblNewLabelTotalToPay.setForeground(Color.WHITE);
		lblNewLabelTotalToPay.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelTotalToPay.setBounds(135, 130, 75, 27);
		payPanel.add(lblNewLabelTotalToPay);
		
		JLabel lblMsgTotal = new JLabel();
		lblMsgTotal.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		lblMsgTotal.setBounds(209, 130, 106, 26);
		lblMsgTotal.setForeground(Color.WHITE);
		payPanel.add(lblMsgTotal);
		
		JLabel lblMsgPayReq = new JLabel();
		lblMsgPayReq.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		lblMsgPayReq.setBounds(94, 169, 337, 26);
		
		payPanel.add(lblMsgPayReq);
		
		JButton btnResetP = new JButton("Reset");
		btnResetC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				lblMsgTotal.setText("");
				lblMsgPayReq.setText("");
				
				
				
			}
		});
		btnResetP.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetP.setBounds(17, 238, 30, 15);
		payPanel.add(btnResetP);
		
		
		
		JLabel lblNewLabelSLCTPpay = new JLabel("Select Parking Space:");
		lblNewLabelSLCTPpay.setForeground(Color.WHITE);
		lblNewLabelSLCTPpay.setFont(new Font("Proxima Nova A", Font.PLAIN, 12));
		lblNewLabelSLCTPpay.setBounds(94, 20, 116, 27);
		payPanel.add(lblNewLabelSLCTPpay);
		
		comboBoxPayPkSpace = new JComboBox<String>();
		comboBoxPayPkSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String sn= (String) comboBoxPayPkSpace.getSelectedItem();
				total = RATE*cust.getHours(sn);
				String fm =new DecimalFormat("#.##").format(total);
				lblMsgTotal.setText("$"+ fm);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBoxPayPkSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		comboBoxPayPkSpace.setBounds(209, 20, 124, 27);
		payPanel.add(comboBoxPayPkSpace);
		

		
		JButton btnConfirmPurchase = new JButton("Request Payment");
		btnConfirmPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sn=(String)comboBoxPayPkSpace.getSelectedItem();
				try {
				cust.addPayReq(sn, String.valueOf(total));
				lblMsgPayReq.setForeground(new Color(35, 250, 1));
				lblMsgPayReq.setText("Payment Request Successful!");
				pkSpArr=cust.myBkArray();
				comboBoxPayPkSpace.setModel(new DefaultComboBoxModel<>(pkSpArr));
				
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirmPurchase.setBounds(430, 200, 92, 29);
		btnConfirmPurchase.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		payPanel.add(btnConfirmPurchase);
		
	
		layeredPane.add(payPanel);
		
		/*
		 * Frame Buttons and labels for logo
		 */
		
		JButton btnMyBookings = new JButton("MY BOOKINGS");
		btnMyBookings.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnMyBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 pkSpArr=cust.myBkArray();
					 comboBoxBookList.setModel(new DefaultComboBoxModel<>(pkSpArr));
					 
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				layeredPane.removeAll();
				layeredPane.add(myBookingsPanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		
		btnMyBookings.setOpaque(true); 
		btnMyBookings.setForeground(Color.WHITE);
		btnMyBookings.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnMyBookings.setBorderPainted(false);
		btnMyBookings.setBackground(Color.BLACK);
		btnMyBookings.setIcon(bk);
		btnMyBookings.setBounds(585, 115, 100, 29);
		frame.getContentPane().add(btnMyBookings);
		
		JButton btnPay = new JButton("PAY");
		btnPay.setHorizontalAlignment(SwingConstants.LEFT);
		btnPay.addActionListener(new ActionListener() {
			/*
			 * Pay for space action listener to initialize checkboxes
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					pkSpArr=cust.myBkArray();
					comboBoxPayPkSpace.setModel(new DefaultComboBoxModel<>(pkSpArr));
					String[] pmts= {"DEBIT", "CREDIT", "PAYPAL"};
					comboBoxPymtMthd.setModel(new DefaultComboBoxModel<>(pmts));
					 
					 
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				layeredPane.removeAll();
				layeredPane.add(payPanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		btnPay.setOpaque(true);
		btnPay.setForeground(Color.WHITE);
		btnPay.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnPay.setBorderPainted(false);
		btnPay.setBackground(Color.BLACK);
		btnPay.setIcon(chk);
		btnPay.setBounds(585, 156, 97, 26);
		frame.getContentPane().add(btnPay);
		
		JButton btnCancelSpace = new JButton("CANCELLATIONS");
		btnCancelSpace.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Cancel space action listener to initialize checkbox
				 */
				try {
					
					 pkSpArr=cust.myBkArray();
					 comboBoxParkListC.setModel(new DefaultComboBoxModel<>(pkSpArr));
			
					 
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				layeredPane.removeAll();
				layeredPane.add(cancelPanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});
		btnCancelSpace.setOpaque(true);
		btnCancelSpace.setForeground(Color.WHITE);
		btnCancelSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnCancelSpace.setBorderPainted(false);
		btnCancelSpace.setBackground(Color.BLACK);
		btnCancelSpace.setIcon(can);
		btnCancelSpace.setBounds(585, 195, 99, 29);
		frame.getContentPane().add(btnCancelSpace);
		
		JButton btnBookSpace = new JButton("BOOK SPACE");
		btnBookSpace.setHorizontalAlignment(SwingConstants.LEFT);
		btnBookSpace.addActionListener(new ActionListener() {
			/*
			 * Book space action listener to initialize checkbox
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					 pkSpArr= cust.pkSpArray();
					 comboBoxParkList.setModel(new DefaultComboBoxModel<>(pkSpArr));
					 
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
				layeredPane.removeAll();
				layeredPane.add(bookSpacePanel);
				layeredPane.repaint();
				layeredPane.validate();
				
			}
		});
		btnBookSpace.setOpaque(true);
		btnBookSpace.setForeground(Color.WHITE);
		btnBookSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnBookSpace.setBorderPainted(false);
		btnBookSpace.setBackground(Color.BLACK);
		btnBookSpace.setIcon(edt);
		btnBookSpace.setBounds(585, 74, 95, 29);  
		frame.getContentPane().add(btnBookSpace);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMER PROFILE");
		lblNewLabel_1.setFont(new Font("Lao MN", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(1, 0, 120, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblLogoC = new JLabel(logo2);
		lblLogoC.setBounds(536, 0, 144, 54);
		frame.getContentPane().add(lblLogoC);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
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
