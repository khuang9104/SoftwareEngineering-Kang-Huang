
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

public interface Subject {
	public void registerObserver(Observer observer);
	// Register an Observer.
	public void removeObserver(Observer observer);
	// Remove an Observer.
	public void notifyObservers();
	// Notify the Observers and trigger an update action.
	public int getObserversSize();
	// Check the number of Observers.
}
