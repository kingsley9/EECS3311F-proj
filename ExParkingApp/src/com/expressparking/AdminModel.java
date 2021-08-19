package com.expressparking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/*
 * Singleton Admin class
 */
public class AdminModel implements User {
	/*
	 * Fields
	 */
	private static AdminModel AdminInstance = null;
	public String uName;
	private String pass;
	private File oDB = new File("DB/OfficerDB.csv");
	private File psDB = new File("DB/PaidSpotDB.csv");
	private File pyReqDB= new File ("DB/PayReq.csv");
	private String pSDBpath = psDB.getAbsolutePath();
	private String oDBpath = oDB.getAbsolutePath();
	private String payReqDB = pyReqDB.getAbsolutePath();

	/*
	 * private constructor to initialize admin instance with master login
	 * 
	 */
	private AdminModel() throws Exception{
		//Master Login
		this.uName = "Admin808";
		this.setPass("ExParking9551");
		//Line counter for DB
		int cntO=0;
		//Build reader Object
		CsvReader readerOff = new CsvReader(oDBpath); 
		//Read headers of file
		readerOff.readHeaders();
       
		//Get file line count
		while(readerOff.readRecord()){ 
			cntO++;
		}
		
		// If DB empty the write headers
		if(cntO<1) {
			CsvWriter writerH;
			
			//Add headers from the top
			writerH = new CsvWriter(new FileWriter(oDBpath, false), ',');
			//User name,FirstName,LastName,Email,Password
			writerH.write("OfficerID");
			writerH.write("FirstName");
	    	writerH.write("LastName");
			writerH.write("Email");
			writerH.write("Password");
			writerH.endRecord();
			//close writer
			writerH.close();
			}
		
	}

	/*
	 * Get instance method to instanstiate singleton object if not null
	 */

	public static AdminModel getInstance() throws Exception{
		if (AdminInstance == null)
			AdminInstance = new AdminModel();
		return AdminInstance;
	}

	/*
	 * Method to add new PEO to database
	 */
	public void addPEO(String Id, String fname, String lname, String email, String pw) throws Exception {

		// New writer object
		CsvWriter writerR;
		// Try catch block
		try {

			// add new records from the bottom
			writerR = new CsvWriter(new FileWriter(oDBpath, true), ',');

			// String array to store officer details
			String[] details = { Id, fname, lname, email, pw };
			//Write details to database
			writerR.writeRecord(details);
			
			// close writer
			writerR.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/*
	 * Boolean Method to authenticate Admin user name and password
	 */
	public boolean authenticate(String usn, String pw) {
		if (usn.equals(this.getuName())) {
			if (pw.equals(this.getPass())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Method to check if PEO exists in database by OfficerID
	 */
	public boolean hasPEO(String usn) throws Exception {
		// Build reader instance
		CsvReader reader = new CsvReader(oDBpath);
		reader.readHeaders();

		// Loop through database until null line
		while (reader.readRecord()) {

			if ((reader.get("OfficerID").equals(usn))) {

				// if OfficerID found return true
				return true;

			}
		}

		// return false if unsuccessful
		return false;
	}

	/*
	 * Method to remove PEO from DB using officer Object
	 */
	public void removePEO(OfficerModel O) throws Exception {
		
		// ArrayList of string to store the ODB records
		ArrayList<String[]> dbToArray = new ArrayList<String[]>();
		// Build the reader and read header
		CsvReader reader = new CsvReader(oDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// Add reader records to array list as string array
			if(!reader.get("OfficerID").equals(O.getuName())) {
			dbToArray.add(reader.getValues());
			}
		}
		
		
		
		// new writer object from the top
		CsvWriter writer = new CsvWriter(new FileWriter(oDBpath, false), ',');
		// ID, Username,FirstName,LastName,Email,Password
		writer.write("OfficerID");
		writer.write("FirstName");
		writer.write("LastName");
		writer.write("Email");
		writer.write("Password");
		writer.endRecord();
		// String array to store officer detials
		for (String[] s : dbToArray) {
			// rewrite records
			writer.writeRecord(s);
			
		}
		writer.close();
	}

	/*
	 * Method to change payment status of customer by adding username and spot
	 * number to paid DB
	 */
	public void setPaid(String p, String time) throws Exception {
		String[] str= {p, time};
		//Line counter for DB
		int cntP=0;
		//Build reader object
		CsvReader readerPk = new CsvReader(pSDBpath); 
		//read headers
		readerPk.readHeaders();
		//count lines in dB
		while(readerPk.readRecord()){ 
			cntP++;
		}
		//If file is empty then write headers then entry from top
		if(cntP<1) {
			CsvWriter writerP;
			
			//Add headers from the top
			writerP = new CsvWriter(new FileWriter(pSDBpath, false), ',');
			writerP.write("Space Number");
			writerP.write("Purchase Time");
			writerP.endRecord();
			
			writerP.writeRecord(str);
		
			
			//close writer
			writerP.close();
			}
		//Else write only entry to bottom
		else {
			// New Csvwriter object
			CsvWriter  writerR;
			// Try catch block
			try {
				// add new records from the bottom
				writerR = new CsvWriter(new FileWriter(pSDBpath, true), ',');			
				// String array to store officer details
				
				writerR.writeRecord(str);
						
				// close writer
				writerR.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	
		
		
		

	}
	/*
	 * Method that returns a String array of Space numbers in payment request database
	 */

	public String[] payReqArray() throws Exception {
		//ArrayList to  store Space ID's
		ArrayList<String> dbToArray = new ArrayList<String>();
		// String array to store Space ID's
		String[] arr;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(payReqDB);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// store element in string and add it to array
			String s = reader.get("Space Number");
			dbToArray.add(s);
		}
		// initialize array
		arr = new String[dbToArray.size()];
		// loop through and assign elements
		for (int i = 0; i < arr.length; i++) {
			arr[i] = dbToArray.get(i);
		}

		return arr;
	}
	/*
	 * Method that returns a String array of Officer ID's in Officer database
	 */
	public String[] offIDAsArray() throws Exception {
		//ArrayList to  store Officer ID's
		ArrayList<String> dbToArray = new ArrayList<String>();
		// String array to store officer ID's
		String[] arr;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(oDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// store element in string and add it to array
			String s = reader.get("OfficerID");
			dbToArray.add(s);
		}
		// initialize array
		arr = new String[dbToArray.size()];
		// loop through and assign elements
		for (int i = 0; i < arr.length; i++) {
			arr[i] = dbToArray.get(i);
		}

		return arr;
	}

	@Override
	public String getuName() {
		// TODO Auto-generated method stub
		return this.uName;
	}

	@Override
	public String getfName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getlName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPass() {
		// TODO Auto-generated method stub
		return this.pass;
	}

	@Override
	public void setfName(String fname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setlName(String lname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEml(String eml) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPass(String Pw) {
		// TODO Auto-generated method stub
		this.pass = Pw;
	}

}
