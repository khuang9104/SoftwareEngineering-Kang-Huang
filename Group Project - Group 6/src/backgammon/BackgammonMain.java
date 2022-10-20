package backgammon;

public class BackgammonMain {
	
	static Gameboard gameboard = new Gameboard();
	static Dice dice = new Dice();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Checker checkerRed = new Checker("W");
		System.out.println(dice.rollDice());
		
		
		gameboard.printGameboard();
		
	}		
}
