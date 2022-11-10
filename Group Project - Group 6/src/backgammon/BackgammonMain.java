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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		gameInitial();
		while (end_flag == false) {
			gameController.rollCommand(player_name[player_sequence]);
			gameController.moveCommand(player_sequence);

			player_sequence++;
			if (player_sequence == 2) {
				player_sequence = 0;
			}
		}
	}

	public static void gameInitial() {
		setNames();
		gameController.printGameboard();
		System.out.printf("%s chose Black, %s chose Red.\n", player_name[0], player_name[1]);
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
		gameController.setNames(player_name);
	}

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

}
