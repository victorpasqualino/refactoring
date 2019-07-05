package com.celfocus.training.controllers;

import java.util.ArrayList;

import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FrontendRequestTest extends TestCase {

	FrontendRequest request = new FrontendRequest();

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public FrontendRequestTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FrontendRequestTest.class);
	}

	/**
	 * Test
	 * 
	 * @throws Exception
	 */
	public void testApp() throws Exception {

		User user = request.upsertUser("user01", "03/04/1992");
		assertNotNull(user);

		user = request.upsertUser("user01", "06/14/1992");
		assertNotNull(user);
		// assertTrue(!user.getBirthDate().equals(user2.getBirthDate()));

		request.addItemToShoppingCart("user01", "item1", 1);
		request.addItemToShoppingCart("user01", "item2", 2);

		User user2 = request.upsertUser("user02", "02/06/1992");
		assertNotNull(user2);

		request.addItemToShoppingCart("user02", "item3", 3);

		try {
			request.addItemToShoppingCart("user03", "item3", 3);
			fail("Exception should be thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
		user = request.upsertUser("user01", "03/04/1992");

		String htmlUser = request.getFrontendUser("html", user);
		assertNotNull(htmlUser);
		assertTrue(htmlUser.contains(user.getName()));

		String xmlUser = request.getFrontendUser("xml", user);
		assertNotNull(xmlUser);
		assertTrue(xmlUser.contains(user.getName()));

		String htmlCart = request.getFrontendShoppingCart("html", new ShoppingCart(user, new ArrayList<>()));
		String xmlCart = request.getFrontendShoppingCart("xml", new ShoppingCart(user, new ArrayList<>()));

		String htmlItem = request.getFrontendItem("html", new ItemInfo("item02", 2.0));
		String xmlItem = request.getFrontendItem("xml", new ItemInfo("item03", 3.0));

	}
}
