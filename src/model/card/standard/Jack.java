package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size()==2 || marbles.size()==1;
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        if(marbles==null) return false;
        if(marbles.size()==1){
            return super.validateMarbleColours(marbles);
        }
        else if(marbles.size()==2){
            Colour mar1 = marbles.get(0).getColour();
            Colour mar2 = marbles.get(1).getColour();
            if(mar1==mar2||(mar1!=this.gameManager.getActivePlayerColour()&&mar2!=this.gameManager.getActivePlayerColour()))
                return false;
        }
        return true;
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        if(marbles.size()==2)
            this.boardManager.swap(marbles.get(0), marbles.get(1));
        else if(marbles.size()==1)
            super.act(marbles);
    }
}
