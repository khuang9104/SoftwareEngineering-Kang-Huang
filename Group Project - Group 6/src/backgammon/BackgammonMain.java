package backgammon;

public class BackgammonMain {
	
	static Gameboard gameboard = new Gameboard();
	static Dice dice = new Dice();
	static Points points = new Points();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		gameboard.updateGameboard(points);
		gameboard.printGameboard();
		
	}		
}
