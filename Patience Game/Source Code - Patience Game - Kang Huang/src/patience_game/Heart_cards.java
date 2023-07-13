package patience_game;

public class Heart_cards extends Card {

	private final String color = "R";
	// Card color: Red (R)
	private final String labell = "H";
	// Card type: Heart (H)
	// Initial variables

	public Heart_cards() {
		for (int i = 0; i < 13; i++) {
			super.deck[i] = super.cards[i] + "[" + this.color + "]" + "(" + this.labell + ")";
		}
	}
	// Construct Heart cards
}
