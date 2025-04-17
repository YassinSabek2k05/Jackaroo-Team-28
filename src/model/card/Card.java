package model.card;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.standard.Jack;
import model.card.standard.Queen;
import model.card.standard.Seven;
import model.card.standard.Ten;
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
    //1 // 🚧 WIP: Logic under development – YS
    public boolean validateMarbleSize(ArrayList<Marble> marbles){
        int size = marbles.size();
        // if((this instanceof Jack || this instanceof Seven)||size!=2)
        //     return false;
        // if(size==1 )
        //     return false;
        // if(size==0)
        //     return false;
        if(size==0)
            if(!(this instanceof Ten||this instanceof Queen))
                return false;
        if(size==2)
            if(!(this instanceof Seven || this instanceof Jack))
                return false;
        return true;
    }
    //2
    public boolean validateMarbleColours(ArrayList<Marble> marbles){
        return false;
    }
    //3
    public abstract void act(ArrayList<Marble> marbles) throws ActionException,InvalidMarbleException;

}
