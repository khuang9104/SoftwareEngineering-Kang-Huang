package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import backgammon.PipsCalculator;
import backgammon.Points;

class PipsCalculatorTest {
	
	private PipsCalculator pipsCalculator = new PipsCalculator();
	private Points points = new Points();
	
	@Test
	void testPipsCalculator() {
		assertNotNull(pipsCalculator);
	}
	// Test the constructor

	@Test
	void testDisplayPips() {
		ByteArrayOutputStream printContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(printContent));
		String expectedOutput = "Red pips = 167, Black pips = 167";
		pipsCalculator.displayPips(points);
		assertEquals(expectedOutput, printContent.toString().trim());
		
		points.clearPoints();
		ByteArrayOutputStream printContent_0 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(printContent_0));
		String expectedOutput_0 = "Red pips = 0, Black pips = 0";
		pipsCalculator.displayPips(points);
		assertEquals(expectedOutput_0, printContent_0.toString().trim());
	}
	// test displayPips and updatePips(private)

	@Test
	void testGameOverCheck() {
		assertFalse(pipsCalculator.gameOverCheck(points));
		points.clearPoints();
		assertTrue(pipsCalculator.gameOverCheck(points));
	}
	// test gameOverCheck

}
