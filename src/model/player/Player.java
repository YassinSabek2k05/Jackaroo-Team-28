package model.player;

import java.util.ArrayList;

import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;

@SuppressWarnings("unused")
public class Player {
    private final String name;
    private final Colour colour;
    private ArrayList<Card> hand;
    private final ArrayList<Marble> marbles;
    private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.hand = new ArrayList<>();
        this.selectedMarbles = new ArrayList<>();
        this.marbles = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            this.marbles.add(new Marble(colour));
        }
        
        //default value
        this.selectedCard = null;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    
    public ArrayList<Marble> getMarbles() {
		return marbles;
	}
    
    public Card getSelectedCard() {
        return selectedCard;
    }
    //milestone 2
    //1
    public void regainMarble(Marble marble){
        this.marbles.add(marble);
    }
    //2
    public Marble getOneMarble(){
        if(this.marbles.size()>0)
            return this.marbles.get(0);
        return null;
    }    
    //3
    public void selectCard(Card card) throws InvalidCardException{
        boolean flag = false;
        for(Card handCard: this.hand){
            if(card==handCard){ //if the given card is available in the player’s hand
                this.selectedCard = card; //sets it to the selectedCard
                flag = true;
                break;
            }
        }
        if(!flag) //Throws an InvalidCardException if the card does not belong to the current player’s hand.
            throw new InvalidCardException("the card does not belong to the current player's hand");
    }
    //4
    public void selectMarble(Marble marble) throws InvalidMarbleException{//Selects a marble to be used in the game
        if(this.selectedMarbles.size()>2) //Throws an InvalidMarbleException if trying to select more than two marbles.
            throw new InvalidMarbleException("Can't select more than 2 Marbles");
        this.selectedMarbles.add(marble);  //adding it to the selectedMarbles
    }
    //5
    public void deselectAll(){
        this.selectedCard = null;
        this.selectedMarbles.clear();
    }
    //6
    public void play() throws GameException{
        if(this.selectedCard==null) //it checks if a card has been selected
            throw new InvalidCardException("No card has been selected");
        //It then validates the number and color of the selected marbles are appropriate for the selected card
        this.selectedCard.validateMarbleSize(marbles);
        this.selectedCard.validateMarbleColours(marbles);
        this.selectedCard.act(marbles);// Upon passing all checks, the method allows the selected card to act with the selected marbles.
    }
}
