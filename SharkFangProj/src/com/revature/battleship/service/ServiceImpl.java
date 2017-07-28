/*
 * Description: Class that will provide the implementations of the Service interface
 */

package com.revature.battleship.service;

import com.revature.battleship.daos.PlayerDAO;
import com.revature.battleship.gamelogic.GameDriver;
import com.revature.battleship.pojos.Player;

public class ServiceImpl implements Service {

	// make Database object
	PlayerDAO myPlayerDatabase = new PlayerDAO();
	GameDriver myGameDriver;
	
	@Override
	public Player login(String usernameEntered, String passwordEntered) {
		return myPlayerDatabase.login(usernameEntered, passwordEntered);
	}

	@Override
	public int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID) {
		int totalBoardSize = xSize * ySize;
		myGameDriver = new GameDriver(totalBoardSize, totalBoardSize);
		
		int gameID = 1; // call db save game to db method, return game id, store that into the session in servlet
		return gameID;
	}
}
