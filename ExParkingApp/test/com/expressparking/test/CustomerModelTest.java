package com.expressparking.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.expressparking.CustomerModel;

public class CustomerModelTest {
	private CustomerModel king;
	@Before
	public void setUp() throws Exception {
		  king = new CustomerModel("King808", "king", "Okon", "king@gmail", "lazy808");
	}
	
	@Test
	public void testRegisterAndAuthenticate() throws Exception {
		king.register();
		String Uname =	king.getuName();
		String Pass =king.getPass();
		Assert.assertEquals(true, king.authenticate(Uname, Pass));
	}

	@Test
	public void testAddBooking() throws Exception {
		int i=king.getBkCount();
		king.addBooking("NW140", "12:50", "AJ6783", "3");
		Assert.assertEquals(i+1, king.getBkCount());
		king.removeBooking("NW140");
	}

	@Test
	public void testRemoveBooking() throws Exception {
		king.addBooking("NW140", "12:50", "AJ6783", "3");
		int k=king.getBkCount();
		king.removeBooking("NW140");
		Assert.assertEquals(k-1, king.getBkCount());
	}

	@Test
	public void testAddPayReq() throws Exception {
		int i=king.getPayReqs();
		king.addPayReq("NW140", "20.03");
		Assert.assertEquals(i+1, king.getPayReqs());
	}

}
