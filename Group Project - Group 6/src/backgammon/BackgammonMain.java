package backgammon;

import java.util.ArrayList;
import java.util.Scanner;

public class BackgammonMain {

	private static GameController gameController = new GameController();
	private static Scanner scanner = new Scanner(System.in);
	private static String[] player_name = new String[2];
	private static ArrayList<Integer> roll_result = new ArrayList<Integer>();
	private static int player_sequence = 0;
	private static boolean end_flag = false;
	// Attributes

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gameInitial();
		while (end_flag == false) {
			gameController.rollCommand(player_sequence);
			gameController.moveCommand(player_sequence);

			player_sequence++;
			if (player_sequence == 2) {
				player_sequence = 0;
			}
		}
	}
	// Run the game (Main)

	public static void gameInitial() {
		setNames();
		GameController.printGameboard();
		System.out.printf("%s chose Black, %s chose Red.\n", player_name[0], player_name[1]);
		System.out.println("Game Start!");
		System.out.println("Now, each player throws a dice to determines who go first.");
		setPlaySequence();
	}
	// Method: initial the game, includes set name and play sequence determine. 

	public static void setNames() {
		boolean flag = false;
		String input;
		int hint_case = 1;
		while (flag == false) {
			System.out.println("Please input the name of player 1 (Black).");
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("hint")) {
				GameController.menuCommand(input, hint_case);
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name");
			} else if (!input.equals("") && !input.equals("pip")) {
				player_name[0] = input;
				flag = true;
			} else if (input.equals("pip")) {
				System.out.println("'pip' function is not available now.");
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name");
			}
		}
		System.out.println(player_name[0] + " chooses Black.");

		flag = false;
		while (flag == false) {
			System.out.println("Please input the name of player 2 (Red).");
			input = scanner.nextLine();
			if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("hint")) {
				GameController.menuCommand(input, hint_case);
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name");
			} else if (!input.equals("") && !input.equals("pip")) {
				player_name[1] = input;
				flag = true;
			} else if (input.equals("pip")) {
				System.out.println("'pip' function is not available now.");
				System.out.println("You can not use system command like 'quit', 'pip' or 'hint' as your name");
			}
		}
		System.out.println(player_name[1] + " chooses Red.");
		gameController.setNames(player_name);                    // Pass the names to GameController.
	}
	
	// Method: set name.

	public static void setPlaySequence() {
		boolean flag = false;
		while (flag == false) {
			roll_result = gameController.playSequenceRollCommand(player_name[0]);
			int dice_result_B = roll_result.get(0);
			System.out.printf("%s rolls %d.\n", player_name[0], dice_result_B);
			roll_result = gameController.playSequenceRollCommand(player_name[1]);
			int dice_result_R = roll_result.get(0);
			System.out.printf("%s rolls %d, %s rolls %d.\n", player_name[0], dice_result_B, player_name[1],
					dice_result_R);
			if (dice_result_B > dice_result_R) {
				player_sequence = 0;
				flag = true;
				System.out.printf("%s go first.\n", player_name[0]);
			}
			if (dice_result_B < dice_result_R) {
				player_sequence = 1;
				flag = true;
				System.out.printf("%s go first.\n", player_name[1]);
			}
			if (dice_result_B == dice_result_R) {
				System.out.println("Equal numbers! Please roll again.");
				flag = false;
			}
		}
	}
	// Method: set play sequence.
}
