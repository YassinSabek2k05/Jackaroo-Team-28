package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import engine.board.Board;

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
        this.players = new ArrayList<>();
        colourOrder.add(Colour.BLUE);
        colourOrder.add(Colour.GREEN);
        colourOrder.add(Colour.RED);
        colourOrder.add(Colour.YELLOW);
        Collections.shuffle(colourOrder);
        this.board = new Board(colourOrder, this);
        Deck.loadCardPool(this.board, this);
        this.players.add(new Player(playerName, colourOrder.get(0)));
        for (int i = 1; i <=3; i++) {
            this.players.add(new CPU("CPU "+i, colourOrder.get(i), this.board));
        }
        for (Player player : this.players) {
            player.setHand(Deck.drawCards());
        }
        this.turn = 0;
        this.currentPlayerIndex = 0;
        this.firePit = new ArrayList<>();
    }
    public ArrayList<Card> getFirePit(){
        return firePit;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public Board getBoard(){
        return board;
    }
    // public static void main(String[] args) {
    //     try {
    //         Game game = new Game("Player1");
    //         for (Player player : game.getPlayers()) {
    //             System.out.println(player.getName());
    //             System.out.println(player.getColour());
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
