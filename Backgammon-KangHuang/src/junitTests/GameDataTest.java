package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backgammon.GameData;
import backgammon.Gameboard;
import backgammon.PipsCalculator;

class GameDataTest {
	
	GameData gameData = new GameData();
	Gameboard gameboard = new Gameboard(gameData);

	@Test
	void testGameData() {
		assertNotNull(gameboard);
	}
	// Test the constructor.
	// The methods related to Observer have been tested in corresponding interface class JUnit test.

	@Test
	void testSetPlayerNames() {
		gameData.setPlayerNames("Kang", 0);
		gameData.setPlayerNames("Wenwei", 1);
		assertEquals("Kang",gameData.getPlayerNames()[0]);
		assertEquals("Wenwei",gameData.getPlayerNames()[1]);
	}
	// Test SetPlayerNames and GetPlayerNames methods.

	@Test
	void testPushPoints() {
		gameData.clearPoints();
		gameData.pushPoints(9, "Red");
		gameData.pushPoints(10, "Black");
		gameData.pushPoints(25, "Black");
		gameData.pushPoints(0, "Red");
		assertEquals("Black",gameData.peekPoints(10));
		assertEquals("Black",gameData.peekPoints(25));
		assertEquals("Red",gameData.peekPoints(0));
		assertEquals("Red",gameData.peekPoints(9));
	}
	// Test PushPoints and PeekPoints methods.

	@Test
	void testPopPoints() {
		gameData.clearPoints();
		gameData.pushPoints(9, "Red");
		gameData.pushPoints(10, "Black");
		assertEquals("Red",gameData.popPoints(9));
		assertEquals("Black",gameData.popPoints(10));
	}
	// Test PopPoints method.

	@Test
	void testGetSize() {
		gameData.clearPoints();
		gameData.pushPoints(9, "Red");
		gameData.pushPoints(9, "Red");
		assertEquals(2,gameData.getSize(9));
		assertEquals(0,gameData.getSize(1));
	}
	// Test GetSize method.

	@Test
	void testClearPoints() {
		gameData.clearPoints();
		for (int i = 0; i < 26; i++) {
			assertEquals(0,gameData.getSize(i));
		}
	}
	// Test ClearPoints method.

	@Test
	void testGetPoints() {
		PipsCalculator pipsCalculator = new PipsCalculator();
		assertEquals(false, pipsCalculator.gameOverCheck(gameData.getPoints()));
	}
	// Test GetPoints method.

	@Test
	void testBearOffCheck() {
		assertEquals(0, gameData.bearOffCheck()[0]);
		assertEquals(0, gameData.bearOffCheck()[1]);
		gameData.clearPoints();
		gameData.pushPoints(24, "Red");
		assertEquals(1, gameData.bearOffCheck()[1]);
		gameData.clearPoints();
		gameData.pushPoints(2, "Red");
		assertEquals(1, gameData.bearOffCheck()[0]);
	}
	// Test BearOffCheck method.

	@Test
	void testSetBearOffCounter() {
		gameData.setBearOffCounter("Black");
		gameData.setBearOffCounter("Black");
		gameData.setBearOffCounter("Red");
		assertEquals(2, gameData.getBearOffCounter()[0]);
		assertEquals(1, gameData.getBearOffCounter()[1]);
	}
	// Test SetBearOffCounter and GetBearOffCounter methods.
}
