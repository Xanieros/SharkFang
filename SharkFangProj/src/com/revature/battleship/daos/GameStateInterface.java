package com.revature.battleship.daos;

import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.Record;

public interface GameStateInterface {
	
	public int startGame(int p1Id, int p2Id, String p1Board, String p2Board, int rowLength);

	public void saveGame(int gid, String p1Board, String p2Board);
	
	public GameState loadGame(int gid);
	
	public Record endGame(int gid, int winner);
}
