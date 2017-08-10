package com.revature.battleship.daos;


import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.battleship.pojos.*;

import oracle.jdbc.OracleTypes;

public class GameStateDAO implements GameStateInterface {
	private static final Logger LOGGER = LogManager.getLogger(GameStateDAO.class);
	private static Connection conn;

	@Override
	public int startGame(int p1Id, int p2Id, String p1Board, String p2Board, int rowLength) {
		LOGGER.info("in startGame");
		conn = OracleConnection.getOracleConnection();
		int output = -1;

		try {
			LOGGER.info("calling START_GAME(?,?,?,?,?,?");
			CallableStatement cs = conn.prepareCall("call START_GAME(?,?,?,?,?,?)");
			cs.setInt(1, p1Id);
			cs.setInt(2, p2Id);
			cs.setString(3, p1Board);
			cs.setString(4, p2Board);
			cs.setInt(5, rowLength);
			cs.registerOutParameter(6, OracleTypes.NUMBER);

			cs.executeQuery();
			LOGGER.info("getting gameState ID");
			output = cs.getInt(6);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("GameStateId: " + output);
		return output;
	}

	@Override
	public GameState loadGame(int gid) {
		LOGGER.info("in loadGame");
		GameState gameState = new GameState();
		conn = OracleConnection.getOracleConnection();
		try {
			LOGGER.info("calling GET_GAME_STATE(?,?)");
			CallableStatement cs = conn.prepareCall("call GET_GAME_STATE(?,?)");
			cs.setInt(1, gid);
			cs.registerOutParameter(2, OracleTypes.CURSOR);

			cs.executeQuery();
			ResultSet rs = (ResultSet) cs.getObject(2);
			if (rs.next()) {
				gameState.setBoardLength(rs.getInt("BOARD_LENGTH"));
				gameState.setGameStateId(gid);
				gameState.setPlayerOneBoard(rs.getString("P1_BOARD"));
				gameState.setPlayerOneId(rs.getInt("P1_ID"));
				gameState.setPlayerTwoBoard(rs.getString("P2_BOARD"));
				gameState.setPlayerTwoId(rs.getInt("P2_ID"));
			} else {
				gameState = null;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			gameState = null;
		}

		return gameState;
	}

	// WHEN MULTIPLAYER IS IMPLEMENTED CHECK RECORD FOR ACCURACY, MAKE SURE THEY
	// ARE NOT BEING UPDATED TWICE
	@Override
	public Record endGame(int gid, int winner) {
	//public ArrayList<Record> endGame(int gid, int winner) {
		LOGGER.info("in endGame");
		conn = OracleConnection.getOracleConnection();
		RecordDAO rDAO = new RecordDAO();
		Record p1Record = new Record();
		//Record p2Record = new Record();
		//ArrayList<Record recordAL = new ArrayList<Record>();

		try {
			LOGGER.info("calling END_GAME(?,?,?)");
			CallableStatement cs = conn.prepareCall("call END_GAME(?,?,?)");
			cs.setInt(1, gid);
			cs.setInt(2, winner);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs = (ResultSet) cs.getObject(3);
			if (rs.next()) {
				if(winner == 1)
				{
					p1Record = rDAO.addWin(rs.getInt("P1_ID"));
					//p2Record = rDAO.addLoss(rs.getInt("P2_ID"));
				}
				else
				{
					p1Record = rDAO.addLoss(rs.getInt("P1_ID"));
					//p2Record = rDAO.addWin(rs.getInt("P2_ID"));
				}
				
				//recordAL.add(p1Record);
				//recordAL.add(p2Record);
				
				/*
				 * LOGGER.info("calling GET_RECORD(?) for player 1"); cs =
				 * conn.prepareCall("call GET_RECORD(?)"); cs.setInt(1,
				 * rs.getInt("P1_ID"));
				 * 
				 * ResultSet p1rs = (ResultSet)cs.executeQuery(); Record p1Rec =
				 * new Record();
				 * 
				 * if(p1rs.next()) { p1Rec.setLosses(p1rs.getInt("LOSSES"));
				 * p1Rec.setRid(p1rs.getInt("R_ID"));
				 * p1Rec.setUid(p1rs.getInt("U_ID"));
				 * p1Rec.setWins(p1rs.getInt("WINS")); }
				 * 
				 * LOGGER.info("calling GET_RECORD(?) for player 2"); cs =
				 * conn.prepareCall("call GET_RECORD(?)"); cs.setInt(1,
				 * rs.getInt("P2_ID"));
				 * 
				 * ResultSet p2rs = (ResultSet)cs.executeQuery(); Record p2Rec =
				 * new Record();
				 * 
				 * if(p2rs.next()) { p2Rec.setLosses(p2rs.getInt("LOSSES"));
				 * p2Rec.setRid(p2rs.getInt("R_ID"));
				 * p2Rec.setUid(p2rs.getInt("U_ID"));
				 * p2Rec.setWins(p2rs.getInt("WINS")); }
				 * 
				 * LOGGER.info("if player one wins"); if(winner == 1) {
				 * LOGGER.info("calling ADD_WIN(?) for player1"); cs =
				 * conn.prepareCall("call ADD_WIN(?,?)");
				 * cs.setInt(2,p1Rec.getWins()+1); cs.setInt(1, p1Rec.getUid());
				 * cs.executeQuery();
				 * 
				 * LOGGER.info("calling ADD_LOSS(?) for player 2"); cs =
				 * conn.prepareCall("call ADD_LOSS(?)");
				 * cs.setInt(1,p2Rec.getUid()); cs.setInt(2,
				 * p2Rec.getLosses()+1);
				 * 
				 * cs.executeQuery(); } else if(winner == 2) {
				 * LOGGER.info("if player 2 wins");
				 * LOGGER.info("calling ADD_WIN(?) for player 2"); cs =
				 * conn.prepareCall("call ADD_WIN(?)");
				 * cs.setInt(1,p2Rec.getUid()); cs.setInt(2, p2Rec.getWins()+1);
				 * cs.executeQuery();
				 * 
				 * LOGGER.info("calling ADD_LOSS(?) for player 1"); cs =
				 * conn.prepareCall("call ADD_LOSS(?)");
				 * cs.setInt(1,p1Rec.getUid()); cs.setInt(2,
				 * p1Rec.getLosses()+1);
				 * 
				 * cs.executeQuery(); }
				 * 
				 * LOGGER.
				 * info("calling GET_RECORD(?,?) to return player 1 record"); cs
				 * = conn.prepareCall("call GET_RECORD(?,?)"); cs.setInt(1,
				 * rs.getInt("P1_ID")); cs.registerOutParameter(2,
				 * OracleTypes.CURSOR);
				 * 
				 * ResultSet rs2 = (ResultSet)cs.executeQuery(); if(rs2.next())
				 * { record.setLosses(rs2.getInt("LOSSES"));
				 * record.setRid(rs2.getInt("R_ID"));
				 * record.setUid(rs2.getInt("U_ID"));
				 * record.setWins(rs2.getInt("WINS")); }
				 */
				LOGGER.debug("record id: " + p1Record.getRid());
				conn.close();
			} else {
				p1Record = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return recordAL;
		return p1Record;
	}

	@Override
	public void saveGame(int gid, String p1Board, String p2Board) {
		LOGGER.info("in saveGame");
		conn = OracleConnection.getOracleConnection();
		try {
			LOGGER.info("calling UPDATE_GAME(?,?,?)");
			CallableStatement cs = conn.prepareCall("call UPDATE_GAME(?,?,?)");
			cs.setInt(1, gid);
			cs.setString(2, p1Board);
			cs.setString(3, p2Board);

			cs.executeQuery();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// used for Junit testing to reverts changes
	@Override
	public void deleteGameState(int gameStateID) {
		LOGGER.debug("in deleteGameState");
		conn = OracleConnection.getOracleConnection();
		try {
			String sqlCommand = "call DELETE_GAME_STATE(?)";
			CallableStatement deleteGSCallableStmt = conn.prepareCall(sqlCommand);
			deleteGSCallableStmt.setInt(1, gameStateID);
			
			deleteGSCallableStmt.executeUpdate();
			conn.close();
		} catch (SQLException sqlE) {
			LOGGER.fatal("SQL Exception in DeleteGameState(" + gameStateID + ")");
			sqlE.printStackTrace();
		}
	}

	@Override
	public ArrayList<GameState> loadPlayerGames(int uid, int offset) {
		ArrayList<GameState> gameStates = new ArrayList<GameState>();
		LOGGER.debug("in loadPlayerGames");
		conn = OracleConnection.getOracleConnection();
		try {
			CallableStatement cs = conn.prepareCall("call GET_PLAYER_GAME_STATES(?,?,?)");
			
			cs.setInt(1, uid);
			cs.setInt(2, offset);
			cs.registerOutParameter(3, OracleTypes.CURSOR);

			cs.executeQuery();
			ResultSet rs = (ResultSet) cs.getObject(3);
			while (rs.next()) {
				GameState gameState = new GameState();
				gameState.setBoardLength(rs.getInt("BOARD_LENGTH"));
				gameState.setGameStateId(rs.getInt("GS_ID"));
				gameState.setPlayerOneBoard(rs.getString("P1_BOARD"));
				gameState.setPlayerOneId(rs.getInt("P1_ID"));
				gameState.setPlayerTwoBoard(rs.getString("P2_BOARD"));
				gameState.setPlayerTwoId(rs.getInt("P2_ID"));
				gameState.setActive(rs.getInt("ACTIVE"));
				gameState.setTimeStamp(rs.getTimestamp("SAVE_TIME"));
				
				gameStates.add(gameState);
			}
			conn = OracleConnection.getOracleConnection();
			
		} catch (SQLException sqlE) {
			LOGGER.fatal("SQL Exception in loadPlayerGames(" + uid + ")");
			sqlE.printStackTrace();
		}
		return gameStates;
	}

}