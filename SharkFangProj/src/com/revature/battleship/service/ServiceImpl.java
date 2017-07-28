/*
 * Description: Class that will provide the implementations of the Service interface
 */

package com.revature.battleship.service;

import com.revature.battleship.daos.PlayerDAO;
import com.revature.battleship.pojos.Player;

public class ServiceImpl implements Service {

	// make Database object
	PlayerDAO myPlayerDatabase = new PlayerDAO();
	
	@Override
	public Player login(String usernameEntered, String passwordEntered) {
		return myPlayerDatabase.login(usernameEntered, passwordEntered);
	}

}
