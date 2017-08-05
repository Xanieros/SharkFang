package com.revature.battleship.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;

import com.revature.battleship.gamelogic.GameDriver;
import com.revature.battleship.pojos.GameState;

public class GameDriverTest {
	
	GameDriver gd;
	GameState currentGameState;
	
	@Before
	public void setUp() throws Exception {
		
		
		currentGameState = new GameState();
		
		//Using reflection to test methods as if a full game were running
		gd = new GameDriver();
		Class<?> gamedriver = gd.getClass();		
		
		//This will inject the GameState made in this JUnit, so that we can access it
		//As it is needed for all the methods
		Field cgsField = gamedriver.getDeclaredField("currentGameState");
		cgsField.setAccessible(true);
		cgsField.set(gd, currentGameState);
		
		//This was Ankit's example for reflection. Haven't got it working.
		/*Class noparams[]={};
		Class gd = Class.forName("com.revature.battleship.gamelogic.GameDriver");
		Object gdObj = gd.newInstance();
				
		Field cgsField = gd.getDeclaredField("currentGameState");
		cgsField.setAccessible(true);
		cgsField.set(gdObj, currentGameState);*/
				
	}

	@After
	public void tearDown() throws Exception {
		gd = null;
		currentGameState = null;
		System.gc();
	}

	@Test
	public void testEnemyAttack() {
		//This is a predefined game from Jon's DB. Player Ships may not be valid
		currentGameState.setPlayerOneBoard("3000000000300000000030000000003300000000330000000033000000003300000000330000000033000000003300000000");		
		int[] attackedCell = gd.enemyAttack();
		assertTrue((attackedCell[0] >= 0) && (attackedCell[0] <= 99));//Valid range for the board given
		System.out.println(attackedCell[0]+" "+attackedCell[1]);
		System.out.println(currentGameState.getPlayerOneBoard());
		
		//No possible moves
		currentGameState.setPlayerOneBoard("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		try{
			gd.enemyAttack();
		}catch (Exception e){
			System.out.println("Aray Index OOB");
		}
		
		//One possible move
		currentGameState.setPlayerOneBoard("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110");
		assertEquals(99, gd.enemyAttack()[0]);
		
	}

}

//Valid fields for a GameState Object
//Timestamp will nullpointer, however
/*currentGameState = new GameState(
2, 
1004, 
0, 
"3000000000300000000030000000003300000000330000000033000000003300000000330000000033000000003300000000", //P1
"3000000000300000000030000000000000000003000000000330000003333000003003300000300300000030000000003000", //CPU
10, 
0, 
new TimeStamp("01-AUG-17 04.14.13.365000000 PM"));*/
