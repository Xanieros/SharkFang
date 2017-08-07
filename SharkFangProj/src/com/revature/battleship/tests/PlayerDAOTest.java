package com.revature.battleship.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.daos.PlayerDAO;

public class PlayerDAOTest {
	PlayerDAO pDAO;
	@Before
	public void setUp() throws Exception {
		pDAO = new PlayerDAO();
	}

	@After
	public void tearDown() throws Exception {
		pDAO = null;
		System.gc();
	}

	@Test
	public void testLogin() {
		assertNotNull(pDAO.login("Xanieros", "p4ssw0rd"));
	}

	@Test
	public void testUpdateInfo() {
		assertNotNull(pDAO.updateInfo(1001, "p4ssw0rd", "Sean", "Barns", "TestString"));
	}

	@Test
	public void testGetUsername() {
		assertEquals("Xanieros", PlayerDAO.getUsername(1001));
	}
	
	@Test
	public void testGetPassword()
	{
		String expectedPassword = "test";
		String actualPassword = pDAO.getPassword(1004);
		assertEquals(expectedPassword, actualPassword);
		
		actualPassword = pDAO.getPassword(-1);
		assertNull(actualPassword);
	}

}
