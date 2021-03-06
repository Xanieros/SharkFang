/*
 * Description: Service Interface that will provide all the methods needed for any Service implementations 
 */

package com.revature.battleship.service;

import java.util.ArrayList;

import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.Player;
import com.revature.battleship.pojos.Record;

public interface Service {
	Player login(String usernameEntered, String passwordEntered);
	int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID);
	void placeShipsOntoBoard(String[] shipLocations);
	void saveGame();
	int playerAttack(int target);
	int[] countSuccessfulHits();
	int[] enemyAttack();
	String getPassword(int uid);
	Player updatePlayer(Player player);
	ArrayList<GameState> loadPlayerGames(int uid, int offset);
	GameState loadGame(int gid);
	Record loadPlayerRecord(int uid);
	ArrayList<Record> loadTopRecords(int numToShow);
	void addWin(int uid);
	void addLoss(int uid);
	void closeGame(int gid, int uid);
	void deleteGame(int gid);
}
