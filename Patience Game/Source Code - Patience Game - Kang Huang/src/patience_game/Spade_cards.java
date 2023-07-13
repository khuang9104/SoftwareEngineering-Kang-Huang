package patience_game;

public class Spade_cards extends Card {

	private final String color = "B";
	// Card color: Black (B)
	private final String labell = "S";
	// Card type: Spade (S)
	// Initial variables

	public Spade_cards() {
		for (int i = 0; i < 13; i++) {
			super.deck[i] = super.cards[i] + "[" + this.color + "]" + "(" + this.labell + ")";
		}
	}
	// Construct Spade cards
}
