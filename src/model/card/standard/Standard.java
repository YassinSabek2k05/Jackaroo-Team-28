package model.card.standard;

import model.card.Card;


public class Standard extends Card {
	private int rank;
	private Suit suit;
	
	
	
	public String getRank(){
		return "Rank = " + rank;
	}
	
	public String getSuit(){
		return "Suit = " +suit ;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
