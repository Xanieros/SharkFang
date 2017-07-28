package com.revature.battleship.daos;

import java.util.ArrayList;

import com.revature.battleship.pojos.Record;

public interface RecordInterface {
	public Record addWin(int pid);
	public Record addLoss(int pid);
	public ArrayList<Record> getPlayerRecord(int uid);
	public ArrayList<Record> getTopRank(int limit);
}
