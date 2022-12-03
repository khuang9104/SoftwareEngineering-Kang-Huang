
// Student names: Kang Huang
// Student number: 20211924
// GitHub ID: khuang9104
// Repo link: https://github.com/khuang9104/SoftwareEngineering-Kang-Huang.git

package backgammon;

public class Gameboard implements Observer {

	private GameData gameData;
	private Points points = new Points();
	private String[] player_name = new String[2];
	private int[] bearoff_flag = new int[2];
	private int[] bearoff_counter = new int[2];
	private String bearoff_indicator_red;
	String bearoff_indicator_black;
	private String[] point_occupy = new String[26];
	private String[][] point_show = new String[26][5];

	public Gameboard(GameData gameData) {
		this.gameData = gameData;
		registerObserver();
	}
	// Constructor.
	// register as an observer.
	
	public void registerObserver() {
		gameData.registerObserver(this);
	}
	// register as an observer.

	public void printGameboard() {
		updateGameboard();
		System.out.println(
				"*********************************************************************************************");
		System.out.println("Red: " + player_name[1]);
		System.out.printf("**** RED Bear-Off: %s                    R E D    H O M E    B O A R D    ****\n",
				bearoff_indicator_red);
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"**| 1 3 || 1 4 || 1 5 || 1 6 || 1 7 || 1 8 | Bar | 1 9 || 2 0 || 2 1 || 2 2 || 2 3 || 2 4 |**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_occupy[13],
				point_occupy[14], point_occupy[15], point_occupy[16], point_occupy[17], point_occupy[18],
				point_occupy[0], point_occupy[19], point_occupy[20], point_occupy[21], point_occupy[22],
				point_occupy[23], point_occupy[24]);
		System.out.println(
				"**-----------------------------------------|-----|-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[13][0],
				point_show[14][0], point_show[15][0], point_show[16][0], point_show[17][0], point_show[18][0],
				point_show[0][0], point_show[19][0], point_show[20][0], point_show[21][0], point_show[22][0],
				point_show[23][0], point_show[24][0]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[13][1],
				point_show[14][1], point_show[15][1], point_show[16][1], point_show[17][1], point_show[18][1],
				point_show[0][1], point_show[19][1], point_show[20][1], point_show[21][1], point_show[22][1],
				point_show[23][1], point_show[24][1]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[13][2],
				point_show[14][2], point_show[15][2], point_show[16][2], point_show[17][2], point_show[18][2],
				point_show[0][2], point_show[19][2], point_show[20][2], point_show[21][2], point_show[22][2],
				point_show[23][2], point_show[24][2]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[13][3],
				point_show[14][3], point_show[15][3], point_show[16][3], point_show[17][3], point_show[18][3],
				point_show[0][3], point_show[19][3], point_show[20][3], point_show[21][3], point_show[22][3],
				point_show[23][3], point_show[24][3]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[13][4],
				point_show[14][4], point_show[15][4], point_show[16][4], point_show[17][4], point_show[18][4],
				point_show[0][4], point_show[19][4], point_show[20][4], point_show[21][4], point_show[22][4],
				point_show[23][4], point_show[24][4]);
		System.out.println(
				"**-----------------------------------------|     |-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[12][4],
				point_show[11][4], point_show[10][4], point_show[9][4], point_show[8][4], point_show[7][4],
				point_show[25][4], point_show[6][4], point_show[5][4], point_show[4][4], point_show[3][4],
				point_show[2][4], point_show[1][4]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[12][3],
				point_show[11][3], point_show[10][3], point_show[9][3], point_show[8][3], point_show[7][3],
				point_show[25][3], point_show[6][3], point_show[5][3], point_show[4][3], point_show[3][3],
				point_show[2][3], point_show[1][3]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[12][2],
				point_show[11][2], point_show[10][2], point_show[9][2], point_show[8][2], point_show[7][2],
				point_show[25][2], point_show[6][2], point_show[5][2], point_show[4][2], point_show[3][2],
				point_show[2][2], point_show[1][2]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[12][1],
				point_show[11][1], point_show[10][1], point_show[9][1], point_show[8][1], point_show[7][1],
				point_show[25][1], point_show[6][1], point_show[5][1], point_show[4][1], point_show[3][1],
				point_show[2][1], point_show[1][1]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_show[12][0],
				point_show[11][0], point_show[10][0], point_show[9][0], point_show[8][0], point_show[7][0],
				point_show[25][0], point_show[6][0], point_show[5][0], point_show[4][0], point_show[3][0],
				point_show[2][0], point_show[1][0]);
		System.out.println(
				"**-----------------------------------------|-----|-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|%s|%s||%s||%s||%s||%s||%s|**\n", point_occupy[12],
				point_occupy[11], point_occupy[10], point_occupy[9], point_occupy[8], point_occupy[7], point_occupy[25],
				point_occupy[6], point_occupy[5], point_occupy[4], point_occupy[3], point_occupy[2], point_occupy[1]);
		System.out.println(
				"**| 1 2 || 1 1 || 1 0 ||  9  ||  8  ||  7  | Bar |  6  ||  5  ||  4  ||  3  ||  2  ||  1  |**");
		System.out.println(
				"*********************************************************************************************");
		System.out.printf("**** BLACK Bear-Off: %s                 B L A C K   H O M E   B O A R D   ****\n",
				bearoff_indicator_black);
		System.out.println("Black: " + player_name[0]);
		System.out.println(
				"*********************************************************************************************");
		System.out.println("Enter 'hint' to get more info.");
	}   
	// Method: Updated the game board and print the UI.

	private void updateGameboard() {
		point_occupy[0] = " Red ";         
		// Red Bar
		point_occupy[25] = "Black";        
		// Black Bar
		for (int i = 1; i < 25; i++) {
			if (points.getSize(i) == 0) {
				point_occupy[i] = "     ";
			} else {
				if (points.peekPoints(i) == "Red") {
					point_occupy[i] = " Red ";
				}
				if (points.peekPoints(i) == "Black") {
					point_occupy[i] = "Black";
				}
			}
		}
		// Updated the occupation status of all points.
		
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 5; j++) {
				if (points.getSize(i) >= 0 && points.getSize(i) <= 5) {
					if (j < points.getSize(i)) {
						point_show[i][j] = "  @  ";
					} else {
						point_show[i][j] = "     ";
					}
				}
				if (points.getSize(i) >= 6 && points.getSize(i) <= 10) {
					if (j + 5 < points.getSize(i)) {
						point_show[i][j] = "  @@ ";
					} else {
						point_show[i][j] = "  @  ";
					}
				}
				if (points.getSize(i) >= 11 && points.getSize(i) <= 15) {
					if (j + 10 < points.getSize(i)) {
						point_show[i][j] = " @@@ ";
					} else {
						point_show[i][j] = "  @@ ";
					}
				}
			}
		}
		// Show the checkers in each points and bars, use ‘@’ stand for a checker.

		if (bearoff_flag[0] == 0) {
			bearoff_indicator_black = "Not available yet";
		} else {
			bearoff_indicator_black = String.valueOf(bearoff_counter[0]) + "                ";
		}                                                                        
		if (bearoff_flag[1] == 0) {
			bearoff_indicator_red = "Not available yet";
		} else {
			bearoff_indicator_red = String.valueOf(bearoff_counter[1]) + "                ";;
		}
	}
	// Update the bear-off status, if it is available to bear-off, show the number of checkers that bear-off successfully. 
	// Method: Update the whole game board.

	@Override
	public void update(Points points, String[] player_name, int[] bearoff_flag, int[] bearoff_counter) {
		this.points.clearPoints();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < points.getSize(i); j++) {
				this.points.pushPoints(i, points.peekPoints(i));
			}
		}
		this.player_name = player_name;
		this.bearoff_flag = bearoff_flag;
		this.bearoff_counter = bearoff_counter;
	}
	// Update all related data from GameData, when is notified
}
