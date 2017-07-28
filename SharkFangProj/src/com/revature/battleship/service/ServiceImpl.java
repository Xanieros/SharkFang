/*
 * Description: Class that will provide the implementations of the Service interface
 */

package com.revature.battleship.service;

import java.util.ArrayList;

import com.revature.battleship.daos.PlayerDAO;
import com.revature.battleship.gamelogic.GameDriver;
import com.revature.battleship.pojos.Player;

public class ServiceImpl implements Service {

	// make Database object
	PlayerDAO myPlayerDatabase = new PlayerDAO();
	GameDriver myGameDriver = new GameDriver();
	
	@Override
	public Player login(String usernameEntered, String passwordEntered) {
		return myPlayerDatabase.login(usernameEntered, passwordEntered);
	}

	@Override
	public int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID) {
		return myGameDriver.initializeGame(xSize, ySize, userPlayerID, enemyPlayerID);
	}

	@Override
	public void placeShipsOntoBoard(ArrayList<Integer> shipLocations) {
		myGameDriver.placeShipsOnPlayerBoard(shipLocations);
	}
}
