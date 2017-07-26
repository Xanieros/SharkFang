package com.revature.battleship.pojos;

import javax.persistence.*;
import java.util.*;

@Entity(name="Player")
public class Player 
{
	@Id
	@GeneratedValue 
	private Long uid;
	@Column(name="username", nullable=false)
	private String uname;
	@Column(name="password", nullable=false)
	private String pword;
	@Column(nullable=false)
	private String email;
	@Column(name="first_name")
	private String fname;
	@Column(name="last_name")
	private String lname;
	@Column(name="profile_picture")
	private String profPic;
	@ElementCollection
	@CollectionTable(
			name="scores",
			joinColumns=@JoinColumn(name="uid")
			)
	@OrderColumn(name="scores")
	private ArrayList<Integer> highScores = new ArrayList<Integer>();


	public Player()
	{
		
	}
	
	public Player(Long uid, String uname, String pword, String email, String fname, String lname, String profPic) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pword = pword;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.profPic = profPic;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getProfPic() {
		return profPic;
	}
	public void setProfPic(String profPic) {
		this.profPic = profPic;
	}
	
	public ArrayList<Integer> getHighScores() {
		return highScores;
	}

	public void setHighScores(ArrayList<Integer> highScores) {
		this.highScores = highScores;
	}
	
}
