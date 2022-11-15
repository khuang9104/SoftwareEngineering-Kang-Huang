package Backgammon_Sprint12;
import java.util.Random;

public class Dice {
	
	private Random random = new Random();
	private final int dice_faces = 6;
	// Setting the faces of dice here
	
	public Dice() {
	}
	// Constructor
	
	public int rollDice() {
		int number = 0;
		number = random.nextInt(dice_faces) + 1;
		return number;
	}
	// Method 1: Roll dice once and return the result
}
