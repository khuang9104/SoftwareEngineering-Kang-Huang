package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backgammon.GameData;
import backgammon.Gameboard;


class ObserverTest {

	GameData gameData = new GameData();
	Gameboard gameboard = new Gameboard(gameData);

	@Test
	void testUpdate() {
		assertEquals("empty",gameData.peekPoints(10));
		assertEquals("empty",gameData.peekPoints(25));
		gameData.pushPoints(10, "Red");
		gameData.pushPoints(25, "Black");
		assertEquals("Red",gameData.peekPoints(10));
		assertEquals("Black",gameData.peekPoints(25));
	}
	// Test the update function
}
