package com.expressparking.test;




import org.junit.Before;
import org.junit.Test;

import com.expressparking.AdminModel;
import com.expressparking.OfficerModel;

import org.junit.Assert;



public class OfficerModelTest {
	private OfficerModel exOff,testOff2;
	private AdminModel sysAdmin;
	@Before
	public void setUp() throws Exception {
		sysAdmin= AdminModel.getInstance();
		sysAdmin.addPEO("OffTest", "Officer", "JUnit", "JTest@java.com", "Junit102");
		exOff= new OfficerModel("TheSaint","","","","saint80");
		testOff2 = new OfficerModel("OffTest","","","","Junit102");
	}

	
	@Test
	public void testAuthenticate() throws Exception {
		Assert.assertTrue(exOff.authenticate("TheSaint", "saint80"));
		Assert.assertTrue(testOff2.authenticate("OffTest", "Junit102"));
		Assert.assertEquals(false,testOff2.authenticate("OffTest", "saint80"));
	}

	@Test
	public void testHasPaid() throws Exception {
		/*
		 * Parking space NW005 currently in DB
		 */
		Assert.assertTrue(exOff.hasPaid("NW005"));
		Assert.assertTrue(testOff2.hasPaid("NW005"));
	}

	@Test
	public void testAddPkSpotHasSpot()throws Exception {
		exOff.addPkSpot("NW129");
		testOff2.addPkSpot("NW128");
		Assert.assertTrue(exOff.pDBhasSpot("NW128"));
		Assert.assertTrue(testOff2.pDBhasSpot("NW129"));
		
	}

	@Test
	public void testRemPkSpotHasSpot() throws Exception{
		
		exOff.remPkSpot("NW129");
		testOff2.remPkSpot("NW128");
		Assert.assertFalse(exOff.pDBhasSpot("NW128"));
		Assert.assertFalse(testOff2.pDBhasSpot("NW129"));
	}

	

}
