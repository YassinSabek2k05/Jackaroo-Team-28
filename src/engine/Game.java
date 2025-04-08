package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import engine.board.Board;
import engine.board.SafeZone;
import exception.CannotDiscardException;
import exception.CannotFieldException;
import exception.GameException;
import exception.IllegalDestroyException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import exception.SplitOutOfRangeException;
import engine.board.Cell;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.*;

@SuppressWarnings("unused")
public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
	private int currentPlayerIndex;
    private final ArrayList<Card> firePit;
    private int turn;

    public Game(String playerName) throws IOException {
        turn = 0;
        currentPlayerIndex = 0;
        firePit = new ArrayList<>();

        ArrayList<Colour> colourOrder = new ArrayList<>();
        
        colourOrder.addAll(Arrays.asList(Colour.values()));
        
        Collections.shuffle(colourOrder);
        
        this.board = new Board(colourOrder, this);
        
        Deck.loadCardPool(this.board, this);
        
        this.players = new ArrayList<>();
        this.players.add(new Player(playerName, colourOrder.get(0)));
        
        for (int i = 1; i < 4; i++) 
            this.players.add(new CPU("CPU " + i, colourOrder.get(i), this.board));
        
        for (int i = 0; i < 4; i++) 
            this.players.get(i).setHand(Deck.drawCards());
        
    }
    
    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getFirePit() {
        return firePit;
    }
    //1
    public void selectCard(Card card) throws InvalidCardException {
        this.players.get(this.currentPlayerIndex).
    }
    //2
    public void selectMarble(Marble marble) throws InvalidMarbleException{

    }
    //3
    public void deselectAll(){

    }
    //4
    public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException{

    }
    //5
    public boolean canPlayTurn(){

    }
    //6
    public void playPlayerTurn() throws GameException {

    }
    //7
    public void endPlayerTurn(){

    }
    //8
    public Colour checkWin(){
        ArrayList<SafeZone> safeZ = this.getBoard().getSafeZones();
        for(SafeZone safe: safeZ){
            boolean noNulls = true;
            for(Cell cell:safe.getCells()){
                if(cell.getMarble()==null){
                    noNulls=false;
                    break;
                }
            }
            if(noNulls)
                return safe.getColour();
        }
        return null;
    }
    //9
    public void sendHome(Marble marble) throws IllegalDestroyException{
        Board board = this.getBoard();
        board.destroyMarble(marble);
    }
    //10
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException{

    }
    //11
    public void discardCard(Colour colour) throws CannotDiscardException {

    }
    //12
    public void discardCard() throws CannotDiscardException {

    }
    //13
    public Colour getActivePlayerColour(){
        return this.players.get(currentPlayerIndex).getColour();
    }
    //14
    public Colour getNextPlayerColour(){
        return this.players.get((currentPlayerIndex>=3)?0:currentPlayerIndex+1).getColour();
    }


    
}
