package com.revature.battleship.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.battleship.daos.GameStateDAO;
import com.revature.battleship.pojos.GameState;

public class GameStateDAOTest {

	GameStateDAO testGameStateDAO;
	GameState testGameState;
	int testGameID;
	int expectedp1ID;
	int expectedp2ID;
	String expectedBoard;
	int expectedRowLength;
	
	@Before
	public void setUp() throws Exception {
		testGameStateDAO = new GameStateDAO();
		expectedp1ID = 1000;
		expectedp2ID = 2000;
		expectedBoard = "0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000" +
				   				"0000000000";
		expectedRowLength = 10;
		testGameID = testGameStateDAO.startGame(expectedp1ID, expectedp2ID, expectedBoard, expectedBoard, expectedRowLength);
	}

	@After
	public void tearDown() throws Exception {
		testGameStateDAO.deleteGameState(testGameID);
		testGameStateDAO = null;
		testGameState = null;
		System.gc();
	}
	
	@Test
	public void testLoadGame() {
		GameState actualGameState = testGameStateDAO.loadGame(testGameID);

		int actualGameID = actualGameState.getGameStateId();
		assertEquals(testGameID, actualGameID);
		
		int expectedPlayerOneID = 1001;
		int actualPlayerOneID = actualGameState.getPlayerOneId();
		assertEquals(expectedp1ID, actualPlayerOneID);
		
		int expectedPlayerTwoID = 1002;
		int actualPlayerTwoID = actualGameState.getPlayerTwoId();
		assertEquals(expectedp2ID, actualPlayerTwoID);
		
		String actualPlayerOneBoard = actualGameState.getPlayerOneBoard();
		assertEquals(expectedBoard, actualPlayerOneBoard);
		
		String actualPlayerTwoBoard = actualGameState.getPlayerTwoBoard();
		assertEquals(expectedBoard, actualPlayerTwoBoard);
		
		int actualBoardLength = actualGameState.getBoardLength();
		assertEquals(expectedRowLength, actualBoardLength);
		
		int expectedActive = 0;
		int actualActive = actualGameState.getActive();
		assertEquals(expectedActive, actualActive);
		
		// need to test timestamp
	}

  @Test
	public void testSaveGame()
	{
		String expectedNewP1Board = "1111122222" +
									"3333344444" +
									"5555566666" +
									"7777788888" +
									"9999900000";
		String expectedNewP2Board = "0000099999" +
									"8888877777" +
									"6666655555" +
									"4444433333" +
									"2222211111";
		
		testGameStateDAO.saveGame(testGameID, expectedNewP1Board, expectedNewP2Board);
		
		GameState actualGameState = testGameStateDAO.loadGame(testGameID);

		int actualGameID = actualGameState.getGameStateId();
		assertEquals(testGameID, actualGameID);
		
		int actualPlayerOneID = actualGameState.getPlayerOneId();
		assertEquals(expectedp1ID, actualPlayerOneID);
		
		int actualPlayerTwoID = actualGameState.getPlayerTwoId();
		assertEquals(expectedp2ID, actualPlayerTwoID);
		
		String actualPlayerOneBoard = actualGameState.getPlayerOneBoard();
		assertEquals(expectedNewP1Board, actualPlayerOneBoard);
		
		String actualPlayerTwoBoard = actualGameState.getPlayerTwoBoard();
		assertEquals(expectedNewP2Board, actualPlayerTwoBoard);
		
		int actualBoardLength = actualGameState.getBoardLength();
		assertEquals(expectedRowLength, actualBoardLength);
	}
	
	@Test
	public void testEndGame()
	{
		//assertEquals(1001, (myGameStateDAO.endGame(1, 1)).getUid());
	}
}
