
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

import java.io.InputStream;
import java.util.Scanner;

public class InputScanner {

	private boolean test_mode = false;
	private Scanner scanner;
	private InputStream testfile;

	public InputScanner() {
		this.scanner = new Scanner(System.in);
	}
	// Constructor

	public String inputScan() {
		String input = null;
		if (test_mode == true && scanner.hasNext() == true) {
			input = scanner.nextLine();
			System.out.println(input);
		} // Test mode: text file input.
		else if (test_mode == true && scanner.hasNext() == false) {
			scanner = new Scanner(System.in);
			this.test_mode = false;
			System.out.println("Commands executed completed, switching to manual input mode.");
			input = scanner.nextLine();
		} // In test mode, if there is no further commands from specific text file can be
			// executed, turn off the test mode and switching to console input mode.
		else if (test_mode == false && scanner.hasNext() == true) {
			input = scanner.nextLine();
		} // Console input
		return input;
	}
	// Method: Scan the command from specific text file or console.

	public boolean testModeCheck() {
		return test_mode;
	}
	// Method: Check whether test mode on or off.

	public String initialScanner() {
		String input = null;
		boolean flag = false;

		while (flag == false) {
			input = scanner.nextLine();
			if (input.matches("test(.)+")) {
				if (input.equalsIgnoreCase("test Commands.txt")) {
					testfile = this.getClass().getResourceAsStream("/testfiles/Commands.txt");
					this.scanner = new Scanner(testfile);
					input = scanner.nextLine();
					this.test_mode = true;
					flag = true;
				} else {
					System.out.println(
							"File not found, please input 'test <filename>' to activate the test mode.(i.e. 'test Commands.txt')");
					this.test_mode = false;
				}
			} else {
				this.scanner = new Scanner(System.in);
				this.test_mode = false;
				flag = true;
			}
		}
		return input;
	}
	// Method: Initial the input scanner.
	// If the input is equal to a test command, set a text file scanner to scan the commands in specific text file. test mode turn on.
	// Test command. A “test <filename>” command which performs the commands in the given text file.
	// i.e. 'test Commands.txt'
	// If not, set a console scanner to scan users input in the console. test mode turn off.
	
	public void resetScanner() {
		this.scanner = new Scanner(System.in);
	}
	// Test Method: Reset the input scanner.
}
