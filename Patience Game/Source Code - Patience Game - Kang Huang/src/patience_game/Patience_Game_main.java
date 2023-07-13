package patience_game;

import java.util.Scanner;

public class Patience_Game_main {

	static Scanner scanner = new Scanner(System.in);
	static Deck Deck = new Deck();
	static Lanes Lanes = new Lanes();
	static Piles Piles = new Piles();
	// Instantiates
	
	static String tips = "Tips:\n1. Input <Line_label_X><Row_Y><Line_label_Z> to move card from Lane X, Row Y to Lane Z (i.e. 2125 -> from Lane 2, Row 12 to Lane 5).\n2. Input 'D' to uncover a new card from the deck. Input 'Q' to quit the game(Q).\n3. Input 'P'<Line_label_X> to move card from deck to lane X. (i.e. P3 -> Deck to Lane 3)\n4. Input 'PP' to move card from deck to piles. (i.e. PP -> Deck to Piles)\n5. Input <Line_label_X>'P' to move card from lane X to piles. (i.e. 3P -> Lane 3 to Piles)\n6. Input 'P'<Card type><Line_label_X> to move card from specific pile to lane X. (i.e. PD3 -> Piles(Diamond) to Lane 3)";
	static String prompt = "Plese input you command OR input 'T' to show command tips";
	static String input_error = "Command input error, please input 'T' to check the Tips for commands";
	static String operation_error = "You cannot operate like this";
	// Initial interface prompts
	
	static int step;
	static int score;
	static int score_punish;
	static int[] deck_mask = {0,1,2,3,4,5,6};
	// Initial variables
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input;
		char[] input_read = new char[5];
		int row;
		int lane_1;
		int lane_2;
		boolean endflag = false;
		int err_message = 0;
		// Initial variables
		
		gameInitial();
		// Game initial
		
