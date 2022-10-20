package backgammon;

import java.util.Random;

public class Dice {
	
	Random random = new Random();
	private final int dice_faces = 6;
	
	public Dice() {
	}
	
	public int rollDice() {
		int number = 0;
		number = random.nextInt(dice_faces) + 1;
		return number;
	}

}
