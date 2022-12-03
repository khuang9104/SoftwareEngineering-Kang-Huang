package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import backgammon.GameController;

class GameControllerTest {

	GameController gameController = new GameController();

	@Test
	void testGameController() {
		assertNotNull(gameController);
	}
	// Test the constructor
 
	@Before
	void testSetPlayerNames() {
		String input = "pip";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		try {
			gameController.resetScanner();
			gameController.setPlayerNames();
		} catch (NoSuchElementException e) {
			assertTrue(gameController.getPlayerNames()[0] == null);
		}

		input = "hint";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		try {
			gameController.resetScanner();
			gameController.setPlayerNames();
		} catch (NoSuchElementException e) {
			assertTrue(gameController.getPlayerNames()[0] == null);
		}
	}
	// Test the SetPlayerNames method.

	@Test
	void testRollCommand() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			String input = "roll";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			gameController.resetScanner();
			gameController.rollCommand(0);
		}
		String input = "dice33";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.rollCommand(0);
		assertTrue(roll_result.get(0) == 3 && roll_result.get(1) == 3 && roll_result.get(2) == 3
				&& roll_result.get(3) == 3);

		input = "dice 3 3";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.rollCommand(0);
		assertTrue(roll_result.get(0) == 3 && roll_result.get(1) == 3 && roll_result.get(2) == 3
				&& roll_result.get(3) == 3);

		input = "dice25";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.rollCommand(0);
		assertTrue(roll_result.get(0) == 2 && roll_result.get(1) == 5);

		input = "dice 2 5";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.rollCommand(0);
		assertTrue(roll_result.get(0) == 2 && roll_result.get(1) == 5);
	}
	// Test RollCommand method

	@Test
	void testPlaySequenceRollCommand() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		String input = "roll";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.playSequenceRollCommand("Kang");

		input = "dice3";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.playSequenceRollCommand("Kang");
		assertTrue(roll_result.get(0) == 3);

		input = "dice 3";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		roll_result = gameController.playSequenceRollCommand("Kang");
		assertTrue(roll_result.get(0) == 3);
	}
	// Test PlaySequenceRollCommand method

	@Test
	void testMoveCommand_A() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		gameController.clearPoints();
		for (int i = 0; i < 1; i++) {
			gameController.pushPoints(1, "Red");
			gameController.pushPoints(4, "Black");
			gameController.pushPoints(12, "Black");
		}
		roll_result.add(2);
		roll_result.add(1);
		String input = "a\na";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(0, roll_result);
		assertEquals("Black", gameController.peekPoints(1));
		assertEquals("Red", gameController.peekPoints(0));
	}
	// Test MoveCommand method A. (Hit test and bar test)

	@Test
	void testMoveCommand_B() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		gameController.clearPoints();
		for (int i = 0; i < 3; i++) {
			gameController.pushPoints(1, "Black");
			gameController.pushPoints(2, "Black");
			gameController.pushPoints(3, "Black");
			gameController.pushPoints(22, "Red");
			gameController.pushPoints(23, "Red");
			gameController.pushPoints(24, "Red");
		}
		assertEquals(0, gameController.getBearOffCounter()[0]);
		assertEquals(0, gameController.getBearOffCounter()[1]);
		roll_result.add(6);
		roll_result.add(3);
		String input = "A\na";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(0, roll_result);
		assertEquals(2, gameController.getBearOffCounter()[0]);
		assertEquals(0, gameController.getBearOffCounter()[1]);

		roll_result.clear();
		roll_result.add(6);
		roll_result.add(3);
		input = "A\na";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(1, roll_result);
		assertEquals(2, gameController.getBearOffCounter()[0]);
		assertEquals(2, gameController.getBearOffCounter()[1]);
	}
	// Test MoveCommand method B. (bear-off test)

	@Test
	void testMoveCommand_C() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		gameController.clearPoints();
		for (int i = 0; i < 1; i++) {
			gameController.pushPoints(25, "Black");
			gameController.pushPoints(0, "Red");
			gameController.pushPoints(5, "Black");
			gameController.pushPoints(20, "Red");
		}
		roll_result.add(3);
		roll_result.add(3);
		String input = "A\na";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(0, roll_result);
		assertEquals("Black", gameController.peekPoints(22));
		assertEquals("Black", gameController.peekPoints(2));

		roll_result.clear();
		roll_result.add(3);
		roll_result.add(3);
		input = "A\na";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(1, roll_result);
		assertEquals("Red", gameController.peekPoints(6));
	}
	// Test MoveCommand method C. (bar priority test)

	@Test
	void testMoveCommand_D() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		gameController.clearPoints();
		for (int i = 0; i < 2; i++) {
			gameController.pushPoints(16, "Black");
			gameController.pushPoints(15, "Black");
			gameController.pushPoints(6, "Black");

		}
		for (int i = 0; i < 1; i++) {
			gameController.pushPoints(13, "Red");
			gameController.pushPoints(1, "Red");
		}
		roll_result.add(2);
		roll_result.add(3);
		String input = "A";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameController.resetScanner();
		gameController.moveCommand(1, roll_result);
		assertEquals("Red", gameController.peekPoints(4));
	}
	// Test MoveCommand method D. (either number can be played but not both)
}
