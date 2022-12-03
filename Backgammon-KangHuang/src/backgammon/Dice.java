
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

import java.util.Random;

public class Dice {
	
	private Random random = new Random();
	private final int dice_faces = 6;
	
	public Dice() {
	}
	
	public int rollDice() {
		int number = 0;
		number = random.nextInt(dice_faces) + 1;
		return number;
	}
	// Method: Roll dice once and return the result
}
