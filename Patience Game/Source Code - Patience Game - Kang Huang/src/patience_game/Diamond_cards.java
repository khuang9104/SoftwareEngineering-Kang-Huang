package patience_game;

public class Diamond_cards extends Card {

	private final String color = "R";
	// Card color: Red (R)
	private final String labell = "D";
	// Card type: Diamond (D)
	// Initial variables

	public Diamond_cards() {
		for (int i = 0; i < 13; i++) {
			super.deck[i] = super.cards[i] + "[" + this.color + "]" + "(" + this.labell + ")";
		}
	}
	// Construct Diamond cards
}
