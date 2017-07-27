package com.revature.battleship.pojos;

public class Record {
	private Long sid;
	private Long wins;
	private Long losses;
	private Long uid;
	public Record(Long sid, Long wins, Long losses, Long uid) {
		super();
		this.sid = sid;
		this.wins = wins;
		this.losses = losses;
		this.uid = uid;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getWins() {
		return wins;
	}
	public void setWins(Long wins) {
		this.wins = wins;
	}
	public Long getLosses() {
		return losses;
	}
	public void setLosses(Long losses) {
		this.losses = losses;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
}