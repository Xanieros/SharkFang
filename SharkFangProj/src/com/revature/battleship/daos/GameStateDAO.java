package com.revature.battleship.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Record;

import oracle.jdbc.internal.OracleTypes;

public class GameStateDAO implements GameStateInterface{
	private Connection conn = OracleConnection.getOracleConnection();
	
	@Override
	public int startGame(int p1Id, int p2Id, String p1Board, String p2Board, int rowLength) {
		int output = -1;
		
		try
		{
			CallableStatement cs = conn.prepareCall("call START_GAME(?,?,?,?,?,?)");
			cs.setInt(1, p1Id);
			cs.setInt(2, p2Id);
			cs.setString(3, p1Board);
			cs.setString(4, p2Board);
			cs.setInt(5, rowLength);
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.executeQuery();
			output = cs.getInt(6);			
		}
		catch(Exception e)
		{
			
		}
		System.out.println("Game ID " + output);
		return output;
	}

	@Override
	public GameState loadGame(int gid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record endGame(int gid, int winner) {
		Record record = new Record();
		
		try
		{
			CallableStatement cs = conn.prepareCall("call GET_PID_FROM_GS(?,?)");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveGame(int gid, String p1Board, String p2Board) {
		try
		{
			CallableStatement cs = conn.prepareCall("call UPDATE_GAME(?,?,?)");
			cs.setInt(1, gid);
			cs.setString(2, p1Board);
			cs.setString(3, p2Board);
			
			cs.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
