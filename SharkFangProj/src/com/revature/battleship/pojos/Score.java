package com.revature.battleship.pojos;

public class Score {
	private Long sid;
	private Long score;
	private Long uid;
	public Score(Long sid, Long score, Long uid) {
		super();
		this.sid = sid;
		this.score = score;
		this.uid = uid;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
}