package backgammon;

import java.util.ArrayList;
import java.util.Stack;

public class Points {
	
	private Checker checker_red = new Checker("Red");
	private Checker checker_white = new Checker("Black");
    private ArrayList<Stack<String>> points = new ArrayList<Stack<String>>();
    // Using an ArrayList to store multiple stacks, each stack stand for a point(Total 24 points)

	public Points() {
		for (int i = 0; i < 24; i++) {
			points.add(new Stack<String>());
		}
		initialPoints();
	}
	// Constructor

	public void pushPoints(int pointsNum, String checkercolor) {
		points.get(pointsNum-1).push(checkercolor);
	}
	// Method 1: push a checker into a specific point 

	public boolean popPoints(int pointsNum) {
		boolean flag = false;
		if (points.get(pointsNum-1).isEmpty() == false) {
			points.get(pointsNum-1).pop();
			flag = true;
		}
		return flag;
	}
	// Method 2: pop a checker from a specific point. It will return a 'False' if the point is empty before pop. 

	public String peekPoints(int pointsNum) {
		String result = "empty";
		if(points.get(pointsNum-1).isEmpty() == false) {
			result = points.get(pointsNum-1).peek();
		}
		return result;
	}
	// Method 3: peek a checker from a specific point. Return the color of check in the top of specific point(stack). 
	
	public int size(int pointsNum) {
		int result = 0;
		if(points.get(pointsNum-1).isEmpty() == false) {
			result = points.get(pointsNum-1).size();
		}
		return result;
	}
	// Method 4: check how many checker in specific point. Return the result.
	
	public void initialPoints() {
		for (int i = 0; i < 2; i++) { 
			points.get(1-1).push(checker_red.getCheckerColor());
		}
		for (int i = 0; i < 3; i++) { 
			points.get(8-1).push(checker_white.getCheckerColor());
		}
		for (int i = 0; i < 5; i++) { 
			points.get(12-1).push(checker_red.getCheckerColor());
			points.get(19-1).push(checker_red.getCheckerColor());
		}
		for (int i = 0; i < 5; i++) { 
			points.get(6-1).push(checker_white.getCheckerColor());
			points.get(13-1).push(checker_white.getCheckerColor());
		}
		for (int i = 0; i < 3; i++) { 
			points.get(17-1).push(checker_red.getCheckerColor());
		}
		for (int i = 0; i < 2; i++) { 
			points.get(24-1).push(checker_white.getCheckerColor());
		}
	}
	// Method 5: initial the points, when game start.

}
