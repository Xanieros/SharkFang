package com.revature.battleship.daos;

import java.sql.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Player;

import oracle.jdbc.OracleTypes;

public class PlayerDAO implements PlayerInterface{
	private static final Logger LOGGER = LogManager.getLogger(PlayerDAO.class);
	private static Connection conn;
	
	@Override
	public Player login(String username, String password) {
		LOGGER.info("in login");
		Player player = new Player();
		conn = OracleConnection.getOracleConnection();
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
				
				player = new Player(uid, username, password, email, fname, lname, profPic);
				LOGGER.debug("userID of player: " + player.getUid());
			}
			else{
				LOGGER.info("login failed");
				player = null;
			}
			conn.close();
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
		conn = OracleConnection.getOracleConnection();
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
			cs.executeQuery();
			ResultSet rs = (ResultSet)cs.getObject(6);
			if(rs.next())
			{
				LOGGER.info("creating new player from database");
				player = new Player(rs.getInt("U_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),  rs.getString("EMAIL"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("PROFILE_PICTURE"));
				LOGGER.debug("userID of player: " + player.getUid());
			}
			else{
				LOGGER.info("player not found");
				player = null;
			}
				
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			player = null;
		}
		return player;
	}
	
	public static String getUsername(int uid)
	{
		LOGGER.info("in getUsername");
		String output = "";
		conn = OracleConnection.getOracleConnection();
		try{
			LOGGER.info("calling GET_USERNAME(?,?)");
			CallableStatement cs = conn.prepareCall("call GET_USERNAME(?,?)");
			cs.setInt(1, uid);
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cs.executeQuery();
			
			output = cs.getString(2);
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
	@Override
	public String getPassword(int pid) {
		String password = null;
		conn = OracleConnection.getOracleConnection();
		try {
			String sqlCommand = "call GET_PASSWORD(?,?)";
			CallableStatement getPasswordCallableStmt = conn.prepareCall(sqlCommand);
			getPasswordCallableStmt.setInt(1, pid);
			getPasswordCallableStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			
			getPasswordCallableStmt.executeQuery();
			LOGGER.debug("Getting password");
			password = getPasswordCallableStmt.getString(2);
			conn.close();
		} catch (SQLException sqlE)
		{
			sqlE.printStackTrace();
			LOGGER.fatal("SQLException in getPassword(" + pid + ")");
		}
		return password;
	}
	
	public static Player createNewPlayer(String uname, String pword, String fname, String lname, String email, String profPic)
	{
		LOGGER.info("in createNewPlayer");
		Player newPlayer = new Player();
		conn = OracleConnection.getOracleConnection();
		try
		{
			LOGGER.info("calling CREATE_PLAYER(?,?,?,?,?,?,?,?)");
			CallableStatement cs = conn.prepareCall("call CREATE_PLAYER(?,?,?,?,?,?,?,?)");
			cs.setString(1, uname);
			cs.setString(2, pword);
			cs.setString(3, fname);
			cs.setString(4, lname);
			cs.setString(5, email);
			cs.setString(6, profPic);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, OracleTypes.NUMBER);
			
			cs.executeQuery();
			
			ResultSet rs = (ResultSet)cs.getObject(7);
			if(rs.next())
			{
				newPlayer.setUid(rs.getInt("U_ID"));
				newPlayer.setUname(rs.getString("USERNAME"));
				newPlayer.setPword(rs.getString("PASSWORD"));
				newPlayer.setFname(rs.getString("FIRST_NAME"));
				newPlayer.setLname(rs.getString("LAST_NAME"));
				newPlayer.setEmail(rs.getString("EMAIL"));
				newPlayer.setProfPic(rs.getString("PROFILE_PICTURE"));
				
				LOGGER.debug("New Player ID: " + newPlayer.getUid());
				conn.close();
			}
			else
				newPlayer = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			newPlayer = null;
		}
		return newPlayer;
	}
}