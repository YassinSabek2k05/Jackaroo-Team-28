package model.card;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.standard.Jack;
import model.card.standard.Seven;
import model.player.Marble;

public abstract class Card {
	private final String name;
    private final String description;
    protected BoardManager boardManager;
    protected GameManager gameManager;

    public Card(String name, String description, BoardManager boardManager, GameManager gameManager) {
        this.name = name;
        this.description = description;
        this.boardManager = boardManager;
        this.gameManager = gameManager;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //milestone 2
    //1
    public boolean validateMarbleSize(ArrayList<Marble> marbles){
        int size = marbles.size();
        if((this instanceof Jack || this instanceof Seven)||size!=2)
            return false;
        if(this instanceof )
        return true;
    }
    //2
    public boolean validateMarbleColours(ArrayList<Marble> marbles){

    }
    //3
    public abstract void act(ArrayList<Marble> marbles) throws ActionException,InvalidMarbleException;

}
