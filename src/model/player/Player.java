package model.player;

	
import java.util.ArrayList;

import model.Colour;
import model.card.Card;


	public class Player {

		private final String name;
		private final Colour colour;
		private ArrayList<Card> hand;
		private final ArrayList<Marble> marbles;
		private Card selectedCard;
		private final ArrayList<Marble> selectedMarbles;


	public Player(String name, Colour colour) {
			super();
			this.name = name;
			this.colour = colour;
			this.hand = new ArrayList<>();
			this.marbles = new ArrayList<>();
			Marble marble1= new Marble(colour);
			Marble marble2= new Marble(colour);
			Marble marble3= new Marble(colour);
			Marble marble4= new Marble(colour);
			marbles.add(marble1);
			marbles.add(marble2);
			marbles.add(marble3);
			marbles.add(marble4);
			this.selectedCard = null;
			this.selectedMarbles = new ArrayList<>();
		}



	//setters
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	//getters
	public ArrayList<Card> getHand() {
		return hand;
	}
	public Card getSelectedCard() {
		return selectedCard;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public ArrayList<Marble> getMarbles() {
		return marbles;
	}
}
