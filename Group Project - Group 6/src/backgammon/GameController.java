package backgammon;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private static Dice dice = new Dice();
	private static int hint_case;
	private static ArrayList<Integer> roll_result = new ArrayList<Integer>();
	private static ArrayList<Integer> available_points = new ArrayList<Integer>();
	private static ArrayList<Integer> available_dice_points = new ArrayList<Integer>();
	private static ArrayList<String> bearoff_counter_red = new ArrayList<String>();
	private static ArrayList<String> bearoff_counter_black = new ArrayList<String>();
	private static ArrayList<String> available_options = new ArrayList<String>();
	private static String[] player_name = new String[2];
	private static Points points = new Points();
	private static boolean hit_detect = false;
	private static int[] bearoff_flag = new int[2];
	private static Gameboard gameboard = new Gameboard();
	private static PipsCalculator pipsCalculator = new PipsCalculator();
	private static Scanner scanner = new Scanner(System.in);
	// Attributes

	public GameController() {
	}

	public void rollCommand(int player_sequence) {
		boolean flag = false;
		String input;
		hint_case = 2;

		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name[player_sequence]);
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("roll")) {
				rollDice();
				printGameboard();
				if (roll_result.size() == 2) {
					System.out.printf("%s rolls %d & %d.\n", player_name[player_sequence], roll_result.get(0),
							roll_result.get(1));
				} else {
					System.out.printf("%s rolls %d & %d. Double! %s now has %d & %d & %d & %d.\n",
							player_name[player_sequence], roll_result.get(0), roll_result.get(1),
							player_name[player_sequence], roll_result.get(0), roll_result.get(1), roll_result.get(2),
							roll_result.get(3));
				}
				flag = true;
			} else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip")
					|| input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n",
						player_name[player_sequence]);
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
	}
	// Method: Roll twice dice and print results.

	public static void menuCommand(String input, int hint_case) {
		if (input.equalsIgnoreCase("quit")) {
			printGameboard();
			System.out.println("Quit the game successfully");
			System.exit(0);
		} else if (input.equalsIgnoreCase("hint")) {
			switch (hint_case) {
			case 1:
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' function is not available now.");
				System.out.println("Hint 2: Please enter a name to join the game.");
				break;
			case 2:
				printGameboard();
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' to check the pips");
				System.out.println("Hint 2: Please enter 'roll' to roll your dice.");
				break;
			case 3:
				printGameboard();
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' to check the pips");
				System.out.println(
						"Hint 2: Please enter a letter to move your checker. (i.e. enter 'A' to select option A.)");
				break;
			case 4:
				printGameboard();
				System.out.println("Hint 1: Enter 'quit' to quit the game, 'pip' to check the pips");
				System.out.println("Hint 2: There are nothing movement your can excute, enter 'P' to pass your turn.");
				break;
			}
		} else if (input.equalsIgnoreCase("pip")) {
			printGameboard();
			pipsCalculator.displayPips(points);
		}
	}
	// Method: Menu method. Based on different input, execute different commands.

	public void moveCommand(int player_sequence) {
		boolean flag = false;

		bearoff_flag = gameboard.bearOffCheck(points);

		while (roll_result.size() > 0 && possibleMove(player_sequence) == true) {
			printAvailableDicePoint(player_sequence);
			selectionInput(player_sequence);
		}
		// If there exist possible legal movement, print available dice point and allow user to select option to move.
		
		if (roll_result.size() > 0 && possibleMove(player_sequence) == false) {
			flag = false;
			String input;
			hint_case = 4;
			printAvailableDicePoint(player_sequence);
			while (flag == false) {
				System.out.println("There is nothing you can do, enter 'p' to pass your turn");
				input = scanner.nextLine();
				if (input.equalsIgnoreCase("p")) {
					flag = true;
				} else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip")
						|| input.equalsIgnoreCase("hint")) {
					menuCommand(input, hint_case);
				} else {
					System.out.println("Input error, there is nothing you can do, enter 'p' to pass your turn");
				}
			}
		}
		// If there is no possible legal movement, player needs to enter 'p' to pass their turn.
	}
	// Method: Move command framework.

	public void selectionInput(int player_sequence) {
		boolean flag = false;
		String input;
		int option_index;
		hint_case = 3;

		moveLegalityCheck(player_sequence);      // Check all possible legal movement and record them in available_points and available_dice_points.
		availableOptionPrint(player_sequence);   // Print all available movement option.

		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip") || input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				printAvailableDicePoint(player_sequence);
				availableOptionPrint(player_sequence);
				continue;
			// Go to menu and reprint info.
			} else if (available_options.contains(input.toUpperCase())) {
				option_index = available_options.indexOf(input.toUpperCase());   // Get the index of option in available_options and available_dice_points.
				moveChecker(player_sequence, available_points.get(option_index),
						available_dice_points.get(option_index));                // Execute movement.
				printGameboard();
				roll_result.remove(roll_result.indexOf(available_dice_points.get(option_index)));  // Remove the consumed dice point.
				if (hit_detect == true) {                                        // Hit detect was checked when execute movement(in method: moveChecker).
					System.out.println("Hit detected! Please check the bar.");
				}
				System.out.println("Checker move completed!");
				flag = true;
			} else {
				System.out.println("Entry error, please enter a correct letter or enter 'hint' for hints");
			}
		}
		if (pipsCalculator.gameOverCheck(points)) {
			System.out.printf("%s won the game!!!", player_name[player_sequence]);
			System.exit(0);
		}
		// Game over check.
	}
	// Method: Provide available movement option with player, allow play select one to execute.

	public static void availableOptionPrint(int player_sequence) {
		available_options.clear();
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
	}
	// Method: Print available movement option.

	public static boolean possibleMove(int player_sequence) {
		boolean flag = false;
		moveLegalityCheck(player_sequence);
		if (available_points.size() != 0) {
			flag = true;
		}
		return flag;
	}
	// Method: Check whether there is any available legal movement.

	public static void moveChecker(int player_sequence, int target_point, int moving_distance) {
		hit_detect = false;
		if (available_points.contains(target_point) && player_sequence == 0) {
			if (target_point - moving_distance > 0) {
				if (points.peekPoints(target_point - moving_distance) == "Red") {
					points.pushPoints(0, points.peekPoints(target_point - moving_distance));
					points.popPoints(target_point - moving_distance);
					hit_detect = true;
					points.pushPoints((target_point - moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
					// Move and hit.
				} else {
					points.pushPoints((target_point - moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
				}   // Normal move.
			}
			if (target_point - moving_distance <= 0) {
				points.popPoints(target_point);
				bearoff_counter_black.add("Black");
			}   // Bear-off
		}
		// Black player

		if (available_points.contains(target_point) && player_sequence == 1) {
			if (target_point + moving_distance < 25) {
				if (points.peekPoints(target_point + moving_distance) == "Black") {
					points.pushPoints(25, points.peekPoints(target_point + moving_distance));
					points.popPoints(target_point + moving_distance);
					hit_detect = true;
					points.pushPoints((target_point + moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
					// Move and hit.
				} else {
					points.pushPoints((target_point + moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
				}   // Normal move.
			}
			if (target_point + moving_distance >= 25) {
				points.popPoints(target_point);
				bearoff_counter_red.add("Red");
			}   // Bear-off
		}
		// Red player
	}

	public static void moveLegalityCheck(int player_sequence) {
		String color;
		int size;

		available_points.clear();
		available_dice_points.clear();
		bearoff_flag = gameboard.bearOffCheck(points);    // Updated the bear-off status.

		if (roll_result.size() >= 2 && (roll_result.get(0) == roll_result.get(1))) {
			size = 1;
		} else {
			size = roll_result.size();
		}
		// if the roll results is the same, only get one legal movement option, avoid duplication of options.

		if (player_sequence == 0) {
			color = "Black";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((points.peekPoints(i) == color) && (i - roll_result.get(j) > 0)
							&& ((points.peekPoints(i - roll_result.get(j)) == color)
									|| points.getSize(i - roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
						// Normal move.
					}
					if (bearoff_flag[0] == 1 && (points.peekPoints(i) == color) && (i - roll_result.get(j) == 0)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}   // Bear-off normal move.
				}
				if (bearoff_flag[0] == 1 && available_points.size() == 0) {
					for (int i = 6; i > 0; i--) {
						if (points.peekPoints(i) == color && (i - roll_result.get(j) < 0)) {
							available_points.add(i);
							available_dice_points.add(roll_result.get(j));
							break;
						}
					}
				}
				// Bear-off special move.
			}
		}
		// Black player

		if (player_sequence == 1) {
			color = "Red";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((points.peekPoints(i) == color) && (i + roll_result.get(j) < 25)
							&& ((points.peekPoints(i + roll_result.get(j)) == color)
									|| points.getSize(i + roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}   // Normal move.
					if (bearoff_flag[1] == 1 && (points.peekPoints(i) == color) && (i + roll_result.get(j) == 25)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}   // Bear-off normal move.
				}
				if (bearoff_flag[0] == 1 && available_points.size() == 0) {
					for (int i = 19; i < 25; i++) {
						if (points.peekPoints(i) == color && (i + roll_result.get(j) > 25)) {
							available_points.add(i);
							available_dice_points.add(roll_result.get(j));
							break;
						}
					}
				}
				// Bear-off special move.
			}
		}
		// Red player

		if (available_points.contains(25) || available_points.contains(0)) {
			for (int i = 0; i < available_points.size();) {
				if (available_points.get(i) != 25 && available_points.get(i) != 0) {
					available_points.remove(i);
					available_dice_points.remove(i);
				} else {
					i++;
				}
			}
		}
		// Bar priority. Remove all other options, only bar option left.
	}

	public static void rollDice() {
		roll_result.clear();
		roll_result.add(dice.rollDice());
		roll_result.add(dice.rollDice());
		if (roll_result.get(0) == roll_result.get(1)) {
			roll_result.add(roll_result.get(0));
			roll_result.add(roll_result.get(1));
		}
	}
	// Method: roll the dice

	public ArrayList<Integer> playSequenceRollCommand(String player_name) {
		boolean flag = false;
		String input;
		hint_case = 2;
		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("roll")) {
				rollDice();
				printGameboard();
				flag = true;
			} else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip")
					|| input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
		return roll_result;
	}
	// Method: roll one dice for play sequence determination.

	public static void printGameboard() {
		gameboard.printGameboard(points, bearoff_counter_red, bearoff_counter_black);
	}
	// Method: Print the game board.

	public void setNames(String[] player_name) {
		GameController.player_name = player_name;
		gameboard.setNames(player_name);
	}
	// Method: Set name and pass the names to game board.

	public static void printAvailableDicePoint(int player_sequence) {
		System.out.printf("It is %s's turn.\n", player_name[player_sequence]);
		System.out.print("Available dice point: ");
		for (int i = 0; i < roll_result.size(); i++) {
			System.out.print(roll_result.get(i) + " ");
		}
		System.out.println("");
	}
	// Method: Print available dice point.
}
