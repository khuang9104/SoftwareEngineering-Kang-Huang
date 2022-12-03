
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

import java.util.ArrayList;
import java.util.Stack;

public class Points {

	private ArrayList<Stack<String>> points = new ArrayList<Stack<String>>();
	// Using an ArrayList to store multiple stacks, each stack stand for a point(Total 24 points)

	public Points() {
		for (int i = 0; i < 26; i++) {
			points.add(new Stack<String>());
		}
		initialPoints();
	}
	// Construct and initial the points

	public void pushPoints(int pointsNum, String checker_color) {
		points.get(pointsNum).push(checker_color);
	}
	// Method: push a checker into a specific point

	public String popPoints(int pointsNum) {
		String result = "empty";
		if (points.get(pointsNum).isEmpty() == false) {
			result = points.get(pointsNum).pop();
		}
		return result;
	}
	// Method: pop a checker from a specific point.

	public String peekPoints(int pointsNum) {
		String result = "empty";
		if (points.get(pointsNum).isEmpty() == false) {
			result = points.get(pointsNum).peek();
		}
		return result;
	}
	// Method: peek a checker from a specific point.
	// Return the colour of the checker in the top of specific point(stack).

	public int getSize(int pointsNum) {
		int result = 0;
		if (points.get(pointsNum).isEmpty() == false) {
			result = points.get(pointsNum).size();
		}
		return result;
	}
	// Method: check how many checkers in specific point. Return the result.

	private void initialPoints() {
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
	// Method: initial the points, when game start.

	public void clearPoints() {
		for (int i = 0; i < 26; i++) {
			points.get(i).clear();
		}
	}
	// Method: Clear all checkers in all points.
}
