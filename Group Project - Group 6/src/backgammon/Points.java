package backgammon;

import java.util.ArrayList;
import java.util.Stack;

public class Points {
	
    Checker checker_red = new Checker("Red");
    Checker checker_white = new Checker("Black");
	ArrayList<Stack<String>> points = new ArrayList<Stack<String>>();

	public Points() {
		for (int i = 0; i < 24; i++) {
			points.add(new Stack<String>());
		}
		initialPoints();
	}

	public void pushPoints(int pointsNum, String checker) {
		points.get(pointsNum-1).push(checker);

	}

	public boolean popPoints(int pointsNum) {
		boolean flag = false;
		if(points.get(pointsNum-1).isEmpty() == false) {
			points.get(pointsNum-1).pop();
			flag = true;
		}
		return flag;
	}

	public String peekPoints(int pointsNum) {
		String result = "empty";
		if(points.get(pointsNum-1).isEmpty() == false) {
			result = points.get(pointsNum-1).peek();
		}
		return result;
	}
	
	public int sizePoints(int pointsNum) {
		int result = 0;
		if(points.get(pointsNum-1).isEmpty() == false) {
			result = points.get(pointsNum-1).size();
		}
		return result;
	}
	
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

}
