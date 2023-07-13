package patience_game;

public class Club_cards extends Card {

	private final String color = "B";
	// Card color: Black (B)
	private final String labell = "C";
	// Card type: Club (C)
	// Initial variables

	public Club_cards() {
		for (int i = 0; i < 13; i++) {
			super.deck[i] = super.cards[i] + "[" + this.color + "]" + "(" + this.labell + ")";
		}
	}
	// Construct Club cards
}
