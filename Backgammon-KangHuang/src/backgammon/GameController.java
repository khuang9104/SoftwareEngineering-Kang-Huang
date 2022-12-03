
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

import java.util.ArrayList;

public class GameController {

	private static int hint_case;
	private boolean hit_detect = false;
	private Dice dice;
	private GameData gameData;
	private Gameboard gameboard;
	private PipsCalculator pipsCalculator;
	private InputScanner inputScanner;

	public GameController() {
		this.dice = new Dice();
		this.gameData = new GameData();
		this.gameboard = new Gameboard(gameData);
		this.pipsCalculator = new PipsCalculator();
		this.inputScanner = new InputScanner();
	}
	// Constructor

	public ArrayList<Integer> rollCommand(int player_sequence) {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		boolean flag = false;
		String input;
		hint_case = 2;

		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n",
				gameData.getPlayerNames()[player_sequence]);
		while (flag == false) {
			flag = false;
			input = inputScanner.inputScan();
			if (input.equalsIgnoreCase("roll")) {
				roll_result = rollDice();
				gameboard.printGameboard();
				if (roll_result.size() == 2) {
					System.out.printf("%s rolls %d & %d.\n", gameData.getPlayerNames()[player_sequence],
							roll_result.get(0), roll_result.get(1));
				}
				// Non-Double
				else {
					System.out.printf("%s rolls %d & %d. Double! %s now has %d & %d & %d & %d.\n",
							gameData.getPlayerNames()[player_sequence], roll_result.get(0), roll_result.get(1),
							gameData.getPlayerNames()[player_sequence], roll_result.get(0), roll_result.get(1),
							roll_result.get(2), roll_result.get(3));
				}
				// Double
				flag = true;
				// Roll twice dice and print results.
			} else if (input.matches("dice[1-6]{1}+[1-6]{1}+")
					|| input.matches("dice(\\s){1}+[1-6]{1}+(\\s){1}+[1-6]{1}+")) {
				roll_result = setDice(input);
				gameboard.printGameboard();
				if (roll_result.size() == 2) {
					System.out.printf("%s rolls %d & %d.\n", gameData.getPlayerNames()[player_sequence],
							roll_result.get(0), roll_result.get(1));
				}
				// Non-Double
				else {
					System.out.printf("%s rolls %d & %d. Double! %s now has %d & %d & %d & %d.\n",
							gameData.getPlayerNames()[player_sequence], roll_result.get(0), roll_result.get(1),
							gameData.getPlayerNames()[player_sequence], roll_result.get(0), roll_result.get(1),
							roll_result.get(2), roll_result.get(3));
				}
				// Double
				flag = true;
			}
			// Test command. 'dice <int> <int>' command to cause the subsequent dice roll to
			// equal the given numbers.
			// i.e. dice 2 3 or dice23 to get dice result 2 & 3.
			else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip")
					|| input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n",
						gameData.getPlayerNames()[player_sequence]);
			}
			// Other commands available.
			else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'hint' for more info.");
			}
			// Prompt for entry error.
		}
		return roll_result;
	}
	// Method: Roll twice dice and print results.

	private ArrayList<Integer> setDice(String input) {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		if (input.charAt(4) == ' ') {
			if (Character.getNumericValue(input.charAt(5)) != Character.getNumericValue(input.charAt(7))) {
				roll_result.add(Character.getNumericValue(input.charAt(5)));
				roll_result.add(Character.getNumericValue(input.charAt(7)));
			}
			// Non-Double
			else {
				for (int i = 0; i < 4; i++) {
					roll_result.add(Character.getNumericValue(input.charAt(5)));
				}
			}
			// Double
		}
		// Test command. 'dice <int> <int>' command to cause the subsequent dice roll to
		// equal the given numbers.
		// i.e. dice 2 3 to get dice result 2 & 3.
		else {
			if (Character.getNumericValue(input.charAt(4)) != Character.getNumericValue(input.charAt(5))) {
				roll_result.add(Character.getNumericValue(input.charAt(4)));
				roll_result.add(Character.getNumericValue(input.charAt(5)));
			}
			// Non-Double
			else {
				for (int i = 0; i < 4; i++) {
					roll_result.add(Character.getNumericValue(input.charAt(4)));
				}
			}
			// Double
		}
		// dice23 is available too.
		// Test command. 'dice<int><int>' command to cause the subsequent dice roll to
		// equal the given numbers.
		// i.e. dice23 to get dice result 2 & 3.
		return roll_result;
	}
	// Method: Cause the subsequent dice roll to equal the given numbers.

	private void menuCommand(String input, int hint_case) {
		if (input.equalsIgnoreCase("quit")) {
			gameboard.printGameboard();
			System.out.println("Quit the game successfully");
			System.exit(0);
			// Quit the game.
		} else if (input.equalsIgnoreCase("hint")) {
			switch (hint_case) {
			case 1:
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' function is not available now.");
				System.out.println("Hint 2: Please enter a name to join the game.");
				break;
			case 2:
				gameboard.printGameboard();
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' to check the pips");
				System.out.println("Hint 2: Please enter 'roll' to roll your dice.");
				break;
			case 3:
				gameboard.printGameboard();
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' to check the pips");
				System.out.println(
						"Hint 2: Please enter a letter to move your checker. (i.e. enter 'A' to select option A.)");
				break;
			}
		}
		// Hints in different situation.
		else if (input.equalsIgnoreCase("pip")) {
			gameboard.printGameboard();
			pipsCalculator.displayPips(gameData.getPoints());
		}
		// Show pips for both players
	}
	// Method: Integrated menu. Based on different input, execute different commands.

	public void moveCommand(int player_sequence, ArrayList<Integer> roll_result) {

		while (roll_result.size() > 0 && possibleMove(player_sequence, roll_result) == true) {
			printAvailableDicePoint(player_sequence, roll_result);
			selectionInput(player_sequence, roll_result);
		}
		// If there exist possible legal movement, print available dice point and allow
		// user to select option to move.

		if (roll_result.size() > 0 && possibleMove(player_sequence, roll_result) == false) {
			printAvailableDicePoint(player_sequence, roll_result);
			System.out.println("There is nothing you can do, turn skip.");
		}
		// If there is no possible legal movement, player have to pass their turn.
	}
	// Method: Move command framework.

	private void selectionInput(int player_sequence, ArrayList<Integer> roll_result) {
		ArrayList<ArrayList<Integer>> available_result;
		ArrayList<Integer> available_points;
		ArrayList<Integer> available_dice_points;
		ArrayList<String> available_options;
		boolean flag = false;
		String input;
		int option_index;
		hint_case = 3;

		available_result = moveLegalityCheck(player_sequence, gameData, roll_result);
		available_points = available_result.get(0);
		available_dice_points = available_result.get(1);
		// Check all possible legal movement and record them in available_points and available_dice_points.
		// Except this rule: “If either number can be played but not both, the player must play the larger one.”.
		int roll_result_larger = onlyOneMoveCheck(player_sequence, available_points, available_dice_points, roll_result);
		// Check available options for following rules:
		// “If either number can be played but not both, the player must play the larger one.”
		if (roll_result_larger != 0) {
			for (int i = 0; i < available_points.size();) {
				if (available_dice_points.get(i) != roll_result_larger) {
					available_points.remove(i);
					available_dice_points.remove(i);
				} else {
					i++;
				}
			}
		}
		// If either number can be played but not both, load the larger dice result
		// options from the temp array lists.
		available_options = availableOptionPrint(player_sequence, available_points, available_dice_points);
		// Print all available movement option.
		while (flag == false) {
			flag = false;
			input = inputScanner.inputScan();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip") || input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				printAvailableDicePoint(player_sequence, roll_result);
				availableOptionPrint(player_sequence, available_points, available_dice_points);
				continue;
				// Go to menu and reprint info.
			} else if (available_options.contains(input.toUpperCase())) {
				option_index = available_options.indexOf(input.toUpperCase());
				// Get the index of option in available_options and available_dice_points.
				moveChecker(player_sequence, available_points.get(option_index),
						available_dice_points.get(option_index), available_points, gameData);
				// Execute movement.
				gameboard.printGameboard();
				roll_result.remove(roll_result.indexOf(available_dice_points.get(option_index)));
				// Remove the consumed dice point.
				if (hit_detect == true) {
					System.out.println("Hit detected! Please check the bar.");
				}
				// Hit detect was checked when execute movement(in method: moveChecker).
				System.out.println("Checker move completed!");
				flag = true;
			} else {
				System.out.println("Entry error, please enter a correct letter or enter 'hint' for hints");
			} // Prompt for entry error.
		}
		if (pipsCalculator.gameOverCheck(gameData.getPoints())) {
			System.out.printf("%s won the game!!!", gameData.getPlayerNames()[player_sequence]);
			System.exit(0);
		}
		// Game over check.
	}
	// Method: Provide available movement option with player, allow play select one to execute.

	private ArrayList<String> availableOptionPrint(int player_sequence, ArrayList<Integer> available_points,
			ArrayList<Integer> available_dice_points) {
		ArrayList<String> available_options = new ArrayList<String>();
		System.out.println("Please select one of the following options to move your checker:");

		for (int i = 0; i < available_points.size(); i++) {
			if (player_sequence == 0 && (available_points.get(i) == 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): You have to move checker from bar first, move %d steps foward from bar.\n",
						available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
				// Option for Bar (Bar first).
			} else if (player_sequence == 0 && (available_points.get(i) - available_dice_points.get(i) > 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase() + "): Points: %d, move %d steps foward.\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
				// Option for normal movement.
			} else if (player_sequence == 0 && (available_points.get(i) - available_dice_points.get(i) <= 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): Points: %d, consume dice point %d to bear-off!\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			}
			// Option for bear-off movement.
			// Options for Black Player

			if (player_sequence == 1 && (available_points.get(i) == 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): You have to move checker from bar first, move %d steps foward from bar.\n",
						available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
				// Option for Bar (Bar first).
			} else if (player_sequence == 1 && (available_points.get(i) + available_dice_points.get(i) < 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase() + "): Points: %d, move %d steps foward.\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
				// Option for normal movement.
			} else if (player_sequence == 1 && (available_points.get(i) + available_dice_points.get(i) >= 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): Points: %d, consume dice point %d to bear-off!\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			}
			// Option for bear-off movement.
			// Options for Red Player
		}
		return available_options;
	}
	// Method: Print available movement option.

	private boolean possibleMove(int player_sequence, ArrayList<Integer> roll_result) {
		boolean flag = false;
		ArrayList<ArrayList<Integer>> available_result;
		available_result = moveLegalityCheck(player_sequence, gameData, roll_result);
		if (available_result.get(0).size() != 0) {
			flag = true;
		}
		return flag;
	}
	// Method: Check whether there is available legal movement.

	private GameData moveChecker(int player_sequence, int target_point, int moving_distance,
			ArrayList<Integer> available_points, GameData gameData) {
		hit_detect = false;
		if (available_points.contains(target_point) && player_sequence == 0) {
			if (target_point - moving_distance > 0) {
				if (gameData.peekPoints(target_point - moving_distance) == "Red") {
					gameData.pushPoints(0, gameData.peekPoints(target_point - moving_distance));
					gameData.popPoints(target_point - moving_distance);
					hit_detect = true;
					gameData.pushPoints((target_point - moving_distance), gameData.peekPoints(target_point));
					gameData.popPoints(target_point);
					// Normal move (hit).
				} else {
					gameData.pushPoints((target_point - moving_distance), gameData.peekPoints(target_point));
					gameData.popPoints(target_point);
				} // Normal move.
			}
			if (target_point - moving_distance <= 0) {
				gameData.popPoints(target_point);
				gameData.setBearOffCounter("Black");
			} // Bear-off
		}
		// Black player

		if (available_points.contains(target_point) && player_sequence == 1) {
			if (target_point + moving_distance < 25) {
				if (gameData.peekPoints(target_point + moving_distance) == "Black") {
					gameData.pushPoints(25, gameData.peekPoints(target_point + moving_distance));
					gameData.popPoints(target_point + moving_distance);
					hit_detect = true;
					gameData.pushPoints((target_point + moving_distance), gameData.peekPoints(target_point));
					gameData.popPoints(target_point);
					// Normal move (hit).
				} else {
					gameData.pushPoints((target_point + moving_distance), gameData.peekPoints(target_point));
					gameData.popPoints(target_point);
				} // Normal move.
			}
			if (target_point + moving_distance >= 25) {
				gameData.popPoints(target_point);
				gameData.setBearOffCounter("Red");
			} // Bear-off
		}
		// Red player
		return gameData;
	}
	// Method: Move the checker.

	private ArrayList<ArrayList<Integer>> moveLegalityCheck(int player_sequence, GameData gameData,
			ArrayList<Integer> roll_result) {
		int size;
		String color;
		ArrayList<Integer> available_points = new ArrayList<Integer>();
		ArrayList<Integer> available_dice_points = new ArrayList<Integer>();
		;
		ArrayList<ArrayList<Integer>> available_result = new ArrayList<ArrayList<Integer>>();

		if (roll_result.size() >= 2 && (roll_result.get(0) == roll_result.get(1))) {
			size = 1;
		} else {
			size = roll_result.size();
		}
		// if the roll results are the same, only display the legal movement options for
		// one, avoid duplication of options.

		if (player_sequence == 0) {
			color = "Black";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((gameData.peekPoints(i) == color) && (i - roll_result.get(j) > 0)
							&& ((gameData.peekPoints(i - roll_result.get(j)) == color)
									|| gameData.getSize(i - roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					// Check whether a normal move can be executed in each point.
					// If it is executable, record it and correspond dice point in to the array
					// lists.
					if (gameData.bearOffCheck()[0] == 1 && (gameData.peekPoints(i) == color)
							&& (i - roll_result.get(j) == 0)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					// Check whether a normal bear-off can be executed in each point.
					// If it is executable, record it and correspond dice point in to the array
					// lists.
				}
			}
			if (gameData.bearOffCheck()[0] == 1 && available_points.size() == 0) {
				for (int j = 0; j < size; j++) {
					for (int i = 5; i > 0; i--) {
						if (gameData.peekPoints(i) == color && (i - roll_result.get(j) < 0)) {
							available_points.add(i);
							available_dice_points.add(roll_result.get(j));
							break;
						}
					}
				}
			}
			// Check whether a special bear-off can be executed in each point.
			// If it is executable, record it and correspond dice point in to the array
			// lists.
			if (gameData.getSize(25) > 0) {
				for (int i = 0; i < available_points.size();) {
					if (available_points.get(i) != 25) {
						available_points.remove(i);
						available_dice_points.remove(i);
					} else {
						i++;
					}
				}
			}
			// Bar first. Remove all other options, only bar option left.
		}
		// Black player

		if (player_sequence == 1) {
			color = "Red";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((gameData.peekPoints(i) == color) && (i + roll_result.get(j) < 25)
							&& ((gameData.peekPoints(i + roll_result.get(j)) == color)
									|| gameData.getSize(i + roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					// Check whether a normal move can be executed in each point.
					// If it is executable, record it and correspond dice point in to the array
					// lists.
					if (gameData.bearOffCheck()[1] == 1 && (gameData.peekPoints(i) == color)
							&& (i + roll_result.get(j) == 25)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					// Check whether a normal bear-off can be executed in each point.
					// If it is executable, record it and correspond dice point in to the array
					// lists.
				}
			}
			if (gameData.bearOffCheck()[1] == 1 && available_points.size() == 0) {
				for (int j = 0; j < size; j++) {
					for (int i = 20; i < 25; i++) {
						if (gameData.peekPoints(i) == color && (i + roll_result.get(j) > 25)) {
							available_points.add(i);
							available_dice_points.add(roll_result.get(j));
							break;
						}
					}
				}
			}
			// Check whether a special bear-off can be executed in each point.
			// If it is executable, record it and correspond dice point in to the array
			// lists.
			if (gameData.getSize(0) > 0) {
				for (int i = 0; i < available_points.size();) {
					if (available_points.get(i) != 0) {
						available_points.remove(i);
						available_dice_points.remove(i);
					} else {
						i++;
					}
				}
			}
			// Bar first. Remove all other options, only bar option left.
		}
		// Red player
		available_result.add(available_points);
		available_result.add(available_dice_points);
		return available_result;
	}
	// Check all possible legal movement and record them in available_points and available_dice_points.

	private int onlyOneMoveCheck(int player_sequence, ArrayList<Integer> available_points,
			ArrayList<Integer> available_dice_points, ArrayList<Integer> roll_result) {
		boolean flag = false;
		int roll_result_larger = 0;
		ArrayList<Integer> roll_result_temp;

		if (roll_result.size() == 2 && roll_result.get(0) != roll_result.get(1) && available_points.size() >= 2) {
			if (roll_result.get(0) > roll_result.get(1)) {
				roll_result_larger = roll_result.get(0);
			} else {
				roll_result_larger = roll_result.get(1);
			}
			// Get the larger dice result.
			for (int i = 0; i < available_points.size(); i++) {
				flag = false;
				roll_result_temp = new ArrayList<Integer>();
				for (int j = 0; j < roll_result.size(); j++) {
					roll_result_temp.add(roll_result.get(j));
				}
				GameData gameData_temp = cloneGameData(gameData);
				gameData_temp = moveChecker(player_sequence, available_points.get(i), available_dice_points.get(i), available_points, gameData_temp);
				// Simulation a given movement
				roll_result_temp.remove(roll_result.indexOf(available_dice_points.get(i)));
				ArrayList<ArrayList<Integer>> available_results = moveLegalityCheck(player_sequence, gameData_temp, roll_result_temp);
				// Check whether a movement option is executed and causes another dice result to
				// become unavailable.
				if (available_results.get(0).size() == 0) {
					flag = true;
				}
				// Restore the roll_result.
			}
		}
		if (flag == false) {
			roll_result_larger = 0;
		}
		// If only one movement are available, return the roll_result_larger value,
		// otherwise set roll_result_larger to 0 and return.
		return roll_result_larger;
	}
	// Check available options for following rules:
	// “If either number can be played but not both, the player must play the larger one.”
	
	private GameData cloneGameData(GameData gameData) {
		GameData GameData_clone = new GameData();
		GameData_clone.clearPoints();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < gameData.getSize(i); j++) {
				GameData_clone.pushPoints(i, gameData.peekPoints(i));
			}
		}
		return GameData_clone;
	}
	// Clone the gameData.

	private ArrayList<Integer> rollDice() {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		roll_result.add(dice.rollDice());
		roll_result.add(dice.rollDice());
		if (roll_result.get(0) == roll_result.get(1)) {
			roll_result.add(roll_result.get(0));
			roll_result.add(roll_result.get(1));
		}
		return roll_result;
	}
	// Method: roll the dice twice.

	public ArrayList<Integer> playSequenceRollCommand(String player_name) {
		ArrayList<Integer> roll_result = new ArrayList<Integer>();
		boolean flag = false;
		String input;
		hint_case = 2;

		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
		while (flag == false) {
			flag = false;
			input = inputScanner.inputScan();
			if (input.equalsIgnoreCase("roll")) {
				roll_result = rollDice();
				gameboard.printGameboard();
				flag = true;
			}
			// Roll the dice.
			else if (input.matches("dice[1-6]{1}+") || input.matches("dice(\\s){1}+[1-6]{1}")) {
				if (input.charAt(4) == ' ') {
					roll_result.add(Character.getNumericValue(input.charAt(5)));
				} else {
					roll_result.add(Character.getNumericValue(input.charAt(4)));
				}
				gameboard.printGameboard();
				flag = true;
			}
			// Test command. 'dice <int>' command to cause the subsequent dice roll to equal
			// the given number.
			// i.e. dice 2 or dice2 to get dice result 2.
			else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip")
					|| input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
			}
			// Other commands available.
			else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'hint' for more info.");
			}
			// Prompt for entry error.
		}
		return roll_result;
	}
	// Method: roll for play sequence determination.

	public void setPlayerNames() {
		boolean flag_b = false;
		boolean flag_r = false;
		String input;
		int hint_case = 1;

		while (flag_b == false) {
			System.out.println("Please input the name of player 1 (Black).");
			input = inputScanner.initialScanner();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name.");
			}
			// If the input is equal to a test command, construct a text file scanner to
			// scan the commands in specific text file.
			else if (!input.equals("") && !input.equals("pip")) {
				// inputScanner = new InputScanner(input, false);
				gameData.setPlayerNames(input, 0);
				flag_b = true;
			}
			// construct a console scanner to scan users input in the console.
			else if (input.equals("pip")) {
				System.out.println("'pip' function is not available now.");
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name.");
			}
			// Prompt for unavailable command.
		}
		System.out.println(gameData.getPlayerNames()[0] + " chooses Black.");

		while (flag_r == false) {
			System.out.println("Please input the name of player 2 (Red).");
			input = inputScanner.inputScan();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name.");
			}
			// Other commands available.
			else if (!input.equals("") && !input.equals("pip")) {
				gameData.setPlayerNames(input, 1);
				flag_r = true;
			}
			// Scan player's name.
			else if (input.equals("pip")) {
				System.out.println("'pip' function is not available now.");
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name.");
			}
			// Prompt for unavailable command.
		}
		System.out.println(gameData.getPlayerNames()[1] + " chooses Red.");
		gameboard.printGameboard();
	}
	// Method: Set name and pass the names to game board.

	private void printAvailableDicePoint(int player_sequence, ArrayList<Integer> roll_result) {
		System.out.printf("It is %s's turn.\n", gameData.getPlayerNames()[player_sequence]);
		System.out.print("Available dice point: ");
		for (int i = 0; i < roll_result.size(); i++) {
			System.out.print(roll_result.get(i) + " ");
		}
		System.out.println("");
	}
	// Method: Print available dice points.

	public String[] getPlayerNames() {
		return gameData.getPlayerNames();
	}
	// Method: get players' name.

	public void resetScanner() {
		inputScanner.resetScanner();
	}
	// Test Method: Reset scanner.

	public void testPrint() {
		gameboard.printGameboard();
	}
	// Test Method: test print.
	
	public void pushPoints(int pointsNum, String checker_color) {
		gameData.pushPoints(pointsNum, checker_color);
	}
	// Test Method: push points.
	
	public String peekPoints(int pointsNum) {
		return gameData.peekPoints(pointsNum);
	}
	// Test Method: peek points.
	
	public void clearPoints() {
		gameData.clearPoints();
	}
	// Test Method: Clear points
	
	public int[] getBearOffCounter() {
		return gameData.getBearOffCounter();
	}
	// Test Method: get BearOffCounter
}
