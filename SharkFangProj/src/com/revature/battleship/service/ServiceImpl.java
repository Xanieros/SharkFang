/*
 * Description: Class that will provide the implementations of the Service interface
 */

package com.revature.battleship.service;

import java.util.ArrayList;

import com.revature.battleship.daos.PlayerDAO;
import com.revature.battleship.gamelogic.GameDriver;
import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.Player;
import com.revature.battleship.pojos.Record;

public class ServiceImpl implements Service {

	// singleton design pattern, so all servlets use the same instance of serviceImpl
	private static ServiceImpl service;
	
	// make Database object
	PlayerDAO myPlayerDatabase = new PlayerDAO();
	GameDriver myGameDriver = new GameDriver();
	
	private ServiceImpl()
	{
		
	}
	
	public static ServiceImpl getService()
	{
		if (service == null)
		{
			service = new ServiceImpl();
		}
		return service;
	}
	
	@Override
	public Player login(String usernameEntered, String passwordEntered) {
		return myPlayerDatabase.login(usernameEntered, passwordEntered);
	}

	@Override
	public int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID) {
		return myGameDriver.initializeGame(xSize, ySize, userPlayerID, enemyPlayerID);
	}

	@Override
	public void placeShipsOntoBoard(String[] shipStringArrayLocations) {
		ArrayList<Integer> shipArrayListLocations = new ArrayList<Integer>();
		
		for(String shipLocation : shipStringArrayLocations)
		{
			shipArrayListLocations.add(Integer.parseInt(shipLocation));
		}
		
		// place user ships on board
		myGameDriver.placeShipsOnPlayerBoard(shipArrayListLocations);
		
		// place Computer/enemyShips on board
		myGameDriver.generateComputerBoard();
		
		// saves the current game state
		myGameDriver.saveGame();
	}

	@Override
	public void saveGame() {
		myGameDriver.saveGame();
	}

	@Override
	public int playerAttack(int target) {
		return myGameDriver.playerAttack(target);
	}

	@Override
	public int[] enemyAttack() {
		return myGameDriver.enemyAttack();
	}

	@Override
	public Player updatePlayer(Player player) {
		// TODO Auto-generated method stub

		Player updatedPlayer = myPlayerDatabase.updateInfo(	
				player.getUid(), 
				player.getPword(), 
				player.getFname(), 
				player.getLname(), 
				player.getProfPic()
		);
		return updatedPlayer;
	}

	@Override
	public String getPassword(int uid) {
		return myPlayerDatabase.getPassword(uid);
	}

	@Override
	public int[] countSuccessfulHits() {
		return myGameDriver.countSuccessfulHits();
	}

	@Override
	public ArrayList<GameState> loadPlayerGames(int uid, int offset) {
		return myGameDriver.loadPlayerGames(uid, offset);
	}

	@Override
	public GameState loadGame(int gid) {
		return myGameDriver.loadGame(gid);
	}

	@Override
	public Record loadPlayerRecord(int uid) {
		// TODO Auto-generated method stub
		return myGameDriver.loadPlayerRecord(uid);

	}

	@Override
	public ArrayList<Record> loadTopRecords(int numToShow) {
		return myGameDriver.loadTopRecords(numToShow);

	}

	@Override
	public void addWin(int uid) {
		myGameDriver.addWin(uid);
		
	}

	@Override
	public void addLoss(int uid) {
		myGameDriver.addLoss(uid);
		
	}

	@Override
	public void closeGame(int gid, int uid) {
		myGameDriver.closeGame(gid, uid);
		
	}

}
