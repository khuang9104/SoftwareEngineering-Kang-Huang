package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import backgammon.InputScanner;

class InputScannerTest {
	
	InputScanner inputScanner = new InputScanner();

	@Test
	void testInputScanner() {
		assertNotNull(inputScanner);
	}
	// Test the constructor

	@Test
	void testInputScan() {
		String input = "test";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		inputScanner.resetScanner();
		assertEquals("test", inputScanner.inputScan());
	}
	// Test the InputScan method
	
	@Test
	void testInitialScanner() {
		String output;
		
		String input = "test \ntest Commands.txt";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		try {
			inputScanner.resetScanner();
			output = inputScanner.initialScanner();
			System.out.println(output);
			assertTrue(inputScanner.testModeCheck());
			for (int i = 0; i < 60; i++) {
				inputScanner.inputScan();
			}
		} catch (NoSuchElementException e) {
			assertFalse(inputScanner.testModeCheck());
		}
		
		String input1 = "t";
		in = new ByteArrayInputStream(input1.getBytes());
		System.setIn(in);
		inputScanner.resetScanner();
		assertEquals("t",inputScanner.initialScanner());
	}
	// Test the InputScan method and testModeCheck method
}