		// Game main body
		while(endflag==false) {
			uiPrint(err_message);
			err_message = 0;
			input = scanner.nextLine();
			
			if(input.length()<=4) {input_read = instructionRead(input);}
			else {input_read[0] = 0;}
			// Instruction parse
			
			switch(input_read[0]) {
			case 1: 
				lane_1 = Character.getNumericValue(input_read[1]);
				row = Character.getNumericValue(input_read[2]);
				lane_2 = Character.getNumericValue(input_read[3]);
				if(row>0 && lane_1>0 && lane_1<8 && lane_2>0 && lane_2<8) {
					if(Lanes.moveCardsBetweenLanes(row,lane_1,lane_2)== false) {err_message = 2;}}
				else {err_message = 1;}
				break;
		    // Case 1 For instruction: card move from Lane X Row Y to Lane Z. (row 1-9) i.e. 212 -> from lane 2 row 1 to lane 2
				
			case 2: 
				lane_1 = Character.getNumericValue(input_read[1]);
				row = Character.getNumericValue(input_read[3])+10;
				lane_2 = Character.getNumericValue(input_read[4]);
				if(row>0 && lane_1>0 && lane_1<8 && lane_2>0 && lane_2<8) {
					if(Lanes.moveCardsBetweenLanes(row,lane_1,lane_2)== false) {err_message = 2;}}
				else {err_message = 1;}
				break;
		    // Case 2 For instruction: card move from Lane X Row Y to Lane Z. (row 10-19) i.e. 1122 -> from lane 1 row 12 to lane 2
				
			case 3: 
				if(input_read[1]=='D') {
					if(Deck.nextCard()==false) {err_message = 2;}
					else{score_punish+=1;}}
				    // Reveal one card, score -1. Encourage more efficient use of the deck.
				else if(input_read[1]=='Q') {
					System.out.println("Quit the game successfully");
					System.exit(0);}
				else if(input_read[1]=='T') {err_message = 3;}
				else {err_message = 1;}
				break;
			// Case 3 For instruction: Uncover a new card from the pile(D) or quit the game(Q)
				
			case 4:
				lane_1 = Character.getNumericValue(input_read[2]);
				if(input_read[1]=='P' && lane_1>0 && lane_1<8) {
					if(deckToLane(lane_1) == false) {err_message = 2;}}
				else {err_message = 1;}
				break;			
			// Case 4 For instruction: Move card from deck to lane. i.e. P3 -> Deck to lane 3
				
			case 5:
				if(input_read[1]=='P' && input_read[2]=='P') {
					if(deck2pile() == false) {err_message = 2;}}
				else {err_message = 1;}
				break;			
			// Case 5 For instruction: Move card from deck to piles. i.e. PP -> Deck to Piles	
				
			case 6:
				lane_1 = Character.getNumericValue(input_read[1]);
				
				if(lane_1>0 && lane_1<8 && input_read[2]=='P') {
					if(laneToPile(lane_1) == false) {
						err_message = 2;
						}
					}
				
				else {err_message = 1;}
				break;			
			// Case 6 For instruction: Move card from lane to piles. i.e. 3P -> Lane3 to Piles
				
			case 7:	
				lane_1 = Character.getNumericValue(input_read[3]);
				if(input_read[1]=='P' && (input_read[2]=='D'||input_read[2]=='H'||input_read[2]=='C'||input_read[2]=='S') && lane_1>0 && lane_1<8) {
					if(pileToLine(input_read[2], lane_1) == false) {err_message = 2;}}
				else {err_message = 1;}
				break;	
			// Case 7 For instruction: Move card from specific pile to lane. i.e. PD3 -> Piles(Diamond) to Lane 3
				
			case 0: err_message = 1; break;}
			// Case 0 input format error
			
		// Game main body
			
			step+=1;
			score = Piles.scoreCalculate() - score_punish;
		    // Step and score calculate
			
			endflag=Piles.pileFullCheck();
			if(endflag==true) {
				System.out.println("Game Over!");
				System.out.println("[Final Step: " + step + "]" + "  [Final Score: " + score + "]");}
			// Won condition check
			
			for(int i=0;i<7;i++) {
				if(deck_mask[i]!=0 && (Lanes.readCardFromLanes(i, deck_mask[i])==null)) {deck_mask[i]-=1;}
				}
			}
			// Reveal the mask if the next slot is null.
		}
	
	public static void gameInitial() {
		for(int i=0;i<7;i++){
			for(int j=0;j<i+1;j++) {
				Lanes.setCardInLane(i, j, Deck.drawDeck());}}
		step = 0;
		score = 0;}
	// Method: initial the game
	
	public static void uiPrint(int err_message) {
		System.out.println("***************************************************************************************************");
		System.out.print("  Deck");
		System.out.println("                                    Piles ->   Diamond      Heart        Club       Spade");
		System.out.print("  P:"+Deck.readDeck()+"                                      ");
		System.out.print("  D:" + Piles.readPile('D'));
		System.out.print("  H:" + Piles.readPile('H'));
		System.out.print("  C:" + Piles.readPile('C'));
		System.out.print("  S:" + Piles.readPile('S'));
		System.out.println("\n***************************************************************************************************");
		// Print deck and 4x piles 
		System.out.println("      Lane: 1"+"       Lane: 2"+"       Lane: 3"+"       Lane: 4"+"       Lane: 5"+"       Lane: 6"+"       Lane: 7");
		System.out.println("***************************************************************************************************");
		// Print lanes' labels
		for(int i=0; i<19; i++) {
			System.out.print("|");
			for(int j=0; j<7; j++) {
				if(i<9) {System.out.print(" "+(i+1));}
				else{System.out.print(i+1);}
				// format adjust
				if(Lanes.readCardFromLanes(j,i)!= null){
					if(deck_mask[j]>i) {
						System.out.print("| "+"********"+" |");}
					else{
						System.out.print("| "+Lanes.readCardFromLanes(j,i)+" |");}}
				else {
					System.out.print("|  "+"       "+" |");}}
			System.out.println();}
		// Print 7x lanes 
		System.out.println("***************************************************************************************************");
		System.out.println("[Step: " + step + "]" + "   [Score: " + score + "]");
		// Print Step and score 
		switch(err_message) {
		case 0: break;
		case 1: System.err.println(input_error);break;
		case 2: System.err.println(operation_error);break;
	    case 3: System.out.println(tips);break;}
		System.out.println(prompt);}
		// Print error message or/and tips
	    // UI print
	
	public static char[] instructionRead(String input) {
		char[] input_read = new char[5];
		// Initialize. input_read[0] for instruction classification. input_read[1-4] store instruction keywords.
		if (input.matches("[0-9]{3}+"))        {input_read[0] = 1;}
		// Case 1 For instruction: card move from Lane X Row Y to Lane Z. (row 1-9) i.e. 212 -> from row 2 of lane 1 to lane 2
		if (input.matches("[0-9]{4}+"))        {input_read[0] = 2;}
		// Case 2 For instruction: card move from Lane X Row Y to Lane Z. (row 10-19) i.e. 1312 -> from row 13 of lane 1 to lane 2
		if (input.matches("[A-Z]"))            {input_read[0] = 3;}
		// Case 3 For instruction: Uncover a new card from the pile(D) or quit the game(Q)	
		if (input.matches("[A-Z][0-9]"))       {input_read[0] = 4;}
		// Case 4 For instruction: Move card from deck to lane. i.e. D3 -> Deck to lane 3
		if (input.matches("[A-Z][A-Z]"))       {input_read[0] = 5;}
		// Case 5 For instruction: Move card from deck to piles. i.e. DP -> Deck to Piles
		if (input.matches("[0-9][A-Z]"))       {input_read[0] = 6;}
		// Case 6 For instruction: Move card from lane to piles. i.e. 3P -> Lane3 to Piles
		if (input.matches("[A-Z][A-Z][0-9]"))  {input_read[0] = 7;}
		// Case 7 For instruction: Move card from specific pile to lane. i.e. PD3 -> Piles(Diamond) to Lane 3
		for(int i=0;i<input.length();i++)      {input_read[i+1] = input.charAt(i);}
		// Parsing instruction into char[]
		return input_read;}
	// Method: Parse and classify instructions
	
	public static boolean deckToLane(int lane) {
		boolean flag = false;
		String card = Deck.readDeck();
		flag = Lanes.moveCardToLane(lane, card);
		if (flag==true) {Deck.drawDeck();}
		return flag;}
	// Method: Move card from deck to lane. i.e. D3 -> Deck to lane 3
	
	public static boolean deck2pile() {
		boolean flag = false;
		String card = Deck.readDeck();
		flag = Piles.pushPile(card);
		if(flag==true) {Deck.drawDeck();}
		return flag;}
	// Method: Move card from deck to piles. i.e. DP -> Deck to piles
	
	public static boolean laneToPile(int lane) {
		boolean flag = false;
		int pointer = 0;
		for(int i=0;i<19;i++) {
			if(Lanes.readCardFromLanes(lane-1,i)!=null) {
				pointer = i+1;}}	
		if (pointer!=0) {
			String card = Lanes.readCardFromLanes(lane-1,pointer-1);
			flag = Piles.pushPile(card);
			if(flag==true) {
				Lanes.setCardInLane(lane-1, pointer-1, null);;}
			}
		return flag;}
	// Method: Move card from lane to piles. i.e. 3P -> Lane3 to Piles
	// Use a pointer to find the last card in specific lane, then push the card(if it exists) to the piles.
	
	public static boolean pileToLine(char type, int lane) {
		boolean flag = false;
		String card = Piles.readPile(type);
		flag = Lanes.moveCardToLane(lane, card);
		if (flag==true) {
		flag = Piles.popPile(type);}
		return flag;}
	// Method: Move card from specific pile to lane. i.e. PD3 -> Piles(Diamond) to Lane 3
}
