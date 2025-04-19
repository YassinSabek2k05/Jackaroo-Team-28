package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Five extends Standard {

    public Five(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 5, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        // TODO Auto-generated method stub
        return marbles.get(0).getColour()!=null;
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        // TODO Auto-generated method stub
        super.act(marbles);
    }

}
