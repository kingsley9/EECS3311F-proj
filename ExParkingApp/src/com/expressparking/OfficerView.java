package com.expressparking;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

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
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class OfficerView {
	/*
	 * Fields
	 */
	private DefaultTableModel modelP, modelB;
	protected JFrame frame;
	protected JTextField textFieldCnumber;
	protected LoginSignUpView mvc;
	protected OfficerManageView ov;
	private JTable tableDetails;
	private JTextField textFieldPcusUsrn;
	private JTable tableCBook;

	public OfficerView(OfficerModel off, AdminModel sysAdmin) {
		ImageIcon logo2 = new ImageIcon(this.getClass().getResource("/logo2.png"));
		ImageIcon bk = new ImageIcon(this.getClass().getResource("/Bookings.png"));
		//ImageIcon can = new ImageIcon(this.getClass().getResource("/cancel.png"));
		ImageIcon chk = new ImageIcon(this.getClass().getResource("/check.png"));
		ImageIcon edt = new ImageIcon(this.getClass().getResource("/edit.png"));
		ImageIcon desgn = new ImageIcon(this.getClass().getResource("/design2.png"));
		String Fname = off.getlName();
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Proxima Nova A", Font.PLAIN, 13));
		frame.setVisible(true);
		frame.getContentPane().setForeground(new Color(240, 255, 240));
		frame.setForeground(new Color(25, 25, 112));
		frame.setBackground(new Color(38, 86, 136));
		frame.getContentPane().setBackground(new Color(165, 157, 175));
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
		welcomePanel.setBackground(new Color(165, 157, 175));
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
/*
 * View Customer booking details panel 
 */
		JPanel customerBookDetailsPanel = new JPanel();
		customerBookDetailsPanel.setLayout(null);
		customerBookDetailsPanel.setBackground(new Color(165, 157, 175));
		customerBookDetailsPanel.setBounds(0, 0, 530, 270);
		

		textFieldPcusUsrn = new JTextField();
		textFieldPcusUsrn.setBounds(200, 20, 130, 26);
		textFieldPcusUsrn.setFont(new Font("Proxima Nova A", Font.PLAIN, 10));
		customerBookDetailsPanel.add(textFieldPcusUsrn);

		JLabel CustUsrn = new JLabel("Enter Customer Username:");
		CustUsrn.setForeground(Color.WHITE);
		CustUsrn.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		CustUsrn.setBounds(74, 25, 126, 16);
		customerBookDetailsPanel.add(CustUsrn);

		

		JLabel lblMsgCustUsr = new JLabel();
		lblMsgCustUsr.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		lblMsgCustUsr.setBounds(116, 58, 130, 26);
		customerBookDetailsPanel.add(lblMsgCustUsr);

		JLabel lblCustBook = new JLabel("CUSTOMER BOOKINGS");
		lblCustBook.setForeground(Color.BLACK);
		lblCustBook.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		lblCustBook.setBounds(411, 6, 113, 16);
		customerBookDetailsPanel.add(lblCustBook);

		JScrollPane scrollPaneCBook = new JScrollPane();
		scrollPaneCBook.setBounds(0, 126, 530, 112);
		customerBookDetailsPanel.add(scrollPaneCBook);

		tableCBook = new JTable();
		tableCBook.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		tableCBook.setBackground(new Color(165, 157, 175));
		tableCBook.getTableHeader().setReorderingAllowed(false);
		scrollPaneCBook.setViewportView(tableCBook);
		
		
		JButton btnResetC = new JButton("Reset");
		btnResetC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPcusUsrn.setText("");
				lblMsgCustUsr.setText("");
				
			}
		});
		btnResetC.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnResetC.setBounds(17, 238, 30, 15);
		customerBookDetailsPanel.add(btnResetC);
		
		
		
		
		JButton btnPViewCustD = new JButton("View Details");
		btnPViewCustD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] columnB = { "Username", "Space Number", "Expiry Time   ", "License Plate" };
				modelB= new DefaultTableModel();
				modelB.setColumnIdentifiers(columnB);
				String usn = textFieldPcusUsrn.getText();
				lblMsgCustUsr.setText("");
				
				try {
				
				
				CustomerModel c= new CustomerModel(usn,"","","","");
				if(c.isInDb()) {
				ArrayList<String[]> arr= c.getDetails();
					for (String [] str : arr) {
						
						modelB.addRow(str);
					}
					
					
					
				}
				else {
					lblMsgCustUsr.setForeground(new Color(237, 28, 7));
					lblMsgCustUsr.setText("Username not found!");
				}
				
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				tableCBook.setModel(modelB);
				tableCBook.setEnabled(false);
				
				
			}
		});
		btnPViewCustD.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnPViewCustD.setBounds(258, 59, 75, 29);
		customerBookDetailsPanel.add(btnPViewCustD);
		layeredPane.add(customerBookDetailsPanel);
		/*
		 * View Purchased spaces panel
		 */
		JPanel purchasedSpacePanel = new JPanel();
		purchasedSpacePanel.setLayout(null);
		purchasedSpacePanel.setBackground(new Color(165, 157, 175));
		purchasedSpacePanel.setBounds(0, 0, 530, 270);

		JScrollPane scrollPanePspaces = new JScrollPane();
		scrollPanePspaces.setBackground(new Color(165, 157, 175));
		scrollPanePspaces.setBounds(0, 30, 530, 240);
		purchasedSpacePanel.add(scrollPanePspaces);

		tableDetails = new JTable();
		tableDetails.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		tableDetails.setBackground(new Color(165, 157, 175));
		tableDetails.getTableHeader().setReorderingAllowed(false);
		scrollPaneCBook.setViewportView(tableCBook);
		
		purchasedSpacePanel.add(tableDetails);
		
		scrollPanePspaces.setViewportView(tableDetails);
	

		JLabel lblPspaces = new JLabel("ACTIVE PURCHASED SPACES");
		lblPspaces.setForeground(Color.BLACK);
		lblPspaces.setFont(new Font("Proxima Nova A", Font.PLAIN, 11));
		lblPspaces.setBounds(6, 2, 142, 16);
		purchasedSpacePanel.add(lblPspaces);
		layeredPane.add(purchasedSpacePanel);


		
		/*
		 * Frame Buttons and labels for logo
		 */

		JButton btnCustBook = new JButton();
		btnCustBook.setText("<html>CUSTOMER<br />BOOKINGS</html>");
		btnCustBook.setHorizontalAlignment(SwingConstants.LEFT);

		btnCustBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPane.removeAll();
				layeredPane.add(customerBookDetailsPanel);
				layeredPane.repaint();
				layeredPane.validate();
			}
		});

		btnCustBook.setOpaque(true);
		btnCustBook.setForeground(Color.WHITE);
		btnCustBook.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnCustBook.setBorderPainted(false);
		btnCustBook.setBackground(Color.BLACK);
		btnCustBook.setIcon(bk);
		btnCustBook.setBounds(585, 115, 100, 30);
		frame.getContentPane().add(btnCustBook);

		JButton btnPSpace = new JButton();
		btnPSpace.setText("<html>PURCHASED<br />SPACES</html>");
		btnPSpace.setHorizontalAlignment(SwingConstants.LEFT);
		btnPSpace.addActionListener(new ActionListener() {
			/*
			 * view purchased spaces action listener to initialize table
			 */
			public void actionPerformed(ActionEvent e) {
				modelP = new DefaultTableModel();
				Object[] column = { "Park Spot ID", "Purchase Date/Time"};
				modelP.setColumnIdentifiers(column);
				try {
				ArrayList<String[]> arr= off.getPaidList();
				for (String [] str : arr) {
					modelP.addRow(str);
				}
				tableDetails.setModel(modelP);
				tableDetails.setEnabled(false);
				
				layeredPane.removeAll();
				layeredPane.add(purchasedSpacePanel);
				layeredPane.repaint();
				layeredPane.validate();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPSpace.setOpaque(true);
		btnPSpace.setForeground(Color.WHITE);
		btnPSpace.setFont(new Font("Proxima Nova A", Font.PLAIN, 8));
		btnPSpace.setBorderPainted(false);
		btnPSpace.setBackground(Color.BLACK);
		btnPSpace.setIcon(chk);
		btnPSpace.setBounds(585, 156, 104, 30);
		frame.getContentPane().add(btnPSpace);

		JButton btnManageParking = new JButton();
		btnManageParking.setText("<html>MANAGE<br />PARKING</html>");
		btnManageParking.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageParking.setFocusable(false);
		btnManageParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
				  //Change to officer profile page
				   ov = new OfficerManageView(off,sysAdmin);

			}
		});
		btnManageParking.setOpaque(true);
		btnManageParking.setForeground(Color.WHITE);
		btnManageParking.setFont(new Font("Proxima Nova A", Font.PLAIN, 9));
		btnManageParking.setBorderPainted(false);
		btnManageParking.setBackground(Color.BLACK);
		btnManageParking.setIcon(edt);
		btnManageParking.setBounds(585, 74, 95, 30);
		frame.getContentPane().add(btnManageParking);

		

		JLabel lblNewLabel_1 = new JLabel("OFFICER PROFILE");
		lblNewLabel_1.setFont(new Font("Lao MN", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(1, 0, 160, 14);
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
