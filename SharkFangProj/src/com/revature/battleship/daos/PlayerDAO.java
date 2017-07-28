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
			
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(3);
			
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
				throw new Exception("Player not found");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			player = new Player(-1, "default", "default", "default", "default", "default", "default");
		}
		
		return player;
	}
	
}
