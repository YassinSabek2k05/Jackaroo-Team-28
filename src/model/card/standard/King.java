package model.card.standard;

import java.util.ArrayList;

import engine.Game;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;
import model.player.Player;

public class King extends Standard {

    public King(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 13, suit, boardManager, gameManager);
    }

    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return (marbles.size()==1||marbles.size()==0);
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        // TODO Auto-generated method stub
        if(marbles.size()==0){
            this.gameManager.fieldMarble();
        }
        else if(marbles.size()==1){
            this.boardManager.moveBy(marbles.get(0), getRank(), true);  
        }

    }
}
