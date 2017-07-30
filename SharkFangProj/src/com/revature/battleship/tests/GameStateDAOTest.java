package com.revature.battleship.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.daos.GameStateDAO;
import com.revature.battleship.pojos.GameState;

public class GameStateDAOTest {

	GameStateDAO myGameStateDAO;
	
	@Before
	public void setUp() throws Exception {
		myGameStateDAO = new GameStateDAO();
	}

	@After
	public void tearDown() throws Exception {
		myGameStateDAO = null;
		System.gc();
	}

	@Test
	public void testLoadGame() {
		GameState actualGameState = myGameStateDAO.loadGame(1);
		
		int expectedGameID = 1;
		int actualGameID = actualGameState.getGameStateId();
		assertEquals(expectedGameID, actualGameID);
		
		int expectedPlayerOneID = 1004;
		int actualPlayerOneID = actualGameState.getPlayerOneId();
		assertEquals(expectedPlayerOneID, actualPlayerOneID);
		
		int expectedPlayerTwoID = 0;
		int actualPlayerTwoID = actualGameState.getPlayerTwoId();
		assertEquals(expectedPlayerTwoID, actualPlayerTwoID);
		
		String expectedBoard = "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000" +
							   "0000000000";
		String actualPlayerOneBoard = actualGameState.getPlayerOneBoard();
		assertEquals(expectedBoard, actualPlayerOneBoard);
		
		String actualPlayerTwoBoard = actualGameState.getPlayerTwoBoard();
		assertEquals(expectedBoard, actualPlayerTwoBoard);
		
		int expectedBoardLength = 10;
		int actualBoardLength = actualGameState.getBoardLength();
		assertEquals(expectedBoardLength, actualBoardLength);
		
		/*int expectedActive = 0;
		int actualActive = actualGameState.get*/
		
		// need to test Active and timestamp, add to pojo
	}

}
