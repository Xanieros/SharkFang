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
			
			ResultSet rs = cs.executeQuery();
			
			if(rs.next())
				output = cs.getInt("GS_ID");
		}
		catch(Exception e)
		{
			
		}
		
		return output;
	}

	@Override
	public GameState loadGame(int gid) {
		// TODO Auto-generated method stub
		return null;
	}

	//WHEN MULTIPLAYER IS IMPLEMENTED CHECK RECORD FOR ACCURACY, MAKE SURE THEY ARE NOT BEING UPDATED TWICE
	@Override
	public Record endGame(int gid, int winner) {
		Record record = new Record();
		
		try
		{
			CallableStatement cs = conn.prepareCall("call END_GAME(?,?,?)");
			cs.setInt(1, gid);
			cs.setInt(2, winner);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			ResultSet rs = (ResultSet)cs.executeQuery();
			if(rs.next())
			{
				if(winner == 1)
				{
					cs = conn.prepareCall("call ADD_WIN(?)");
					cs.setInt(1,rs.getInt("P1_ID"));
					
					cs.executeQuery();
					
					cs = conn.prepareCall("call ADD_LOSS(?)");
					cs.setInt(1,rs.getInt("P2_ID"));
					
					cs.executeQuery();
				}
				else if(winner == 2)
				{
					cs = conn.prepareCall("call ADD_WIN(?)");
					cs.setInt(1,rs.getInt("P2_ID"));
					
					cs.executeQuery();
					
					cs = conn.prepareCall("call ADD_LOSS(?)");
					cs.setInt(1,rs.getInt("P1_ID"));
					
					cs.executeQuery();
				}

				cs = conn.prepareCall("call GET_RECORD(?,?)");
				cs.setInt(1, rs.getInt("P1_ID"));
				cs.registerOutParameter(2, OracleTypes.CURSOR);
				
				ResultSet rs2 = (ResultSet)cs.executeQuery();
				if(rs2.next())
				{
					record.setLosses(rs2.getInt("LOSSES"));
					record.setSid(rs2.getInt("R_ID"));
					record.setUid(rs2.getInt("U_ID"));
					record.setWins(rs2.getInt("WINS"));
				}
			}
			else
			{
				record = null;
			}
			cs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return record;
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
