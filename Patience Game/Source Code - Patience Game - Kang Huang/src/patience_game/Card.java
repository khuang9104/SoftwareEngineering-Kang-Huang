package patience_game;

abstract public class Card {

	protected final String[] cards = { " A", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", " J", " Q", " K" };
	protected String[] deck = new String[13];
	// Initial variables

	public Card() {
	}

	public String[] getDeck() {
		return deck;
	}
	// Method: get deck(13 cards)
}
