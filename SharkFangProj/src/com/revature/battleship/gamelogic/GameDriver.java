package com.revature.battleship.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;

import com.revature.battleship.daos.GameStateDAO;
import com.revature.battleship.pojos.GameState;

public class GameDriver {
	
	GameStateDAO myGameStateDatabase = new GameStateDAO();
	GameState currentGameState;
	
	
	public static final char EMPTY = '0';
	public static final char HIT = '1';
	public static final char MISS = '2';
	public static final char SHIP = '3';
	
	// size of each ship that is placable on both boards
	private ArrayList<Integer> boardShips = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 3, 2)); 
	
	public void placeShipsOnPlayerBoard(ArrayList<Integer> playerShipLocations)
	{
		StringBuilder playerBoard = new StringBuilder(currentGameState.getPlayerOneBoard());
		
		for (Integer shipLocation : playerShipLocations)
		{
			playerBoard.setCharAt(shipLocation, GameDriver.SHIP);
		}
		
		currentGameState.setPlayerOneBoard(playerBoard.toString());
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
	
	public void saveGame() 
	{
		int gameID = currentGameState.getGameStateId();
		String p1Board = currentGameState.getPlayerOneBoard();
		String p2Board = currentGameState.getPlayerTwoBoard();
		myGameStateDatabase.saveGame(gameID, p1Board, p2Board);
	}
	
	public void generateComputerBoard()
	{
		// generate 5, 4, 3, 3, 2 ships for computer on computer board
		for (int i=0 ; i< boardShips.size(); i++ )
		{
			int totalBoardSize = currentGameState.getPlayerTwoBoard().length();
			int randomIndex = (int) (Math.random() * totalBoardSize);
			int randomDirection = (int) (Math.random() * 4); // Up:0 Right:1 Down:2 Left:3
			boolean placedOnBoard = false;

			boolean shipFits = shipFitsAtLocation(randomIndex, randomDirection, boardShips.get(i));
				
			if (shipFits)
			{
				placeShipOnComputerBoard(randomIndex, randomDirection, boardShips.get(i));
				placedOnBoard = true;
			}
			
			if (!placedOnBoard)
			{
				i--;
			}
		}
	}
	
	private boolean shipFitsAtLocation(int location, int direction, int shipSize)
	{
		String computerBoard = currentGameState.getPlayerTwoBoard();
		
		int directionParity; // +1 or -1, to determine where the next location will be at with respect to the board
		int incrementer; // 1 or rowLength, to determine how many spaces we must add/subtract to get to the next space of interest
		
		
		// get the location the ship last index would be at
				
		// check to see if there is already a ship at the location
		if (computerBoard.charAt(location) == GameDriver.SHIP)
		{
			return false;
		}
		
		int directionIncrementerCheck = direction % 2; // will return 0 if up or down, 1 if left or right
		if (directionIncrementerCheck == 0) // going either up or down
		{
			incrementer = currentGameState.getBoardLength();
		}
		else // directionCheck == 1,  going either right or left
		{
			incrementer = 1;
		}
		
		int directionParityCheck = direction % 3; // will return 0 if going up or left, 1 or 2 if right and down
		if (directionParityCheck == 0) // going up or left
		{
			directionParity = -1;
		}
		else // directionParityCheck == 1 or 2, going right or down
		{
			directionParity = 1;
		}
		
		int lastIndex = location + (directionParity * incrementer * (shipSize - 1));
		// check if it is on the board
		if (lastIndex < 0 || lastIndex >= currentGameState.getPlayerTwoBoard().length())
		{
			return false;
		}
		
		int rowLength = currentGameState.getBoardLength();
		
		int currentLocation;
		int oldColumn = location % rowLength;
		// loop through the spaces and see if there is a ship already there
		for (int i = 1; i < shipSize; i++)
		{
			currentLocation = location + (directionParity * incrementer * (shipSize - i));
			if (computerBoard.charAt(currentLocation) == '3')
			{
				return false;
			}
			int newColumn = (currentLocation % rowLength);
			int edgeTest = oldColumn - newColumn;
			if (edgeTest == -1 || edgeTest == 1 || edgeTest == 0) // correct move
			{
				oldColumn = newColumn;
			}
			else
			{
				return false;
			}
		}
		
		// all the spaces are not ships, so must be empty and space can fit in this direction
		return true;
	}
	
	private void placeShipOnComputerBoard(int location, int direction, int shipSize)
	{
		String computerBoard = currentGameState.getPlayerTwoBoard();
		
		int directionParity; // +1 or -1, to determine where the next location will be at with respect to the board
		int incrementer; // 1 or rowLength, to determine how many spaces we must add/subtract to get to the next space of interest
		
		int directionIncrementerCheck = direction % 2; // will return 0 if up or down, 1 if left or right
		if (directionIncrementerCheck == 0) // going either up or down
		{
			incrementer = currentGameState.getBoardLength();
		}
		else // directionCheck == 1,  going either right or left
		{
			incrementer = 1;
		}
		
		int directionParityCheck = direction % 3; // will return 0 if going up or left, 1 or 2 if right and down
		if (directionParityCheck == 0) // going up or left
		{
			directionParity = -1;
		}
		else // directionParityCheck == 1 or 2, going right or down
		{
			directionParity = 1;
		}
		
		int currentLocation;
		// change this to a string builder 
		StringBuilder currentBoardSB = new StringBuilder(computerBoard);
		currentBoardSB.setCharAt(location, GameDriver.SHIP);
		for(int i = 1; i < shipSize; i++)
		{
			currentLocation = location + (directionParity * incrementer * (shipSize - i));
			currentBoardSB.setCharAt(currentLocation, GameDriver.SHIP);
		}
		
		currentGameState.setPlayerTwoBoard(currentBoardSB.toString());
	}
}