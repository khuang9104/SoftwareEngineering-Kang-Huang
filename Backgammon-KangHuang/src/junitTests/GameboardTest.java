package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backgammon.GameData;
import backgammon.Gameboard;

class GameboardTest {

	GameData gameData = new GameData();
	Gameboard gameboard = new Gameboard(gameData);

	@Test
	void testGameboard() {
		assertNotNull(gameboard);
	}
	// Test the constructor.
	// The methods related to Observer have been tested in corresponding interface class JUnit test.

	@Test
	void testPrintGameboard() {
		gameData.setPlayerNames("Kang", 0);
		gameData.setPlayerNames("Wenwei", 1);
		gameData.setBearOffCounter("Black");
		gameData.setBearOffCounter("Black");
		gameData.setBearOffCounter("Red");
		gameboard.printGameboard();

		gameData.clearPoints();
		for (int i = 0; i < 3; i++) {
			gameData.pushPoints(1,"Black");
			gameData.pushPoints(3,"Black");
			gameData.pushPoints(22,"Red");
			gameData.pushPoints(23,"Red");
			gameData.pushPoints(25,"Black");
			gameData.pushPoints(0,"Red");
		}
		for (int j = 0; j < 9; j++) {
			gameData.pushPoints(19, "Red");
			gameData.pushPoints(1, "Black");
		}
		gameboard.printGameboard();
	}
	// Check the UI manually
}
