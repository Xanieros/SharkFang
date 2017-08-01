package com.revature.battleship.tests;

import static org.junit.Assert.assertEquals;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.daos.RecordDAO;
import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Record;

import oracle.jdbc.internal.OracleTypes;

public class RecordDAOTest {
	Connection conn;
	Record record;
	RecordDAO recordDAO = new RecordDAO();
	@Before
	public void setUp() {
		try{
		conn = OracleConnection.getOracleConnection();
		CallableStatement cs = conn.prepareCall("call GET_RECORD(?,?)");
		cs.setInt(1, 1001);
		cs.registerOutParameter(2, OracleTypes.CURSOR);
		cs.executeQuery();
		ResultSet rs = (ResultSet)cs.getObject(2);
		while(rs.next())
			record = new Record(rs.getInt("R_ID"), rs.getInt("WINS"), rs.getInt("LOSSES"), rs.getInt("U_ID"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddWin() throws Exception{
		Record testRecord = recordDAO.addWin(1001);
		
		assertEquals(testRecord.getWins(), record.getWins() + 1);
	}

	@Test
	public void testAddLoss() 
	{
		
		Record testRecord = recordDAO.addLoss(1001);
		
		assertEquals(testRecord.getLosses(), record.getLosses() + 1);
		}

	@Test
	public void testGetPlayerRecord() throws Exception{
		Record testRecord = new Record();
		CallableStatement cs = conn.prepareCall("call GET_RECORD(?,?)");
		cs.setInt(1, 1001);
		cs.registerOutParameter(2, OracleTypes.CURSOR);
		cs.executeQuery();
		ResultSet rs = (ResultSet)cs.getObject(2);
		while(rs.next())
			testRecord = new Record(rs.getInt("R_ID"), rs.getInt("WINS"), rs.getInt("LOSSES"), rs.getInt("U_ID"));
		assertEquals(testRecord.getRid(), record.getRid());
	}

	@Test
	public void testGetTopRank() throws Exception{
		ArrayList<Record> recordAL = recordDAO.getTopRank(2);
		assertEquals(recordAL.get(0).getRid(), record.getRid());
	}

}
