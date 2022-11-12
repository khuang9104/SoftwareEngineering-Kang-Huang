package backgammon;

public class PipsCalculator {

	private static int[] pips = new int[2];

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

	public void displayPips(Points points) {
		updatePips(points);
		System.out.printf("Red pips = %d, Black pips = %d\n", pips[1], pips[0]);
	}
	
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

}
