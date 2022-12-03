
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

import java.util.ArrayList;

public class BackgammonMain {

	private static GameController gameController = new GameController();
	private static String[] player_name = new String[2];
	private static ArrayList<Integer> roll_result = new ArrayList<Integer>();
	private static int player_sequence = 0;
	private static boolean end_flag = false;

	public static void main(String[] args) {
		gameInitial();
		while (end_flag == false) {
			ArrayList<Integer> roll_result = new ArrayList<Integer>();
			roll_result = gameController.rollCommand(player_sequence);
			gameController.moveCommand(player_sequence, roll_result);

			player_sequence++;
			if (player_sequence == 2) {
				player_sequence = 0;
			}
			// Take turns
		}
	}
	// Run the game (Main)

	private static void gameInitial() {
		gameController.setPlayerNames();
		player_name = gameController.getPlayerNames();
		System.out.printf("%s chose Black, %s chose Red.\n", player_name[0], player_name[1]);
		System.out.println("Game Start!");
		System.out.println("Now, each player throws a dice to determines who go first.");
		setPlaySequence();
	}
	// Method: initial the game, includes set name and play sequence determine. 

	private static void setPlaySequence() {
		boolean flag = false;
		while (flag == false) {
			
			roll_result = gameController.playSequenceRollCommand(player_name[0]);
			int dice_result_B = roll_result.get(0);
			System.out.printf("%s rolls %d.\n", player_name[0], dice_result_B);
			
			roll_result = gameController.playSequenceRollCommand(player_name[1]);
			int dice_result_R = roll_result.get(0);
			System.out.printf("%s rolls %d, %s rolls %d.\n", player_name[0], dice_result_B, player_name[1],	dice_result_R);
			
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
	// Method: Determine the play sequence.
}

