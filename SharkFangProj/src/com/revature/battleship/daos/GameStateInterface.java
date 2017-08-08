package com.revature.battleship.daos;

import java.util.ArrayList;

import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.Record;

public interface GameStateInterface {
	
	public int startGame(int p1Id, int p2Id, String p1Board, String p2Board, int rowLength);

	public void saveGame(int gid, String p1Board, String p2Board);
	
	public GameState loadGame(int gid);
	
	public ArrayList<GameState> loadPlayerGames(int uid, int offset);
	
	public Record endGame(int gid, int winner);
	
	void deleteGameState(int gameStateID);
}
