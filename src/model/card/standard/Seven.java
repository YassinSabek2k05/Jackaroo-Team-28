package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Seven extends Standard {

    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        if (marbles == null) return false;
        if(marbles.size()==2)
            return marbles.get(0).getColour() == this.gameManager.getActivePlayerColour()&&marbles.get(0).getColour()==marbles.get(1).getColour();
        if(marbles.size()==1)
            return super.validateMarbleColours(marbles);
        return false;
    }
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return !marbles.isEmpty()&&(super.validateMarbleSize(marbles) || marbles.size()==2);
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        if(marbles.size()==1)
            super.act(marbles);
        else if(marbles.size()==2){
            int steps1 = this.boardManager.getSplitDistance();
            int steps2 = 7-steps1;
            this.boardManager.moveBy(marbles.get(0), steps1, false);
            this.boardManager.moveBy(marbles.get(1), steps2, false);
        }
    }
}
