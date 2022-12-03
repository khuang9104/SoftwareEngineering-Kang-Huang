package backgammon;

import java.util.ArrayList;

public class GameData implements Subject {
	
	private Points points = new Points();
	private String[] player_name = new String[2];
	private int[] bearoff_flag = new int[2];
	private int[] bearoff_counter = new int[2];
	private ArrayList<Observer> observers;

	public GameData() {
		observers = new ArrayList<Observer>();
	}
	// Constructor
	
	public void setPlayerNames(String player_name, int sequence) {
		this.player_name[sequence] = player_name;
		notifyObservers();
	}
	// Method: Set players' names
	// Trigger notifyObservers.
	
	public String[] getPlayerNames() {
		return player_name;
	}
	// Method: Get players' names.
	
	public void pushPoints(int pointsNum, String checker_color) {
		points.pushPoints(pointsNum, checker_color);
		notifyObservers();
	}
	// Method: Push a checker into a specific point
	// Trigger notifyObservers.

	public String popPoints(int pointsNum) {
		String result =  points.popPoints(pointsNum);
		bearOffCheck();
		notifyObservers();
		return result;
	}
	// Method: Pop a checker from a specific point.
	// After pop, check whether is ready to bear-off.
	// Trigger notifyObservers.

	public String peekPoints(int pointsNum) {
		return points.peekPoints(pointsNum);
	}
	// Method: Peek a checker from a specific point.

	public int getSize(int pointsNum) {
		return points.getSize(pointsNum);
	}
	// Method: Check how many checkers in specific point. Return the result.

	public void clearPoints() {
		points.clearPoints();
		notifyObservers();
	}
	// Method: Clear points
	// Trigger notifyObservers.
	
	public Points getPoints() {
		return points;
	}
	// Method: Get points
	
	public int[] bearOffCheck() {
		boolean existFlag;
		existFlag = false;
		for (int i = 7; i < 26; i++) {
			if (points.peekPoints(i) == "Black") {
				existFlag = true;
			}
		// Check whether there is checker outside the home board of Black(Include Bar).
		}
		if (existFlag == false) {
			bearoff_flag[0] = 1;     
		} else {
			bearoff_flag[0] = 0;
		}
		// Bearoff_flag[0] for Black
		// If there is no checker outside the home board of Black(Include Bar), Black are allow to bear-off.
		
		existFlag = false;
		for (int i = 0; i < 19; i++) {
			if (points.peekPoints(i) == "Red") {
				existFlag = true;
			}
		}
		// Check whether there is checker outside the home board of Red(Include Bar).
		if (existFlag == false) {
			bearoff_flag[1] = 1;    
		} else {
			bearoff_flag[1] = 0;
		}
		// Bearoff_flag[1] for Red
		// If there is no checker outside the home board of Red(Include Bar), Red are allow to bear-off.
		notifyObservers();
		return bearoff_flag;
	}
	// Method: Check whether players are ready for bear-off or not.
	// Trigger notifyObservers.
	
	public void setBearOffCounter(String color) {
		if (color.equals("Black")) {
			bearoff_counter[0] = bearoff_counter[0] + 1;
		}
		else if (color.equals("Red")) {
			bearoff_counter[1] = bearoff_counter[1] + 1;
		}
		notifyObservers();
	}
	// Method: Set the BearOff Counter.
	// Trigger notifyObservers.
	
	public int[] getBearOffCounter() {
		return bearoff_counter;
	}
	// Method: Get the BearOff Counter.

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	// Register an Observer.

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	// Remove an Observer.

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(points, player_name, bearoff_flag,bearoff_counter);
			}
	}
	// Notify the Observers and trigger an update action.

	@Override
	public int getObserversSize() {
		return observers.size();
	}
	// Check the number of Observers.
}
