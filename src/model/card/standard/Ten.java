package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Ten extends Standard {

    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return !(marbles == null) &&(marbles.size()==0 ||super.validateMarbleSize(marbles));
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        if(marbles.size()==0)
            this.gameManager.discardCard(this.gameManager.getNextPlayerColour());
        else if(marbles.size()==1)
            super.act(marbles);
    }

}
