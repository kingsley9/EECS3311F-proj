package com.expressparking.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.expressparking.AdminModel;
import com.expressparking.OfficerModel;

public class AdminModelTest {
	private AdminModel sysAdmin ;
	private OfficerModel exOff;
	@Before
	public void setUp() throws Exception {
		sysAdmin= AdminModel.getInstance();
		exOff= new OfficerModel("TheSaint","","","","saint80");
	}

	@Test
	public void testAddPEO() throws Exception {
		sysAdmin.addPEO("JUnit12", "Officer", "JUnit", "JTest@java.com", "Junit102");
		Assert.assertTrue(sysAdmin.hasPEO("JUnit12"));	
	}

	@Test
	public void testAuthenticate() {
		Assert.assertTrue(sysAdmin.authenticate("Admin808", "ExParking9551"));
		
	}

	@Test
	public void testHasPEO() throws Exception{
		Assert.assertTrue(sysAdmin.hasPEO("TheSaint"));
	}

	@Test
	public void testRemovePEO() throws Exception {
		OfficerModel off = new OfficerModel("JUnit12", "", "", "", "");
		sysAdmin.removePEO(off);
	}

	@Test
	public void testSetPaidandOffHasPaid() throws Exception {
		sysAdmin.setPaid("NW140", "23.4");
		Assert.assertTrue(exOff.hasPaid("NW140"));
	}

}
