package com.expressparking;


import java.awt.EventQueue;





public class SystemRunner {

	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminModel sysAdmin = AdminModel.getInstance();
					LoginSignUpView sys = new LoginSignUpView(sysAdmin);
					sys.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
