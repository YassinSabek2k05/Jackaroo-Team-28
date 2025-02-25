package model.card.standard;
import model.card.standard.Suit;
import model.card.*;

public class Standard extends Card {
	private final int rank;
	private final Suit suit;
	
	
	
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
