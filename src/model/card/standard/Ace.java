package model.card.standard;

import java.util.ArrayList;

import engine.Game;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;
import model.player.Player;

public class Ace extends Standard {

    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 1, suit, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return (super.validateMarbleSize(marbles)||marbles.size()==0);
    }
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        if(marbles.size()==0){
            this.gameManager.fieldMarble();
        }
        else if(marbles.size()==1){
            super.act(marbles);  
        }
    }

}
