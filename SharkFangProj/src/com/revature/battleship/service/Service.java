/*
 * Description: Service Interface that will provide all the methods needed for any Service implementations 
 */

package com.revature.battleship.service;

import java.util.ArrayList;

import com.revature.battleship.pojos.Player;

public interface Service {
	Player login(String usernameEntered, String passwordEntered);
	int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID);
	void placeShipsOntoBoard(String[] shipLocations);
	void saveGame();
	int playerAttack(int target);
	int[] enemyAttack();
	String getPassword(int uid);
	Player updatePlayer(Player player);
}
