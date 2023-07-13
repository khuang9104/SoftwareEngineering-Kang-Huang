package patience_game;

public class Lanes {

	private String[][] lanes;
	// Initial variables
	
	public Lanes() {
		this.lanes = new String[7][19];
	}
	// Construct lanes (7 column * 19 row)

	public String readCardFromLanes(int i, int j) {
		return lanes[i][j];
	}
	// Method: Read card in specific position of the lane

	public void setCardInLane(int i, int j, String card) {
		this.lanes[i][j] = card;
	}
	// Method: Set card in specific position of the line

	public boolean moveCardsBetweenLanes(int row, int lane_x, int lane_y) {
		boolean flag = false;
		int count = 0;
		// count the number of cards to be move
		boolean rule_check = true;
		int pointer = 0;
		if (lanes[lane_x - 1][row - 1] != null) {
			count = 1;
		}
		// If this card(lane_x,row) slot is not empty(null), the number of cards to be move = 1.
		
		for (int i = row; i < 19; i++) {
			if (orderCalculate(lanes[lane_x - 1][i - 1]) == orderCalculate(lanes[lane_x - 1][i]) + 1) {
				count += 1;
				// count the number of rest cards to be moved
			}
			if (lanes[lane_x - 1][i] != null
					&& orderCalculate(lanes[lane_x - 1][i - 1]) != orderCalculate(lanes[lane_x - 1][i]) + 1) {
				rule_check = false;
				// Ensure the cards to be moved are all in order
			}
			if (lanes[lane_x - 1][i] != null && lanes[lane_x - 1][i - 1].charAt(3) == lanes[lane_x - 1][i].charAt(3)) {
				rule_check = false;
				// Color check
			}
		}
		// 1. Count how many cards (in sequence) needs to be moved
		// 2. Check the target card and following cards are in sequence (rule_check)
		// 3. Check the target card and following cards are in certain color (rule_check)
		String[] temp = new String[count];
		for (int i = 0; i < 19; i++) {
			if (lanes[lane_y - 1][i] != null) {
				pointer = i + 1;
			}
		}
		if (rule_check == true) {
			if ((pointer != 0
					&& (orderCalculate(lanes[lane_y - 1][pointer - 1]) == orderCalculate(lanes[lane_x - 1][row - 1]) + 1))
					|| (pointer == 0 && orderCalculate(lanes[lane_x - 1][row - 1]) == 13)) {
				if ((pointer != 0 && (lanes[lane_y - 1][pointer - 1].charAt(3) != lanes[lane_x - 1][row - 1].charAt(3)))
						|| pointer == 0) {
					for (int i = 0; i < count; i++) {
						temp[i] = lanes[lane_x - 1][row - 1 + i];
						lanes[lane_x - 1][row - 1 + i] = null;
					}
					for (int i = 0; i < count; i++) {
						for (int j = 0; j < 19; j++) {
							if (lanes[lane_y - 1][j] == null) {
								lanes[lane_y - 1][j] = temp[i];
								flag = true;
								break;
							}
						}
					}
				}
			}
		}
		// 1. Find first available slot(s) for input card(s) (First null slot).
		// 2. If it is a empty lane, only K can be moved in.
		// 2. If the order of the last card in lane_y = the order of the target card(lane_x,row) + 1, move card in.
		// 3. Ensure target card and following cards are in certain color.
		return flag;
	}
	// Method: Move a card or cards(in sequence) from a lane to another lane.

	public boolean moveCardToLane(int lane, String card) {
		boolean flag = false;
		int pointer = 0;
		for (int i = 0; i < 19; i++) {
			if (lanes[lane - 1][i] != null) {
				pointer = i + 1;
			}
		}
		if ((pointer != 0 && (orderCalculate(lanes[lane - 1][pointer - 1]) == orderCalculate(card) + 1))
				|| (pointer == 0 && orderCalculate(card) == 13)) {
			if ((pointer != 0 && (lanes[lane - 1][pointer - 1].charAt(3) != card.charAt(3))) || pointer == 0) {
				lanes[lane - 1][pointer] = card;
				flag = true;
			}
		}
		return flag;
	}
	// Method: Move a card to the bottom of a specific lane(First null slot).
	// 1. If the order of the last card in lane_y = the order of the target card(lane_x,row) + 1, move card in.
	// 2. If it is a empty lane, only K can be moved in.
	// 3. Ensure target card is in certain color.

	private int orderCalculate(String card) {
		int order = 0;
		if (card != null) {
			switch (card.charAt(1)) {
			case 'A':
				order = 1;
				break;
			case '2':
				order = 2;
				break;
			case '3':
				order = 3;
				break;
			case '4':
				order = 4;
				break;
			case '5':
				order = 5;
				break;
			case '6':
				order = 6;
				break;
			case '7':
				order = 7;
				break;
			case '8':
				order = 8;
				break;
			case '9':
				order = 9;
				break;
			case '0':
				order = 10;
				break;
			case 'J':
				order = 11;
				break;
			case 'Q':
				order = 12;
				break;
			case 'K':
				order = 13;
				break;
			}
		} else {
			order = 0;
		}
		return order;
	}
	// Method: Tool to check the order of card
}
