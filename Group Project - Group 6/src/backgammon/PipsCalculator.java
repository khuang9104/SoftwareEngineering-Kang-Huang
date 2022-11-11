package backgammon;

public class PipsCalculator {

	private static int pips_red;
	private static int pips_black;
	
	public PipsCalculator() {
		pips_red = 0;
		pips_black = 0;
	}
	
	public void updatePips(Points points) {
		pips_red = 0;
		pips_black = 0;
		for (int i = 1; i < 25; i++) {
			if (points.peekPoints(i) == "Red") {
				pips_red = pips_red + (points.getSize(i)*(25-i));
			}
		}
		for (int i = 1; i < 25; i++) {
			if (points.peekPoints(i) == "Black") {
				pips_black = pips_black + (points.getSize(i)*i);
			}
		}
	}
	
	public void displayPips() {
		System.out.printf("Red pips = %d, Black pips = %d\n", pips_red, pips_black);
	}
	
	
}
