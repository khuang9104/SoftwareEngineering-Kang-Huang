package backgammon;

import java.util.ArrayList;
import java.util.Stack;

public class Points {

	private ArrayList<Stack<String>> points = new ArrayList<Stack<String>>();
	// Using an ArrayList to store multiple stacks, each stack stand for a
	// point(Total 24 points)

	public Points() {
		for (int i = 0; i < 26; i++) {
			points.add(new Stack<String>());
		}
		initialPoints();
	}
	// Constructor

	public void pushPoints(int pointsNum, String checker_color) {
		points.get(pointsNum).push(checker_color);
	}
	// Method 1: push a checker into a specific point

	public boolean popPoints(int pointsNum) {
		boolean flag = false;
		if (points.get(pointsNum).isEmpty() == false) {
			points.get(pointsNum).pop();
			flag = true;
		}
		return flag;
	}
	// Method 2: pop a checker from a specific point. It will return a 'False' if
	// the point is empty before pop.

	public String peekPoints(int pointsNum) {
		String result = "empty";
		if (points.get(pointsNum).isEmpty() == false) {
			result = points.get(pointsNum).peek();
		}
		return result;
	}
	// Method 3: peek a checker from a specific point. Return the color of check in
	// the top of specific point(stack).

	public int getSize(int pointsNum) {
		int result = 0;
		if (points.get(pointsNum).isEmpty() == false) {
			result = points.get(pointsNum).size();
		}
		return result;
	}
	// Method 4: check how many checker in specific point. Return the result.

	public void initialPoints() {
		for (int i = 0; i < 2; i++) {
			points.get(1).push("Red");
			points.get(24).push("Black");
		}
		for (int i = 0; i < 3; i++) {
			points.get(8).push("Black");
			points.get(17).push("Red");
		}
		for (int i = 0; i < 5; i++) {
			points.get(6).push("Black");
			points.get(13).push("Black");
			points.get(12).push("Red");
			points.get(19).push("Red");
		}
	}
	// Method 5: initial the points, when game start.

	public void clearPoints() {
		for (int i = 0; i < 26; i++) {
			points.get(i).clear();
		}
	}

	public Points clonePoints(Points point) {
		Points points_clone = new Points();
		points_clone.clearPoints();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < point.getSize(j); j++) {
				points_clone.pushPoints(i, point.peekPoints(j));
			}
		}
		return points_clone;

	}

}
