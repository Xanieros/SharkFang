package com.revature.battleship.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.daos.PlayerDAO;
import com.revature.battleship.pojos.Player;

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
		assertEquals("test", PlayerDAO.getUsername(1001));
	}
	
	@Test
	public void testGetPassword()
	{
		String expectedPassword = "p4ssw0rd";
		String actualPassword = pDAO.getPassword(1001);
		assertEquals(expectedPassword, actualPassword);
		
		actualPassword = pDAO.getPassword(-1);
		assertNull(actualPassword);
	}

	@Test
	public void testCreateNewPlayer()
	{
		String expected = "testing";
		Player actual = PlayerDAO.createNewPlayer("testing", "testing", "test", "test", "test@test.com", null);
		System.out.println(actual.getUname());
		assertTrue(expected.equals(actual.getUname()));
	}
}
