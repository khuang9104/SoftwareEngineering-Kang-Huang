package backgammon;

public class Gameboard {

	private String[] point_count = new String[24];
	private String[][] point_show = new String[24][5];
	private String[] player_name = new String[2];
	// Attributes

	public Gameboard() {
	}
	// Constructor

	public void printGameboard(Points points) {

		updateGameboard(points);

		System.out.println(
				"********************************************************************************************");
		System.out.println("Red: " + player_name[1]);
		System.out.println(
				"**** RED Bear-Off:                                     R E D    H O M E    B O A R D    ****");
		System.out.println(
				"********************************************************************************************");
		System.out.println(
				"**| 1 3 || 1 4 || 1 5 || 1 6 || 1 7 || 1 8 |    | 1 9 || 2 0 || 2 1 || 2 2 || 2 3 || 2 4 |**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_count[13 - 1],
				point_count[14 - 1], point_count[15 - 1], point_count[16 - 1], point_count[17 - 1], point_count[18 - 1],
				point_count[19 - 1], point_count[20 - 1], point_count[21 - 1], point_count[22 - 1], point_count[23 - 1],
				point_count[24 - 1]);
		System.out.println(
				"**-----------------------------------------|    |-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[13 - 1][0],
				point_show[14 - 1][0], point_show[15 - 1][0], point_show[16 - 1][0], point_show[17 - 1][0],
				point_show[18 - 1][0], point_show[19 - 1][0], point_show[20 - 1][0], point_show[21 - 1][0],
				point_show[22 - 1][0], point_show[23 - 1][0], point_show[24 - 1][0]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[13 - 1][1],
				point_show[14 - 1][1], point_show[15 - 1][1], point_show[16 - 1][1], point_show[17 - 1][1],
				point_show[18 - 1][1], point_show[19 - 1][1], point_show[20 - 1][1], point_show[21 - 1][1],
				point_show[22 - 1][1], point_show[23 - 1][1], point_show[24 - 1][1]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[13 - 1][2],
				point_show[14 - 1][2], point_show[15 - 1][2], point_show[16 - 1][2], point_show[17 - 1][2],
				point_show[18 - 1][2], point_show[19 - 1][2], point_show[20 - 1][2], point_show[21 - 1][2],
				point_show[22 - 1][2], point_show[23 - 1][2], point_show[24 - 1][2]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[13 - 1][3],
				point_show[14 - 1][3], point_show[15 - 1][3], point_show[16 - 1][3], point_show[17 - 1][3],
				point_show[18 - 1][3], point_show[19 - 1][3], point_show[20 - 1][3], point_show[21 - 1][3],
				point_show[22 - 1][3], point_show[23 - 1][3], point_show[24 - 1][3]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[13 - 1][4],
				point_show[14 - 1][4], point_show[15 - 1][4], point_show[16 - 1][4], point_show[17 - 1][4],
				point_show[18 - 1][4], point_show[19 - 1][4], point_show[20 - 1][4], point_show[21 - 1][4],
				point_show[22 - 1][4], point_show[23 - 1][4], point_show[24 - 1][4]);
		System.out.println(
				"**-----------------------------------------|    |-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[12 - 1][4],
				point_show[11 - 1][4], point_show[10 - 1][4], point_show[9 - 1][4], point_show[8 - 1][4],
				point_show[7 - 1][4], point_show[6 - 1][4], point_show[5 - 1][4], point_show[4 - 1][4],
				point_show[3 - 1][4], point_show[2 - 1][4], point_show[1 - 1][4]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[12 - 1][3],
				point_show[11 - 1][3], point_show[10 - 1][3], point_show[9 - 1][3], point_show[8 - 1][3],
				point_show[7 - 1][3], point_show[6 - 1][3], point_show[5 - 1][3], point_show[4 - 1][3],
				point_show[3 - 1][3], point_show[2 - 1][3], point_show[1 - 1][3]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[12 - 1][2],
				point_show[11 - 1][2], point_show[10 - 1][2], point_show[9 - 1][2], point_show[8 - 1][2],
				point_show[7 - 1][2], point_show[6 - 1][2], point_show[5 - 1][2], point_show[4 - 1][2],
				point_show[3 - 1][2], point_show[2 - 1][2], point_show[1 - 1][2]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[12 - 1][1],
				point_show[11 - 1][1], point_show[10 - 1][1], point_show[9 - 1][1], point_show[8 - 1][1],
				point_show[7 - 1][1], point_show[6 - 1][1], point_show[5 - 1][1], point_show[4 - 1][1],
				point_show[3 - 1][1], point_show[2 - 1][1], point_show[1 - 1][1]);
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_show[12 - 1][0],
				point_show[11 - 1][0], point_show[10 - 1][0], point_show[9 - 1][0], point_show[8 - 1][0],
				point_show[7 - 1][0], point_show[6 - 1][0], point_show[5 - 1][0], point_show[4 - 1][0],
				point_show[3 - 1][0], point_show[2 - 1][0], point_show[1 - 1][0]);
		System.out.println(
				"**-----------------------------------------|    |-----------------------------------------**");
		System.out.printf("**|%s||%s||%s||%s||%s||%s|    |%s||%s||%s||%s||%s||%s|**\n", point_count[12 - 1],
				point_count[11 - 1], point_count[10 - 1], point_count[9 - 1], point_count[8 - 1], point_count[7 - 1],
				point_count[6 - 1], point_count[5 - 1], point_count[4 - 1], point_count[3 - 1], point_count[2 - 1],
				point_count[1 - 1]);
		System.out.println(
				"**| 1 2 || 1 1 || 1 0 || 0 9 || 0 8 || 0 7 |    | 0 6 || 0 5 || 0 4 || 0 3 || 0 2 || 0 1 |**");
		System.out.println(
				"********************************************************************************************");
		System.out.println(
				"****   BLACK Bear-Off:                                B L A C K   H O M E   B O A R D   ****");
		System.out.println("Black: " + player_name[0]);
		System.out.println(
				"********************************************************************************************");
		System.out.println("Enter 'menu' for tips and more functions(i.e Quit)");
		// Method 1: UI print
		// Bar to be added
		// Bear-off area to be added (can inherit from Bar class, 15 max for each color)
	}

	public void updateGameboard(Points points) {
		for (int i = 0; i < 24; i++) {
			if (points.size(i + 1) == 0) {
				point_count[i] = "Empty";
			} else {
				point_count[i] = " " + points.peekPoints(i + 1).charAt(0) + "("
						+ String.valueOf(points.size(i + 1) + ")");
			}
		}
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 5; j++) {
				if (points.size(i + 1) >= 0 && points.size(i + 1) <= 5) {
					if (j < points.size(i + 1)) {
						point_show[i][j] = "  @  ";
					} else {
						point_show[i][j] = "     ";
					}
				}
				if (points.size(i + 1) >= 6 && points.size(i + 1) <= 10) {
					if (j + 5 < points.size(i + 1)) {
						point_show[i][j] = "  @@ ";
					} else {
						point_show[i][j] = "  @  ";
					}
				}
				if (points.size(i + 1) >= 11 && points.size(i + 1) <= 15) {
					if (j + 10 < points.size(i + 1)) {
						point_show[i][j] = " @@@ ";
					} else {
						point_show[i][j] = "  @@ ";
					}
				}
			}

		}
	}
	// Method 2: use this to update the whole game board after any checker movement.

	public void setNames(String[] names) {
		this.player_name[0] = names[0];
		this.player_name[1] = names[1];
	}
	// Method 3: set players' namer
}
