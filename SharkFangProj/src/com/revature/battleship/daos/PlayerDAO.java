package com.revature.battleship.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Player;

import oracle.jdbc.OracleTypes;

public class PlayerDAO implements PlayerInterface{
	private Connection conn = OracleConnection.getOracleConnection();
	@Override
	public Player login(String username, String password) {
		Player player = new Player();
		
		try{
			CallableStatement cs = conn.prepareCall("call AUTH(?,?,?)");
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			ResultSet rs = (ResultSet)cs.executeQuery();
			
			if(rs.next())
			{
				int uid = rs.getInt("U_ID");
				String fname = rs.getString("FIRST_NAME");
				String lname = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String profPic = rs.getString("PROFILE_PICTURE");
				
				player = new Player(uid, username, password, fname, lname, email, profPic);
			}
			else
				player = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return player;
	}
	@Override
	public Player updateInfo(int pid, String pword, String fname, String lname, String profPic) {
		Player player = new Player();
		try
		{
			CallableStatement cs = conn.prepareCall("call UPDATE_PLAYER(?,?,?,?,?,?)");
			cs.setInt(1, pid);
			cs.setString(2, pword);
			cs.setString(3, fname);
			cs.setString(4, lname);
			cs.setString(5, profPic);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			
			ResultSet rs = (ResultSet)cs.executeQuery();
			if(rs.next())
			{
				player = new Player(rs.getInt("U_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getString("PROFILE_FICTURE"));
			}
			else
				player = null;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return player;
	}
	
}
