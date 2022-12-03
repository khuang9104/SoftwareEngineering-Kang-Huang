package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backgammon.GameData;
import backgammon.Gameboard;

class SubjectTest {

	GameData gameData = new GameData();
	Gameboard gameboard = new Gameboard(gameData);
	
	@Test
	void testRegisterObserver() {
		assertEquals(1,gameData.getObserversSize());
	}
	// Test the Register Observer function

	@Test
	void testRemoveObserver() {
		gameData.removeObserver(gameboard);
		assertEquals(0,gameData.getObserversSize());
	}
	// Test the Remove Observer function

	@Test
	void testNotifyObservers() {
		assertEquals("empty",gameData.peekPoints(10));
		assertEquals("empty",gameData.peekPoints(25));
		gameData.pushPoints(10, "Red");
		gameData.pushPoints(25, "Black");
		assertEquals("Red",gameData.peekPoints(10));
		assertEquals("Black",gameData.peekPoints(25));
	}
	// Test the Notify Observer function
}
