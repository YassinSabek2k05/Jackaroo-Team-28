package model.card.wild;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Saver extends Wild {

    public Saver(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        return marbles.get(0).getColour()==this.gameManager.getActivePlayerColour();
    }
    //milestone 2
    // 🚧 WIP: Logic under development – YS
    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        // TODO Auto-generated method stub
        this.boardManager.sendToSafe(marbles.get(0));
    }

}
