package com.revature.battleship.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.battleship.pojos.OracleConnection;
import com.revature.battleship.pojos.Record;

import oracle.jdbc.OracleTypes;

public class RecordDAO implements RecordInterface {
	private static final Logger LOGGER = LogManager.getLogger(GameStateDAO.class);
	private Connection conn = OracleConnection.getOracleConnection();

	@Override
	public Record addWin(int pid) {
		LOGGER.info("in addWin");
		Record rec = new Record();
		try {
			LOGGER.info("calling ADD_WIN(?) for " + pid);
			CallableStatement cs = conn.prepareCall("call ADD_WIN(?)");
			cs.setInt(1, pid);
			cs.executeUpdate();
			
			rec = getPlayerRecord(pid);
			
			LOGGER.debug(pid + "'s wins: " + rec.getWins());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rec;
	}

	@Override
	public Record addLoss(int pid) {
		LOGGER.info("in addLoss");
		Record rec = new Record();
		try {
			LOGGER.info("calling ADD_LOSS(?) for " + pid);
			CallableStatement cs = conn.prepareCall("call ADD_LOSS(?)");
			cs.setInt(1, pid);
			cs.executeUpdate();

			rec = getPlayerRecord(pid);
			LOGGER.debug(pid + "'s wins: " + rec.getLosses());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rec;
	}

	@Override
	public Record getPlayerRecord(int uid) {
		LOGGER.info("in getPlayerRecord");
		Record record = new Record();

		try {
			LOGGER.info("calling GET_RECORD(?,?)");
			CallableStatement cs = conn.prepareCall("call GET_RECORD(?,?)");
			cs.setInt(1, uid);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs = (ResultSet) cs.getObject(2);
			if (rs.next()) {
				record.setLosses(rs.getInt("LOSSES"));
				record.setRid(rs.getInt("R_ID"));
				record.setUid(uid);
				record.setWins(rs.getInt("WINS"));

				LOGGER.debug("record ID: " + record.getRid());

			} else
				record = null;
		} catch (Exception e) {
			e.printStackTrace();
			record = null;
		}
		return record;
	}

	@Override
	public ArrayList<Record> getTopRank(int limit) {
		LOGGER.info("in getTopRank");
		ArrayList<Record> recordsAL = new ArrayList<Record>();

		try {
			LOGGER.info("calling GET_TOP_RECORDS");
			CallableStatement cs = conn.prepareCall("call GET_TOP_RECORDS(?,?)");
			cs.setInt(1, limit);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(2);

			while (rs.next()) {
				Record curRec = new Record(rs.getInt("R_ID"), rs.getInt("WINS"), rs.getInt("LOSSES"),
						rs.getInt("U_ID"));
				recordsAL.add(curRec);
			}
			LOGGER.debug("top records: " + recordsAL.toString());
		} catch (Exception e) {
			e.printStackTrace();
			recordsAL = null;
		}

		return recordsAL;
	}

}
