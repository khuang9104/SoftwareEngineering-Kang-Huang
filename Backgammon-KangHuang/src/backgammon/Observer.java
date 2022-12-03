
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

public interface Observer {

	public void update(Points points, String[] player_name, int[] bearoff_flag, int[] bearoff_counter);
	// Update all related data from GameData, when is notified
}
