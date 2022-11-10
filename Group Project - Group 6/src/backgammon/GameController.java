package backgammon;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private static Dice dice = new Dice();
	private static ArrayList<Integer> roll_result = new ArrayList<Integer>();
	private static ArrayList<Integer> available_point = new ArrayList<Integer>();
	private static Points points = new Points();
	private static boolean hit_detect = false;
	private static Gameboard gameboard = new Gameboard();
	private static Scanner scanner = new Scanner(System.in);

	public GameController() {
	}

	public void rollCommand(String player_name) {
		boolean flag = false;
		String input;

		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equals("roll")) {
				rollDice();
				gameboard.printGameboard(points);
				if (roll_result.size() == 2) {
					System.out.printf("%s rolls %d & %d.\n", player_name, roll_result.get(0), roll_result.get(1));
				} else {
					System.out.printf("%s rolls %d & %d. Double! %s now has %d & %d & %d & %d.\n", player_name,
							roll_result.get(0), roll_result.get(1), player_name, roll_result.get(0), roll_result.get(1),
							roll_result.get(2), roll_result.get(3));
				}
				flag = true;
			} else if (input.equals("quit") || input.equals("pip") || input.equals("hint")) {
				menuCommand(input);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
	}

	private static void menuCommand(String input) {
		if (input.equals("quit")) {
			gameboard.printGameboard(points);
			System.out.println("Quit the game successfully");
			System.exit(0);
		} else if (input.equals("hint")) {
			gameboard.printGameboard(points);
			// TBD
		} else if (input.equals("pip")) {
			gameboard.printGameboard(points);
			// TBD
		}
	}

	public void moveCommand(int player_sequence) {
		boolean flag = false;
		int point_number_input = 99;

		while (roll_result.size() > 0 && possibleMove(player_sequence, roll_result) == true) {
			System.out.print("Available dice point: ");
			for (int i = 0; i < roll_result.size(); i++) {
				System.out.print(roll_result.get(i) + " ");
			}
			if (player_sequence == 0) {
				if (points.getSize(25) == 0) {
					point_number_input = inputPointNumber(player_sequence);
				} else {
					point_number_input = barFirst(player_sequence);
				}
			}

			if (player_sequence == 1) {
				if (points.getSize(0) == 0) {
					point_number_input = inputPointNumber(player_sequence);
				} else {
					point_number_input = barFirst(player_sequence);
				}
			}

			inputDicePointToMove(player_sequence, point_number_input);
		}
		if (roll_result.size() > 0 && possibleMove(player_sequence, roll_result) == false) {
			flag = false;
			String input;
			System.out.print("Available dice point: ");
			for (int i = 0; i < roll_result.size(); i++) {
				System.out.print(roll_result.get(i) + " ");
			}
			while (flag == false) {
				System.out.println("There is nothing you can do, enter 'pass' to pass your turn");
				input = scanner.nextLine();
				if (input == "pass") {
					flag = true;
				}
			}
		}
	}

	public static int inputPointNumber(int player_sequence) {
		boolean flag;
		int point_number_input = 99;
		System.out.println(
				"\nEnter a point number to move your checker(i.e. Enter 10 means moving the checker from point 10)");
		flag = false;
		while (flag == false) {
			flag = false;
			point_number_input = tryParseInt(scanner.nextLine());

			for (int i = 0; i < roll_result.size(); i++) {
				available_point.clear();
				available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
				if (available_point.contains(point_number_input)) {
					flag = true;
				}
			}
			if (flag == false) {
				System.out.println("Entry error, please enter a correct point number or enter 'hint' for hints");
			}
		}
		return point_number_input;
	}

	public static int barFirst(int player_sequence) {
		boolean flag;
		String input;
		int point_number_input = 99;
		System.out.println("\nYou have to move your checker(s) from Bar first. Enter 'bar' to continue.");
		flag = false;
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equals("bar")) {
				for (int i = 0; i < roll_result.size(); i++) {
					available_point.clear();
					available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
					if (available_point.contains(25) && player_sequence == 0) {
						flag = true;
						point_number_input = 25;
					}
					if (available_point.contains(0) && player_sequence == 1) {
						flag = true;
						point_number_input = 0;
					}
				}
			} else {
				System.out.println("Entry error, please enter 'bar' to continue");
			}
		}
		return point_number_input;
	}

	public static void inputDicePointToMove(int player_sequence, int point_number_input) {
		boolean flag;
		int moving_distance;
		System.out.println(
				"Enter a dice point to move your checker(i.e. Enter 5 means moving the checker 5 points forward)");
		flag = false;
		while (flag == false) {
			flag = false;
			moving_distance = tryParseInt(scanner.nextLine());

			if (roll_result.contains(moving_distance)) {
				available_point.clear();
				available_point = moveLegalityCheck(player_sequence, moving_distance);
				if (available_point.contains(point_number_input)) {
					points = moveChecker(player_sequence, point_number_input, moving_distance);
					gameboard.printGameboard(points);
					roll_result.remove(roll_result.indexOf(moving_distance));
					if (hit_detect == true) {
						System.out.println("Hit detected!");
					}
					System.out.println("Checker move completed!");
					flag = true;
				}
			}
			if (flag == false) {
				System.out.println("Entry error, please enter a correct dice point or enter 'hint' for hints");
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

	public static boolean possibleMove(int player_sequence, ArrayList<Integer> roll_result) {
		boolean flag = false;

		if (player_sequence == 0) {
			if (points.getSize(25) == 0) {
				for (int i = 0; i < roll_result.size(); i++) {
					available_point.clear();
					available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
					if (available_point.size() != 0) {
						flag = true;
					}
				}
			} else {
				for (int i = 0; i < roll_result.size(); i++) {
					available_point.clear();
					available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
					if (available_point.contains(25)) {
						flag = true;
					}
				}
			}
		}
		if (player_sequence == 1) {
			if (points.getSize(0) == 0) {
				for (int i = 0; i < roll_result.size(); i++) {
					available_point.clear();
					available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
					if (available_point.size() != 0) {
						flag = true;
					}
				}
			} else {
				for (int i = 0; i < roll_result.size(); i++) {
					available_point.clear();
					available_point = moveLegalityCheck(player_sequence, roll_result.get(i));
					if (available_point.contains(0)) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	public ArrayList<Integer> playSequenceRollCommand(String player_name) {
		boolean flag = false;
		String input;

		System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equals("roll")) {
				rollDice();
				gameboard.printGameboard(points);
				flag = true;
			} else if (input.equals("quit") || input.equals("pip") || input.equals("hint")) {
				menuCommand(input);
				System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name);
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
		return roll_result;
	}

	public static Points moveChecker(int player_sequence, int target_point, int moving_distance) {
		available_point.clear();
		hit_detect = false;
		available_point = moveLegalityCheck(player_sequence, moving_distance);

		if (available_point.contains(target_point) && player_sequence == 0) {
			if (points.peekPoints(target_point - moving_distance) == "Red") {
				points.pushPoints(0, points.peekPoints(target_point + moving_distance));
				points.popPoints(target_point - moving_distance);
				hit_detect = true;
				points.pushPoints((target_point - moving_distance), points.peekPoints(target_point));
				points.popPoints(target_point);
			} else {
				points.pushPoints((target_point - moving_distance), points.peekPoints(target_point));
				points.popPoints(target_point);
			}
		}
		if (available_point.contains(target_point) && player_sequence == 1) {
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
		return points;
	}

	public static ArrayList<Integer> moveLegalityCheck(int player_sequence, int moving_distance) {
		available_point.clear();
		String color;
		if (player_sequence == 0) {
			color = "Black";
			for (int i = 0; i < 26; i++) {
				if ((points.peekPoints(i) == color) && (i - moving_distance > 0)
						&& ((points.peekPoints(i - moving_distance) == color)
								|| points.getSize(i - moving_distance) <= 1)) {
					available_point.add(i);
				}
			}
		}
		if (player_sequence == 1) {
			color = "Red";
			for (int i = 0; i < 26; i++) {
				if ((points.peekPoints(i) == color) && (i + moving_distance < 25)
						&& ((points.peekPoints(i + moving_distance) == color)
								|| points.getSize(i + moving_distance) <= 1)) {
					available_point.add(i);
				}
			}
		}
		return available_point;
	}
	
	/*
	public void CheckerBearOff(int player_sequence) {
		int[] bearoff_flag = new int[2];;
		bearoff_flag = gameboard.bearOffCheck(points);
		if (player_sequence == 0 && bearoff_flag[0] == 1) {
			System.out.println("You can Bear-Off now");

			
		}
	}
	*/

	public void printGameboard() {
		gameboard.printGameboard(points);
	}

	public void setNames(String[] player_name) {
		gameboard.setNames(player_name);
	}

	public static Integer tryParseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
