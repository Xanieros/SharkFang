package com.revature.battleship.pojos;

public class Record {
	private int rid;
	private int wins;
	private int losses;
	private int uid;
	
	public Record()
	{
		
	}
	
	public Record(int rid, int wins, int losses, int uid) {
		super();
		this.rid = rid;
		this.wins = wins;
		this.losses = losses;
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}