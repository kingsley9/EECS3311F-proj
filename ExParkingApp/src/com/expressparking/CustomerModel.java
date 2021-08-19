package com.expressparking;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CustomerModel implements User {
	/*
	 * Fields
	 *
	 */

	private File cusPkDB = new File("DB/CusPkSptDB.csv");
	private File cusDB = new File("DB/CustomerDB.csv");
	private File pkSpDB = new File("DB/ParkSpotDB.csv");
	private File pyReqDB = new File("DB/PayReq.csv");
	private String cusDBpath = cusDB.getAbsolutePath();
	private String cBookDBpath = cusPkDB.getAbsolutePath();
	private String pkSpotDBpath = pkSpDB.getAbsolutePath();
	private String payReqDB = pyReqDB.getAbsolutePath();
	public String uName;
	public String fName;
	public String lName;
	public String eml;
	private String pass;
	private boolean inDb;

	/*
	 * New Customer Constructor
	 *
	 */

	public CustomerModel(String usrName, String fname, String lname, String email, String pw) throws Exception {
		super();
		// Line counter for customer Bookings DB
		int cntP = 0;
		// Build reader object and read headers
		CsvReader readerPk = new CsvReader(cBookDBpath);
		readerPk.readHeaders();
		// Get file line count
		while (readerPk.readRecord()) {
			cntP++;
		}
		// If DB empty the write headers
		if (cntP < 1) {
			CsvWriter writerP;

			// Add headers from the top
			writerP = new CsvWriter(new FileWriter(cBookDBpath, false), ',');
			// User name,Space Number,Expiry Time,License,hrs
			writerP.write("Username");
			writerP.write("Space Number");
			writerP.write("Expiry Time");
			writerP.write("License");
			writerP.write("hrs");
			writerP.endRecord();
			// close writer
			writerP.close();
		}

		// Line counter for customer login DB
		int cnt = 0;
		// Build reader instance using Customer DB path
		CsvReader reader = new CsvReader(cusDBpath);
		reader.readHeaders();
		// boolean flag to check for existing user name
		boolean bingo = false;
		while (reader.readRecord()) {
			cnt++;
			if ((reader.get("Username").equals(usrName))) {
				bingo = true;
				this.uName = reader.get("Username");
				this.fName = reader.get("FirstName");
				this.lName = reader.get("LastName");
				this.eml = reader.get("Email");
				this.pass = reader.get("Password");
				this.inDb = bingo;

			}

		}
		// If DB empty the write headers
		if (cnt < 1) {
			CsvWriter writerH;

			// Add headers from the top
			writerH = new CsvWriter(new FileWriter(cusDBpath, false), ',');
			// User name,FirstName,LastName,Email,Password
			writerH.write("Username");
			writerH.write("FirstName");
			writerH.write("LastName");
			writerH.write("Email");
			writerH.write("Password");
			writerH.endRecord();
			// close writer
			writerH.close();
		}

		if (!bingo) {
			this.uName = usrName;
			this.fName = fname;
			this.lName = lname;
			this.eml = email;
			this.pass = pw;
			this.inDb = bingo;

		}
	}

	// no argument constructor
	public CustomerModel() {
		super();
	}

	/*
	 * Method to register customer into DB
	 */
	public void register() throws Exception {

		// New Csvwriter object
		CsvWriter writerR;
		// Try catch block
		try {

			// add new records from the bottom
			writerR = new CsvWriter(new FileWriter(cusDBpath, true), ',');

			// String array to store customer details
			String[] details = this.toString().split(",");
			writerR.writeRecord(details);
			writerR.endRecord();

			// close writer
			writerR.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/*
	 * Method to authenticate customer user name and password and return boolean
	 */
	public boolean authenticate(String usn, String psw) throws Exception {
		// Build reader instance
		CsvReader reader = new CsvReader(cusDBpath);
		reader.readHeaders();

		// Loop through database until null line
		while (reader.readRecord()) {

			if ((reader.get("Username").equals(usn))) {
				if (reader.get("Password").equals(psw)) {
					// if correct combination found return true
					return true;
				}
			}
		}

		// return false if unsuccessful
		return false;
	}

	/*
	 * Method that returns number of bookings a customer has in the database
	 */
	public int getBkCount() throws Exception {
		// counter
		int c = 0;
		// Build reader instance
		CsvReader reader = new CsvReader(cBookDBpath);
		reader.readHeaders();

		// Loop through database until null line
		while (reader.readRecord()) {
			if ((reader.get("Username").equals(this.getuName()))) {
				// if username found add to counter
				c++;
			}
		}
		// return counter
		return c;
	}

	/*
	 * Method to add parking spot to the booking list of this customer and returns a
	 * boolean
	 */
	public boolean addBooking(String p, String ex, String lic, String hrs) throws Exception {
		String [] bk= {this.getuName(),p, ex,lic,hrs};
		// get booking list count
		int c = this.getBkCount();
		// if customer has less than 3 bookings then add a new booking one and return
		// true
		if (c < 3) {
			// New Csvwriter object
			CsvWriter writerR;
			// Try catch block
			try {
				// add new records from the bottom
				writerR = new CsvWriter(new FileWriter(cBookDBpath, true), ',');
				// String array to store officer details
				writerR.writeRecord(bk);
				
				// close writer
				writerR.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

			// return true after writing successfully
			removeSpacefomDB(p);
			return true;

		}
		// return false if bookings are full
		else {
			return false;
		}
	}

	/*
	 * Method to remove parking spot from the booking list of this customer
	 */
	public void removeBooking(String p) throws Exception {
		// ArrayList of string to store the ODB records
		ArrayList<String[]> dbToArray = new ArrayList<String[]>();
		// Build the reader and read headers
		CsvReader reader = new CsvReader(cBookDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {

			if (!reader.get("Space Number").equals(p)) {
				// Add reader records to array list
				dbToArray.add(reader.getValues());
			}

		}
		try {
			// new writer object from the top
			CsvWriter writer = new CsvWriter(new FileWriter(cBookDBpath, false), ',');
			// Park Spot ID, Location
			writer.write("Username");
			writer.write("Space Number");
			writer.write("Expiry Time");
			writer.write("License");
			writer.write("hrs");
			writer.endRecord();
			// String array to store customer detials
			for (String[] s : dbToArray) {
				// rewrite records
				writer.writeRecord(s);
				
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		this.addSpacetoDB(p);

	}

	/*
	 * private helper method to add a space back to the parking space DB after its
	 * been removed from a customers booking list
	 */
	private void addSpacetoDB(String p) throws Exception {
		// call helper method to get db as an arrayList
		ArrayList<String> db = pkSpArrayList();
		// add park space number to arrayList
		db.add(p);
		// writer
		CsvWriter writer;
		// Try catch block
		try {

			writer = new CsvWriter(new FileWriter(pkSpotDBpath, false), ',');
			// ParkSPotID
			writer.write("ParkSpotID");
			writer.endRecord();
			// looop through array list and add new records
			for (String str : db) {
				writer.write(str);
				writer.endRecord();

			}
			// close writer
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * private Helper method to temporarily remove a space from the parking space DB
	 * after its been added to a customers booking list
	 */
	private void removeSpacefomDB(String p) throws Exception {
		// call helper method to get db as an arrayList
		ArrayList<String> db = pkSpArrayList();
		// remove park space number from arrayList
		db.remove(p);
		// writer
		CsvWriter writer;
		// Try catch block
		try {

			writer = new CsvWriter(new FileWriter(pkSpotDBpath, false), ',');
			// ParkSPotID
			writer.write("ParkSpotID");
			writer.endRecord();
			// looop through array list and add new records
			for (String str : db) {
				writer.write(str);
				writer.endRecord();

			}
			// close writer
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * Method that returns customer booking with details as ArrayList of String
	 * arrays
	 */
	protected ArrayList<String[]> getDetails() throws Exception {
		// ArrayList of string to store the customer booking records
		ArrayList<String[]> dbToArray = new ArrayList<String[]>();
		// Build the reader and read headers
		CsvReader reader = new CsvReader(cBookDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			if (reader.get("Username").equals(this.getuName())) {
				// Add reader records to array list as string array
				dbToArray.add(reader.getValues());
			}
		}
		return dbToArray;
	}

	/*
	 * Method that returns customer bookings as string array of space numbers
	 */

	public String[] myBkArray() throws Exception {
		// Array list to store db temporarily
		ArrayList<String> dbToArray = new ArrayList<String>();
		// String array to store space numbers
		String[] arr;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(cBookDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// If username Found
			if (reader.get("Username").equals(this.getuName())) {
				// store element in string and add it to array
				String s = reader.get("Space Number");
				dbToArray.add(s);
			}

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
	 * Private Helper method that returns available parking spots as array list of
	 * strings
	 */
	private ArrayList<String> pkSpArrayList() throws Exception {
		ArrayList<String> dbToArray = new ArrayList<String>();
		// Build the reader and read headers
		CsvReader reader = new CsvReader(pkSpotDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// store element in string and add it to array
			String s = reader.get("ParkSpotID");
			dbToArray.add(s);
		}

		return dbToArray;
	}

	/*
	 * Method to get available parking spots as String array
	 */
	public String[] pkSpArray() throws Exception {
		ArrayList<String> dbToArray = new ArrayList<String>();
		// String array to store officer ID's
		String[] arr;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(pkSpotDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// store element in string and add it to array
			String s = reader.get("ParkSpotID");
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
	 * Method that returns
	 */

	public int getPayReqs() throws Exception {
		// ArrayList<String> dbToArray= new ArrayList<String>();
		int cntP = 0;
		CsvReader readerPk = new CsvReader(payReqDB);
		readerPk.readHeaders();
		while (readerPk.readRecord()) {
			cntP++;
		}
		return cntP;
	}

	protected int getHours(String p) throws Exception {
		// int to store number of hours of booking
		int hrs = 0;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(cBookDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			if (reader.get("Space Number").equals(p)) {
				hrs = Integer.parseInt(reader.get("hrs"));
			}
		}
		return hrs;

	}

	/*
	 * Method to add parking spot to the payment request database
	 */
	public void addPayReq(String p, String tot) throws Exception {
		String [] pReq= {this.getuName(),p,tot};
		// Line counter for payment request DB
		int cntP = 0;
		CsvReader readerPk = new CsvReader(payReqDB);
		readerPk.readHeaders();
		// Get line count
		while (readerPk.readRecord()) {
			cntP++;
		}
		// Write headers and details from top if database is empty
		if (cntP < 1) {
			CsvWriter writerP;

			// Add headers from the top
			writerP = new CsvWriter(new FileWriter(payReqDB, false), ',');
			// User name,Space Number,Expiry Time,License,hrs
			writerP.write("Username");
			writerP.write("Space Number");
			writerP.write("Total");
			writerP.endRecord();
			
			writerP.writeRecord(pReq);
			
			// close writer
			writerP.close();

		}
		// Write details from bottom if database isnt empty
		else {
			// New Csvwriter object
			CsvWriter writerR;
			// Try catch block
			try {
				// add new records from the bottom
				writerR = new CsvWriter(new FileWriter(payReqDB, true), ',');
				writerR.writeRecord(pReq);
				
				// close writer
				writerR.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		this.removeBooking(p);

	}

	// Getters and Setters

	protected boolean isInDb() {
		return this.inDb;
	}

	protected void setInDb(boolean inDb) {
		this.inDb = inDb;
	}

	@Override
	public String getuName() {
		return this.uName;
	}

	@Override
	public String getEml() {
		return this.eml;
	}

	@Override
	public void setEml(String newEml) {
		this.eml = newEml;
	}

	@Override
	public String getfName() {
		return this.fName;
	}

	@Override
	public String getlName() {
		return this.lName;
	}

	@Override
	public String getPass() {
		return this.pass;
	}

	@Override
	public void setfName(String fname) {
		this.fName = fname;

	}

	@Override
	public void setlName(String lname) {
		this.lName = lname;

	}

	@Override
	public void setPass(String Pass) {

		this.pass = Pass;
	}

	// Override toString to store customer details as comma separated values
	@Override
	public String toString() {
		return this.uName + "," + this.fName + "," + this.lName + "," + this.eml + "," + this.pass;
	}

}
