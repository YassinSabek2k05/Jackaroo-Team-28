package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import model.player.Marble;

public class Five extends Standard {

    public Five(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 5, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        return marbles!=null&&!marbles.isEmpty()&&marbles.get(0) != null &&marbles.get(0).getColour() != null;
    }
    
}
