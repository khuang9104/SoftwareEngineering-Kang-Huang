package junitTests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import backgammon.BackgammonMain;

class BackgammonMainTest {

	@Test
	void testMain() {
		String input = "test Commands.txt";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		ByteArrayOutputStream printContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(printContent));
		String expectedOutput = "switching to manual input mode.";
		try {
			BackgammonMain.main(null);
		} catch (NoSuchElementException e) {
			assertEquals(expectedOutput, printContent.toString().split("Commands executed completed, ")[1].trim());
		}
	}
	// Test the Main method by the reading test commands from comands.txt.
}
