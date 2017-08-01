package com.revature.battleship.tests;

import static org.junit.Assert.*;

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
		
		int expectedPlayerOneID = 1001;
		int actualPlayerOneID = actualGameState.getPlayerOneId();
		assertEquals(expectedPlayerOneID, actualPlayerOneID);
		
		int expectedPlayerTwoID = 1002;
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
		
		int expectedActive = 0;
		int actualActive = actualGameState.getActive();
		assertEquals(expectedActive, actualActive);
		
		// need to test timestamp
	}
	
	@Test
	public void testStartGame()
	{
		assertTrue(1<myGameStateDAO.startGame(1002, 1003, "testString", "testString", 10));
	}
	
	@Test
	public void testEndGame()
	{
		assertEquals(1001, (myGameStateDAO.endGame(1, 1)).getUid());
	}
	
	/*@Test
	public void testSaveGame()
	{
		myGameStateDAO.saveGame(1, "newTest", "newTest");
	}*/
}
