package backgammon;

public class PipsCalculator {

	private static int[] pips = new int[2]; 
	// pips[0] = Black pip, pips[1] = Red pip.

	public PipsCalculator() {
	}

	private void updatePips(Points points) {
		pips[0] = 0;
		pips[1] = 0;
		for (int i = 0; i < 26; i++) {
			if (points.peekPoints(i) == "Black") {
				pips[0] = pips[0] + (points.getSize(i) * i);
			}
		}
		for (int i = 0; i < 26; i++) {
			if (points.peekPoints(i) == "Red") {
				pips[1] = pips[1] + (points.getSize(i) * (25 - i));
			}
		}
	}
	// Updated pip based on the checkers position (including checkers in Bar).

	public void displayPips(Points points) {
		updatePips(points);
		System.out.printf("Red pips = %d, Black pips = %d\n", pips[1], pips[0]);
	}
	// Display the pip.
	
	public boolean gameOverCheck(Points points) {
		boolean flag;
		updatePips(points);
		if (pips[0] == 0 || pips[1] == 0) {
			flag = true;}
		else {
			flag = false;
		}
		return flag;
	}
	// when the pip of any player is equal to 0, that player won the game.

}
