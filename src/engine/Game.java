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
//Submission #2
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
    //milestone 2
    //1 
    public void selectCard(Card card) throws InvalidCardException {
        this.players.get(this.currentPlayerIndex).selectCard(card);
    }
    //2
    public void selectMarble(Marble marble) throws InvalidMarbleException{
	    this.players.get(this.currentPlayerIndex).selectMarble(marble);
    }
    //3
    public void deselectAll(){
    	Player currentPlayer = this.players.get(currentPlayerIndex);
        currentPlayer.deselectAll();
    }
    //4
    public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException{
	    if(splitDistance<1 || splitDistance>6)
            throw new SplitOutOfRangeException("Split distance must be between 1 and 6 inclusive");
	    board.setSplitDistance(splitDistance);
    }
    //5
    public boolean canPlayTurn(){
        Player currentPlayer = this.players.get(currentPlayerIndex);
        
        if (currentPlayer.getHand().size() + turn==4) {
            return true;  
        } else {
            return false;  
        }
    }
    //6
    public void playPlayerTurn() throws GameException {
        if(checkWin()==null){
            if(this.canPlayTurn())
	            this.players.get(this.currentPlayerIndex).play();
        }
    }
    //7
    public void endPlayerTurn(){
        Player player = this.getPlayers().get(currentPlayerIndex);
        firePit.add(player.getSelectedCard());//a
        player.deselectAll(); //b
        this.currentPlayerIndex++; //c
        if(this.currentPlayerIndex==this.players.size()){//d
            this.currentPlayerIndex=0; 
            turn++;
        }
        if(turn==4) {
            turn=0;
            for(Player pl: this.getPlayers()){
                if(Deck.getPoolSize()<4){
                    Deck.refillPool(firePit);
                    firePit.clear();
                }
                pl.setHand(Deck.drawCards());
            }
        }
    }
    //8 
    public Colour checkWin(){ 
        ArrayList<SafeZone> safeZ = this.getBoard().getSafeZones();
        for(SafeZone safe: safeZ){
            if(safe.isFull())
                return safe.getColour();
        }
        return null;
    }
    //9 
    public void sendHome(Marble marble){
        if(marble==null) return;
        Board board = this.getBoard();
        for(Player player: this.getPlayers()){
            if(player.getColour()==marble.getColour()){
                player.getMarbles().add(marble);
            }
        }
    }
    //10
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException{
	    Marble marble= this.players.get(this.currentPlayerIndex).getOneMarble();
	    if(marble ==null)
            throw new CannotFieldException("No marble available to field");
	    board.sendToBase(marble);
	    this.players.get(this.currentPlayerIndex).getMarbles().remove(0);
    }
    //11
    public void discardCard(Colour colour) throws CannotDiscardException {
        ArrayList<Card> tmp = null;
        for(Player player:this.getPlayers()){
            if(player.getColour()==colour)
                tmp = player.getHand();
        }
        if(tmp==null||tmp.isEmpty())
            throw new CannotDiscardException("This player has no cards");
        int rand = (int) (Math.random() * tmp.size());
        firePit.add(tmp.remove(rand));
    }
    //12
    public void discardCard() throws CannotDiscardException {
        int numberOfPlayers = this.getPlayers().size();
        Player randomPlayer = this.getPlayers().get((int) (Math.random() * numberOfPlayers));
        do{
            randomPlayer = this.getPlayers().get((int) (Math.random() * numberOfPlayers));
        }
        while(randomPlayer.getColour()==this.getActivePlayerColour());
            if(randomPlayer.getHand()==null||randomPlayer.getHand().isEmpty()){
                throw new CannotDiscardException("This player has no cards");
        }
        int rand = (int) (Math.random() * randomPlayer.getHand().size());
        firePit.add(randomPlayer.getHand().remove(rand));
    }
    //13 
    public Colour getActivePlayerColour(){
        return this.players.get(currentPlayerIndex).getColour();
    }
    //14 
    public Colour getNextPlayerColour(){
        return this.players.get((currentPlayerIndex>=3)?0:currentPlayerIndex+1).getColour();
    }

//    public static void main(String[] args) throws Throwable{
//        Game g = new Game("Y");
//        System.out.println(Deck.getPoolSize());
//
//    }
    
}
