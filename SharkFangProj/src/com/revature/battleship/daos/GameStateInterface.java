package com.revature.battleship.daos;

import com.revature.battleship.pojos.GameState;
import com.revature.battleship.pojos.Record;

public interface GameStateInterface {

	public int saveGame(int p1Id, int p2Id, String p1Board, String p2Board, int rowLength);
	
	public GameState loadGame(int gid);
	
	public Record endGame(int winner);
}
