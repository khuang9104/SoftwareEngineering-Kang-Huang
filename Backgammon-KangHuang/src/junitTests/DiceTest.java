package junitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backgammon.Dice;

class DiceTest {
	private Dice dice;
	
	@BeforeEach
	void setUp() {
		dice = new Dice();
	}

	@Test
	void testDice() {
		assertNotNull(dice);
	}
	// Test the constructor.

	@Test
	void testRollDice() {
		for (int i = 0; i < 100; i++) {
			int result = dice.rollDice();
			assertTrue(result <= 6 || result >= 1 );
		}
	}
	// Test RollDice method.
	// Method: Roll dice once and return the result
}
