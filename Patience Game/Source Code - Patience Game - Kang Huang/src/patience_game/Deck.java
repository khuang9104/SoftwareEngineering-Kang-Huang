package patience_game;

import java.util.LinkedHashSet;
import java.util.Random;

public class Deck {

	private Diamond_cards cards_Diamond = new Diamond_cards();
	private Heart_cards cards_Heart = new Heart_cards();
	private Club_cards cards_Club = new Club_cards();
	private Spade_cards cards_Spade = new Spade_cards();
	private Random random = new Random();
	// Instantiates

	private String[] deck_Diamond = cards_Diamond.getDeck();
	private String[] deck_Heart = cards_Heart.getDeck();
	private String[] deck_Club = cards_Club.getDeck();
	private String[] deck_Spade = cards_Spade.getDeck();
	private String[] deck = new String[52];
	// Initial variables

	public Deck() {
		for (int i = 0; i < 52; i++) {
			if (i < 13) {
				this.deck[i] = this.deck_Diamond[i];
			} else if (i < 26) {
				this.deck[i] = this.deck_Heart[i - 13];
			} else if (i < 39) {
				this.deck[i] = this.deck_Club[i - 26];
			} else {
				this.deck[i] = this.deck_Spade[i - 39];
			}
		}
		this.deck = shuffleDeck(this.deck);
	}
	// Construct a deck(52 cards), mix and shuffle 4 types of cards (each 13).

	public String[] getDeck() {
		return this.deck;
	}
	// Method: Return deck

	public String drawDeck() {
		String card = null;
		for (int i = 0; i < 52; i++) {
			if (this.deck[i] != null) {
				card = this.deck[i];
				this.deck[i] = null;
				break;
			}
		}
		return card;
	}
	// Method: Get(remove) a card form the top of deck (the first not null slot)

	public String readDeck() {
		String card = null;
		for (int i = 0; i < 52; i++) {
			if (this.deck[i] != null) {
				card = this.deck[i];
				break;
			}
		}
		return card;
	}
	// Method: Read a card form the top of deck (the first not null slot)

	public boolean nextCard() {
		boolean flag = true;
		String temp = null;
		for (int i = 0; i < 52; i++) {
			if (this.deck[i] != null) {
				temp = this.deck[i];
				this.deck[i] = null;
				break;
			}
		}
		for (int i = 0; i < 51; i++) {
			this.deck[i] = this.deck[i + 1];
		}
		this.deck[51] = temp;
		if (temp == null) {
			flag = false;
		}
		// When deck is empty
		return flag;
	}
	// Method: Take the card form the top of deck (the first not null slot) to the
	// bottom

	public String[] shuffleDeck(String[] deck) {
		LinkedHashSet<Integer> indexSet = new LinkedHashSet<Integer>();
		String[] shuffled_deck = new String[deck.length];
		while (indexSet.size() < deck.length) {
			indexSet.add(random.nextInt(deck.length) + 1);
		}
		Integer[] random_indexes = indexSet.toArray(new Integer[0]);
		for (int i = 0; i < deck.length; i++) {
			shuffled_deck[i] = deck[random_indexes[i] - 1];
		}
		return shuffled_deck;
	}
	// Method: Shuffle the deck
}
