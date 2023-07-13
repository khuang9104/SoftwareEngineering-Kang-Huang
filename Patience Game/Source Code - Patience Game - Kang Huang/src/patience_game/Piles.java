package patience_game;

public class Piles {

	private String[][] piles;
	private int[] piles_pointers;
	// Initial variables

	public Piles() {
		this.piles = new String[4][13];
		this.piles_pointers = new int[4];
	}
	// Construct 4 piles to store 4 types of cards

	public boolean pushPile(String card) {
		boolean flag = false;
		char type = card.charAt(6);
		int order = order_alt(card);
		switch (type) {
		case 'C':
			if (order == (piles_pointers[0] + 1)) {
				this.piles[0][piles_pointers[0]] = card;
				piles_pointers[0] += 1;
				flag = true;
			}
			break;
		case 'D':
			if (order == (piles_pointers[1] + 1)) {
				this.piles[1][piles_pointers[1]] = card;
				piles_pointers[1] += 1;
				flag = true;
			}
			break;
		case 'H':
			if (order == (piles_pointers[2] + 1)) {
				this.piles[2][piles_pointers[2]] = card;
				piles_pointers[2] += 1;
				flag = true;
			}
			break;
		case 'S':
			if (order == (piles_pointers[3] + 1)) {
				this.piles[3][piles_pointers[3]] = card;
				piles_pointers[3] += 1;
				flag = true;
			}
			break;
		}
		return flag;
	}
	// Method: Move card to correspond pile
	// Use a pointer to record the order of the top card in specific pile.
	// if the order of input card = pointer + 1, push the card into correspond pile.

	public boolean popPile(char type) {
		boolean flag = false;
		int card_type = 0;
		switch (type) {
		case 'C':
			card_type = 0;
			break;
		case 'D':
			card_type = 1;
			break;
		case 'H':
			card_type = 2;
			break;
		case 'S':
			card_type = 3;
			break;
		}
		if (piles_pointers[card_type] != 0) {
			piles_pointers[card_type] -= 1;
			flag = true;
		}
		return flag;
	}
	// Method: Remove(draw) the card from the top of the specific pile
	// Use readPile to get the card first

	public String readPile(char type) {
		String read = "  Empty ";
		int card_type = 0;
		switch (type) {
		case 'C':
			card_type = 0;
			break;
		case 'D':
			card_type = 1;
			break;
		case 'H':
			card_type = 2;
			break;
		case 'S':
			card_type = 3;
			break;
		}
		if (piles_pointers[card_type] != 0) {
			read = this.piles[card_type][piles_pointers[card_type] - 1];
		}
		return read;
	}
	// Method: Read the card from the top of specific pile

	public boolean pileFullCheck() {
		boolean won_flag = false;
		if (piles_pointers[0] == 13 && piles_pointers[1] == 13 && piles_pointers[2] == 13 && piles_pointers[3] == 13) {
			won_flag = true;
		}
		return won_flag;
	}
	// Method: Check whether all piles are full

	private int order_alt(String card) {
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

	public int scoreCalculate() {
		int score = 0;
		score = (piles_pointers[0] + piles_pointers[1] + piles_pointers[2] + piles_pointers[3]) * 10;
		return score;
	}
	// Method: Calculate the score(basic).
}
