package com.revature.battleship.tests;

import static org.junit.Assert.*;
import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.pojos.OracleConnection;

public class OracleConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Connection conn = OracleConnection.getOracleConnection();
		assertNotNull(conn);
	}

}
