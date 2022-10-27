package backgammon;

import java.util.Scanner;

public class BackgammonMain {

	private static Scanner scanner = new Scanner(System.in);
	private static Gameboard gameboard = new Gameboard();
	private static Dice dice = new Dice();
	private static Points points = new Points();
	private static String[] player_name = new String[2];
	private static int player_sequence = 0;
	private static boolean end_flag = false;
	private static int[] roll_result = new int[4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		gameInitial();

		while (end_flag == false) {
			System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n",
					player_name[player_sequence]);
			basicCommandExcutor();
			if (roll_result[0] != roll_result[1]) {
				System.out.printf("%s rolls %d & %d.\n", player_name[player_sequence], roll_result[0], roll_result[1]);
			} else {
				System.out.printf("%s rolls %d & %d. Double! %s now has %d & %d & %d & %d.\n",
						player_name[player_sequence], roll_result[0], roll_result[1], player_name[player_sequence],
						roll_result[0], roll_result[1], roll_result[2], roll_result[3]);
			}
			player_sequence++;
			if (player_sequence == 2) {
				player_sequence = 0;
			}
		}

	}

	public static void gameInitial() {
		setNames();
		gameboard.printGameboard(points);
		System.out.println("Game Start!");
		System.out.println("Now, each player throws a dice to determines who go first.");
		setPlaySequence();
	}

	public static void setNames() {
		boolean flag = false;
		while (flag == false) {
			System.out.println("Please input the name of player 1 (Black).");
			player_name[0] = scanner.nextLine();
			if (player_name[0] != "") {
				flag = true;
			}
		}
		System.out.println(player_name[0] + " chooses Black.");

		flag = false;
		while (flag == false) {
			System.out.println("Please input the name of player 2 (Red).");
			player_name[1] = scanner.nextLine();
			if (player_name[1] != "") {
				flag = true;
			}
		}
		System.out.println(player_name[1] + " chooses Red.");
		gameboard.setNames(player_name);
	}

	public static void setPlaySequence() {
		boolean flag = false;
		while (flag == false) {
			System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name[0]);
			basicCommandExcutor();
			int dice_result_B = roll_result[0];
			System.out.printf("%s rolls %d.\n", player_name[0], dice_result_B);
			System.out.printf("It is %s's turn, please enter 'roll' to roll your dice.\n", player_name[1]);
			basicCommandExcutor();
			int dice_result_R = roll_result[0];
			System.out.printf("%s rolls %d.\n", player_name[1], dice_result_R);
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

	public static void basicCommandExcutor() {
		boolean flag = false;
		boolean flag_menu = false;
		String input;
		while (flag == false) {
			flag = false;
			input = scanner.nextLine();
			if (input.equals("roll")) {
				rollDice();
				flag = true;
			} else if (input.equals("menu")) {
				flag_menu = false;
				while (flag_menu == false) {
					System.out.println(
							"Enter 'tips' for operation tips and 'quit' to exit the game or 'c' to continue the game.");
					input = scanner.nextLine();
					if (input.equals("quit")) {
						gameboard.printGameboard(points);
						System.out.println("Quit the game successfully");
						System.exit(0);
					}
					else if (input.equals("tips")) {
						gameboard.printGameboard(points);
						System.out.println("Put tips here");
						// TBD
						flag_menu = true;
						System.out.println("Please enter 'roll' to roll your dice or 'menu' for more info.");
					}
					else if (input.equals("c")) {
						flag_menu = true;
						gameboard.printGameboard(points);
						System.out.println("Please enter 'roll' to roll your dice or 'menu' for more info.");
						
					} else {
						gameboard.printGameboard(points);
						System.out.println(
								"Entry error, please enter 'tips' for operation tips and 'quit' to exit the game or 'c' to continue the game.");
					}
				}
			} else {
				System.out.println("Entry error, please enter 'roll' to roll your dice or 'menu' for more info.");
			}
		}
	}

}
