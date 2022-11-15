package Backgammon_Sprint12;
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

	public GameController() {
	}
	// It contains the interface to provide the user to enter roll to roll the dice and it doesn't return any value
	// It picks a player sequence int argument to Identify the player.
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
	// Method contains the menu for the game and triggers events basing on the user selection.
	// It picks to args, a string input to capture user's selection and int to provide a hint.
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
	// A void method used to move dices from and to different locations.
	public void moveCommand(int player_sequence) {
		boolean flag = false;

		bearoff_flag = gameboard.bearOffCheck(points);

		while (roll_result.size() > 0 && possibleMove(player_sequence) == true) {
			printAvailableDicePoint(player_sequence);
			selectionInput(player_sequence);
		}
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
	}
	// Takes the player position arg and gets the users selection from the console
	public void selectionInput(int player_sequence) {
		boolean flag = false;
		String input;
		int option_index;
		hint_case = 3;

		moveLegalityCheck(player_sequence);
		availableOptionPrint(player_sequence);

		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("pip") || input.equalsIgnoreCase("hint")) {
				menuCommand(input, hint_case);

				printAvailableDicePoint(player_sequence);
				availableOptionPrint(player_sequence);
				continue;
			} else if (available_options.contains(input.toUpperCase())) {
				option_index = available_options.indexOf(input.toUpperCase());
				moveChecker(player_sequence, available_points.get(option_index),
						available_dice_points.get(option_index));
				printGameboard();
				roll_result.remove(roll_result.indexOf(available_dice_points.get(option_index)));
				if (hit_detect == true) {
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
	}
	// It gives available  options in which the dice can be rolled.
	public static void availableOptionPrint(int player_sequence) {
		available_options.clear();
		System.out.println("Legal moves available \n");
		System.out.println("Please select one of the following options to move your checker:");
		for (int i = 0; i < available_points.size(); i++) {

			if (player_sequence == 0 && (available_points.get(i) == 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): You have to move checker from bar first, move %d steps foward from bar.\n",
						available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			} else if (player_sequence == 0 && (available_points.get(i) - available_dice_points.get(i) > 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase() + "): Points: %d, move %d steps foward.\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			} else if (player_sequence == 0 && (available_points.get(i) - available_dice_points.get(i) <= 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): Points: %d, consume dice point %d to bear-off!\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			}

			if (player_sequence == 1 && (available_points.get(i) == 0)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): You have to move checker from bar first, move %d steps foward from bar.\n",
						available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			} else if (player_sequence == 1 && (available_points.get(i) + available_dice_points.get(i) < 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase() + "): Points: %d, move %d steps foward.\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			} else if (player_sequence == 1 && (available_points.get(i) + available_dice_points.get(i) >= 25)) {
				System.out.printf(
						String.valueOf((char) (97 + i)).toUpperCase()
								+ "): Points: %d, consume dice point %d to bear-off!\n",
						available_points.get(i), available_dice_points.get(i));
				available_options.add(String.valueOf((char) (97 + i)).toUpperCase());
			}
		}
	}
	// Determines the possible postion in which a dice can move to
	public static boolean possibleMove(int player_sequence) {
		boolean flag = false;
		moveLegalityCheck(player_sequence);
		if (available_points.size() != 0) {
			flag = true;
		}
		return flag;
	}
	// Determies which move the player istaking , it takes 3 args the player position, the point to move to and the distance to get to
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
				} else {
					points.pushPoints((target_point - moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
				}
			}
			if (target_point - moving_distance <= 0) {
				points.popPoints(target_point);
				bearoff_counter_black.add("Black");
			}
		}

		if (available_points.contains(target_point) && player_sequence == 1) {
			if (target_point + moving_distance <= 25) {
				if (points.peekPoints(target_point + moving_distance) == "Black") {
					points.pushPoints(25, points.peekPoints(target_point + moving_distance));
					points.popPoints(target_point + moving_distance);
					hit_detect = true;
					points.pushPoints((target_point + moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
				} else {
					points.pushPoints((target_point + moving_distance), points.peekPoints(target_point));
					points.popPoints(target_point);
				}
			}
			if (target_point + moving_distance >= 25) {
				points.popPoints(target_point);
				bearoff_counter_red.add("Red");
			}
		}
	}
	// Checks fo the legal moves for each player
	public static void moveLegalityCheck(int player_sequence) {
		String color;
		int size;

		available_points.clear();
		available_dice_points.clear();
		bearoff_flag = gameboard.bearOffCheck(points);

		if (roll_result.size() >= 2 && (roll_result.get(0) == roll_result.get(1))) {
			size = 1;
		} else {
			size = roll_result.size();
		}

		if (player_sequence == 0) {
			color = "Black";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((points.peekPoints(i) == color) && (i - roll_result.get(j) > 0)
							&& ((points.peekPoints(i - roll_result.get(j)) == color)
									|| points.getSize(i - roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					if (bearoff_flag[0] == 1 && (points.peekPoints(i) == color) && (i - roll_result.get(j) == 0)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
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
			}
		}

		if (player_sequence == 1) {
			color = "Red";
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < 26; i++) {
					if ((points.peekPoints(i) == color) && (i + roll_result.get(j) < 25)
							&& ((points.peekPoints(i + roll_result.get(j)) == color)
									|| points.getSize(i + roll_result.get(j)) <= 1)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
					if (bearoff_flag[1] == 1 && (points.peekPoints(i) == color) && (i + roll_result.get(j) == 25)) {
						available_points.add(i);
						available_dice_points.add(roll_result.get(j));
					}
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
			}
		}

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
//it returns an array of the the commends a player has issued.
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

	public static void printGameboard() {
		gameboard.printGameboard(points, bearoff_counter_red, bearoff_counter_black);
	}

	public void setNames(String[] player_name) {
		GameController.player_name = player_name;
		gameboard.setNames(player_name);
	}

	public static void printAvailableDicePoint(int player_sequence) {
		System.out.printf("It is %s's turn.\n", player_name[player_sequence]);
		System.out.print("Available dice point: ");
		for (int i = 0; i < roll_result.size(); i++) {
			System.out.print(roll_result.get(i) + " ");
		}
		System.out.println("");
	}
}
