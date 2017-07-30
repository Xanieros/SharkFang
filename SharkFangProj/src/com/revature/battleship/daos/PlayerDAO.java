package com.revature.battleship.daos;

import java.sql.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Player;

import oracle.jdbc.OracleTypes;

public class PlayerDAO implements PlayerInterface{
	private static final Logger LOGGER = LogManager.getLogger(PlayerDAO.class);
	private Connection conn = OracleConnection.getOracleConnection();
	
	@Override
	public Player login(String username, String password) {
		LOGGER.info("in login");
		Player player = new Player();
		
		try{
			LOGGER.info("calling AUTH(?,?,?)");
			CallableStatement cs = conn.prepareCall("call AUTH(?,?,?)");
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
      
			ResultSet rs = (ResultSet) cs.getObject(3);
			
			if(rs.next())
			{
				LOGGER.info("creating a new player from database");
				int uid = rs.getInt("U_ID");
				String fname = rs.getString("FIRST_NAME");
				String lname = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String profPic = rs.getString("PROFILE_PICTURE");
				
				player = new Player(uid, username, password, fname, lname, email, profPic);
				LOGGER.debug("userID of player: " + player.getUid());
			}
			else{
				LOGGER.info("login failed");
				player = null;
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			player = null;
		}
		return player;
	}
	@Override
	public Player updateInfo(int pid, String pword, String fname, String lname, String profPic) {
		LOGGER.info("in updateInfo");
		Player player = new Player();
		try
		{
			LOGGER.info("calling UPDATE_PLAYER(?,?,?,?,?,?)");
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
				LOGGER.info("creating new player from database");
				player = new Player(rs.getInt("U_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getString("PROFILE_FICTURE"));
				LOGGER.debug("userID of player: " + player.getUid());
			}
			else{
				LOGGER.info("player not found");
				player = null;
			}
				
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			player = null;
		}
		return player;
	}
	
}