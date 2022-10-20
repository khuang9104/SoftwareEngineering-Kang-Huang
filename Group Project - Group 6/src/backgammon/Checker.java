package backgammon;

public class Checker {
	
	private String checkerColor;
	
	public Checker(String color) {
		this.checkerColor = color;
	}
	
	public String getCheckerColor() {
		return checkerColor;
	}
	
	public String toString() {
		return "It is a " + checkerColor +" checker";
	}

}
