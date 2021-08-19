package com.expressparking;

import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class OfficerModel implements User {

	public String id;
	private File oDB = new File("DB/OfficerDB.csv");
	private File psDB = new File("DB/PaidSpotDB.csv");
	private File pkDB = new File("DB/ParkSpotDB.csv");
	private String oDBpath = oDB.getAbsolutePath();
	private String pSDBpath = psDB.getAbsolutePath();
	private String pkDBpath = pkDB.getAbsolutePath();
	public String uName;
	public String fName;
	public String lName;
	public String email;
	protected String pass;
	protected boolean inDb;

	public OfficerModel(String Id, String fName, String lName, String eml, String pw) throws Exception {
		// Build reader instance using Officer DB path
		int cntP = 0;
		CsvReader reader = new CsvReader(oDBpath);
		reader.readHeaders();
		// boolean flag to check for existing OfficerID
		boolean bingo = false;
		while (reader.readRecord()) {

			if ((reader.get("OfficerID").equals(Id))) {
				bingo = true;
				this.uName = reader.get("OfficerID");
				this.fName = reader.get("FirstName");
				this.lName = reader.get("LastName");
				this.email = reader.get("Email");
				this.pass = reader.get("Password");
				this.inDb = bingo;
			}

		}
		CsvReader readerPk = new CsvReader(pkDBpath);
		readerPk.readHeaders();
		while (readerPk.readRecord()) {
			cntP++;
		}

		if (cntP < 1) {
			CsvWriter writerP;

			// Add headers from the top
			writerP = new CsvWriter(new FileWriter(pkDBpath, false), ',');
			// User name,FirstName,LastName,Email,Password
			writerP.write("ParkSpotID");
			writerP.endRecord();
			// close writer
			writerP.close();
		}

	}

	/*
	 * Method to authenticate Officer user name and password
	 */
	public boolean authenticate(String usn, String psw) throws Exception {
		// Build reader instance
		CsvReader reader = new CsvReader(oDBpath);
		reader.readHeaders();
		// Loop through database until null line
		while (reader.readRecord()) {
			if ((reader.get("OfficerID").equals(usn))) {
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
 * Helper Method that returns customer details As ArrayList of String arrays
 */
	protected ArrayList<String[]> getBookings(CustomerModel c) throws Exception {

		return c.getDetails();
	}
/*
 * Helper method that returns the paid list Database
 */
	protected ArrayList<String[]> getPaidList() throws Exception {
		// ArrayList of string to store the paid space DB records
		ArrayList<String[]> dbToArray = new ArrayList<String[]>();
		// Build the reader and read headers
		CsvReader reader = new CsvReader(pSDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			// Add reader records to array list as string array
			dbToArray.add(reader.getValues());
		}
		return dbToArray;
	}
/*
 * Method that returns true only if the space number has been paid for
 */
	public boolean hasPaid(String sn) throws Exception {

		// Build the reader and read headers
		CsvReader reader = new CsvReader(pSDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {
			if (reader.get("Space Number").equals(sn)) {
				return true;
			}

		}
		return false;
	}
/*
 * Method to add a new parking spot to the database
 */
	public void addPkSpot(String p) throws Exception {

		// New Csvwriter object
		CsvWriter writerR;
		// Try catch block
		try {
			// add new records from the bottom
			writerR = new CsvWriter(new FileWriter(pkDBpath, true), ',');
			//write record and end
			writerR.write(p);
			writerR.endRecord();
			// close writer
			writerR.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

/*
 * Method to return String array of available parking spots
 */
	public String[] pkSpArray() throws Exception {
		ArrayList<String> dbToArray = new ArrayList<String>();
		// String array to store parkSpot ID's
		String[] arr;
		// Build the reader and read headers
		CsvReader reader = new CsvReader(pkDBpath);
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
	 * Method to remove parking spot from database
	 */
	public void remPkSpot(String p) throws Exception {
		// ArrayList of string to store the park space database records
		ArrayList<String> dbToArray = new ArrayList<String>();
		// Build the reader and read headers
		CsvReader reader = new CsvReader(pkDBpath);
		reader.readHeaders();

		// loop through each record of the file
		while (reader.readRecord()) {

			// Add reader records to array list
			dbToArray.add(reader.get("ParkSpotID"));

		}
		// remove the String from the array list
		dbToArray.remove(p);
		try {
			// new writer object from the top
			CsvWriter writer = new CsvWriter(new FileWriter(pkDBpath, false), ',');
			// Park Spot ID, Location
			writer.write("ParkSpotID");
			writer.endRecord();
			
			for (String s : dbToArray) {
				// rewrite records
				writer.write(s);
				writer.endRecord();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * Method to check if park space exists in database by spotID
	 */
	public boolean pDBhasSpot(String p) throws Exception {
		// Build reader instance
		CsvReader reader = new CsvReader(pkDBpath);
		reader.readHeaders();

		// Loop through database until null line
		while (reader.readRecord()) {

			if ((reader.get("ParkSpotID").equals(p))) {

				// if correct combination found return true
				return true;

			}
		}

		// return false if unsuccessful
		return false;
	}

	@Override
	public String getEml() {
		return this.email;
	}

	@Override
	public void setEml(String newEml) {
		this.email = newEml;
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

	@Override
	public String toString() {
		return this.uName + "," + this.fName + "," + this.lName + "," + this.email + "," + this.pass;
	}

	@Override
	public String getuName() {
		// TODO Auto-generated method stub
		return this.uName;
	}

}
