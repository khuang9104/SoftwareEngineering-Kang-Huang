package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backgammon.Points;

class PointsTest {
	
	private Points points = new Points();

	@Test
	void testPoints() {
		assertNotNull(points);
	}
	// test the constructor

	@Test
	void testPushPoints() {
		for (int i = 0; i < 26; i++) {
			points.clearPoints();
			points.pushPoints(i, "Black");
			assertEquals("Black", points.peekPoints(i));
		}
		for (int i = 0; i < 26; i++) {
			points.clearPoints();
			points.pushPoints(i, "Red");
			assertEquals("Red", points.peekPoints(i));
		}
	}
	// Test the pushPoints method
	// Method: push a checker into a specific point

	@Test
	void testPopPoints() {
		for (int i = 0; i < 26; i++) {
			points.clearPoints();
			points.pushPoints(i, "Black");
			assertEquals("Black", points.popPoints(i));
		}
		for (int i = 0; i < 26; i++) {
			points.clearPoints();
			assertEquals("empty", points.popPoints(i));
		}
	}
	// Test the popPoints method
	// Method: pop a checker from a specific point.

	@Test
	void testPeekPoints() {
		assertEquals("Red", points.peekPoints(1));
		points.clearPoints();
		for (int i = 0; i < 26; i++) {
			assertEquals("empty", points.peekPoints(i));
		}
	}
	// Test the peekPoints method
	// Method: peek a checker from a specific point.

	@Test
	void testGetSize() {
		points.clearPoints();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 15; j++) {
				assertEquals(j, points.getSize(i));
				points.pushPoints(i, "Black");
			}
		}
	}
	// Test the getSize method
	// Method: check how many checkers in specific point. Return the result.

	@Test
	void testClearPoints() {
		for (int i = 0; i < 26; i++) {
			points.pushPoints(i, "Black");
		}
		points.clearPoints();
		for (int i = 0; i < 26; i++) {
			assertEquals("empty", points.peekPoints(i));
		}
	}
	// Test the clearPoints method
	// Method: Clear all checkers in all points.
}
