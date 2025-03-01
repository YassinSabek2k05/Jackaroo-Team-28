package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import engine.board.Board;
import engine.board.BoardManager;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.CPU;
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
        ArrayList<Player> playersTmp = new ArrayList<>();

        playersTmp.add(new Player(playerName, colourOrder.get(0)));
        for (int i = 1; i <=3; i++) {
            playersTmp.add(new CPU("CPU "+i, colourOrder.get(i),(BoardManager) this.board));
        }
        this.players = playersTmp;
        for (Player player : this.players) {
            player.setHand(Deck.drawCards());
        }
        this.currentPlayerIndex = 0;
        this.firePit = new ArrayList<>();

    }
}
