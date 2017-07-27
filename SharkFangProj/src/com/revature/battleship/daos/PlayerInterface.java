package com.revature.battleship.daos;

import com.revature.battleship.pojos.Player;

public interface PlayerInterface {
	public Player login(String username, String password);
}
