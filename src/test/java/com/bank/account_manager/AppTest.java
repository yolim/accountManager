package com.bank.account_manager;

import com.bank.account_manager.controller.HomeController;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	@org.junit.Test
	public void testApp() {
		HomeController hc = new HomeController();
		String result = hc.home();
		assertEquals(result, "This is accountManager! Access with localhost:8080/index.html");
	}
}
