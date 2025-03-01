package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import engine.board.Board;
import engine.board.BoardManager;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.Player;

public class Game implements GameManager{
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;
    

    public Game(String playerName) throws IOException{
        ArrayList<Colour> colourOrder = new ArrayList<>();
        colourOrder.add(Colour.BLUE);
        colourOrder.add(Colour.GREEN);
        colourOrder.add(Colour.RED);
        colourOrder.add(Colour.YELLOW);
        Collections.shuffle(colourOrder);
        this.board = new Board(colourOrder, this);
        Deck.loadCardPool((BoardManager) this.board, this);
        
    }
}
