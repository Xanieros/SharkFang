package com.revature.battleship.daos;

import com.revature.battleship.pojos.Player;

public interface PlayerInterface {
	public Player login(String username, String password);
	public Player updateInfo(int pid, String pword, String fname, String lname, String profPic);
}
