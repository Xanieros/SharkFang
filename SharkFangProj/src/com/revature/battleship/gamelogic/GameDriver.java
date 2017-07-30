package com.revature.battleship.gamelogic;

import java.util.ArrayList;

import com.revature.battleship.daos.GameStateDAO;
import com.revature.battleship.pojos.GameState;

public class GameDriver {
	
	GameStateDAO myGameStateDatabase = new GameStateDAO();
	GameState currentGameState;
	
	
	public static final char EMPTY = '0';
	public static final char HIT = '1';
	public static final char MISS = '2';
	public static final char SHIP = '3';
	
	public void placeShipsOnPlayerBoard(ArrayList<Integer> playerShipLocations)
	{
		StringBuilder playerBoard = new StringBuilder(currentGameState.getPlayerOneBoard());
		
		for (Integer shipLocation : playerShipLocations)
		{
			playerBoard.setCharAt(shipLocation, GameDriver.SHIP);
		}
		
		currentGameState.setPlayerOneBoard(playerBoard.toString());
		
		int gameID = currentGameState.getGameStateId();
		String p1Board = currentGameState.getPlayerOneBoard();
		String p2Board = currentGameState.getPlayerTwoBoard();
		myGameStateDatabase.saveGame(gameID, p1Board, p2Board);
	}
	
	public int initializeGame(int xSize, int ySize, int userPlayerID, int enemyPlayerID)
	{
		int totalBoardSize = xSize * ySize;
		StringBuilder initialBoard = new StringBuilder("");
		
		for (int i = 0; i < totalBoardSize; i++)
		{
			initialBoard.append("0");
		}
		
		String initialBoardAsString = initialBoard.toString();
		
		int gameID = myGameStateDatabase.startGame(userPlayerID, enemyPlayerID, initialBoardAsString, initialBoardAsString, xSize);
		
		myGameStateDatabase.saveGame(gameID, initialBoardAsString, initialBoardAsString);
		
		currentGameState = new GameState(gameID, userPlayerID, enemyPlayerID, initialBoardAsString, initialBoardAsString, xSize);
		
		return gameID;
	}
}