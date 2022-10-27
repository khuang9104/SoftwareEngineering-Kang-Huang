package backgammon;

public class Checker {
	
	private String checkerColor;
	// Attributes
	
	public Checker(String color) {
		this.checkerColor = color;
	}
	// Constructor
	
	public String getCheckerColor() {
		return checkerColor;
	}
	// Method 1: return the color of Checker
	
	public String toString() {
		return "It is a " + checkerColor +" checker";
	}
	// toString
}
