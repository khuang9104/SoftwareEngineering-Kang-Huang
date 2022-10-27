package backgammon;

import java.util.Scanner;

public class CommandExcutor {

	private static Dice dice = new Dice();
	private static int[] roll_result = new int[4];
	private static Gameboard gameboard = new Gameboard();
	private static Scanner scanner = new Scanner(System.in);

	public CommandExcutor() {
	}

	public int[] basicCommand(Points points) {
		boolean flag = false;
		String input;
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equals("roll")) {
				rollDice();
				flag = true;
			} else if (input.equals("menu")) {
				menuCommand(points);
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
		return roll_result;
	}

	private static void menuCommand(Points points) {
		String input;
		boolean flag_menu = false;
		while (flag_menu == false) {
			flag_menu = false;
			System.out.println(
					"Enter 'tips' for operation tips and 'quit' to exit the game or 'c' to continue the game.");
			input = scanner.nextLine();
			if (input.equals("quit")) {
				gameboard.printGameboard(points);
				System.out.println("Quit the game successfully");
				System.exit(0);
			} else if (input.equals("tips")) {
				gameboard.printGameboard(points);
				System.out.println("Put tips here");
				// TBD
				flag_menu = true;
				System.out.println("Please enter 'roll' to roll your dice or 'menu' for more info.");
			} else if (input.equals("c")) {
				flag_menu = true;
				gameboard.printGameboard(points);
				System.out.println("Please enter 'roll' to roll your dice or 'menu' for more info.");

			} else {
				gameboard.printGameboard(points);
				System.out.println(
						"Entry error, please enter 'tips' for operation tips and 'quit' to exit the game or 'c' to continue the game.");
			}
		}
	}
	
	public static void moveCommand() {
		
	}
	

	public static void rollDice() {
		roll_result[0] = dice.rollDice();
		roll_result[1] = dice.rollDice();
		if (roll_result[0] != roll_result[1]) {
			roll_result[2] = 0;
			roll_result[3] = 0;
		} else {
			roll_result[2] = roll_result[0];
			roll_result[3] = roll_result[1];
		}
	}

}
