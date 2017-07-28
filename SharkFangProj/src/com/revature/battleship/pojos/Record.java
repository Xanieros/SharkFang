package com.revature.battleship.pojos;

public class Record {
	private int sid;
	private int wins;
	private int losses;
	private int uid;
	
	public Record()
	{
		
	}
	
	public Record(int sid, int wins, int losses, int uid) {
		super();
		this.sid = sid;
		this.wins = wins;
		this.losses = losses;
		this.uid = uid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
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