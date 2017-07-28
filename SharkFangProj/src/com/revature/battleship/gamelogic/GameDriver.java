package com.revature.battleship.gamelogic;

import java.util.ArrayList;

public class GameDriver {

	private ArrayList<Integer> playerBoard;
	private ArrayList<Integer> enemyBoard;
	
	public GameDriver(int playerBoardSize, int enemyBoardSize)
	{
		playerBoard = new ArrayList<Integer>(playerBoardSize);
		enemyBoard = new ArrayList<Integer>(enemyBoardSize);
	}
	
	public ArrayList<Integer> getPlayerBoard() {
		return playerBoard;
	}

	public void setPlayerBoard(ArrayList<Integer> playerBoard) {
		this.playerBoard = playerBoard;
	}

	public ArrayList<Integer> getEnemyBoard() {
		return enemyBoard;
	}

	public void setEnemyBoard(ArrayList<Integer> enemyBoard) {
		this.enemyBoard = enemyBoard;
	}

	public static void main(String[] args) {
		System.out.println("It worked!");
	}
	
	void placeShipsOnPlayerBoard(ArrayList<Integer> playerShipLocations)
	{
		//need for loops
	}
}