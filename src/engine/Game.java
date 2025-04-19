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
    //milestone 2
    //1 🔍 REVIEW: Needs code review – YS
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
	    	throw new SplitOutOfRangeException();
	    board.setSplitDistance(splitDistance);
	    
	    

    }
    //5
    public boolean canPlayTurn(){
        Player currentPlayer = this.players.get(currentPlayerIndex);
        
        if (currentPlayer.getHand().size() >= turn) {
            return true;  
        } else {
            return false;  
        }
    }
    //6
    public void playPlayerTurn() throws GameException {
	    this.players.get(this.currentPlayerIndex).play();

    }
    //7
    public void endPlayerTurn(){
	    
		firePit.add(this.players.get(this.currentPlayerIndex).getSelectedCard());
	    this.players.get(this.currentPlayerIndex).deselectAll();
	    
	    if(currentPlayerIndex>3){
		    currentPlayerIndex=0;
		    turn++;
	    }
	    else
		currentPlayerIndex++;
	    if(turn>4)
	    {turn=0;
	     Deck.drawCards();
	     Deck.refillPool(firePit);
	     if(Deck.getPoolSize()<4)
		     Deck.refillPool(null);
	    }
	    
	    
	    
	    

    }
    //8 🔍 REVIEW: Needs code review – YS
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
    //9 🔍 REVIEW: Needs code review – YS
    public void sendHome(Marble marble){
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
		    throw new CannotFieldException();
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
    //13 🔍 REVIEW: Needs code review – YS
    public Colour getActivePlayerColour(){
        return this.players.get(currentPlayerIndex).getColour();
    }
    //14 🔍 REVIEW: Needs code review – YS
    public Colour getNextPlayerColour(){
        return this.players.get((currentPlayerIndex>=3)?0:currentPlayerIndex+1).getColour();
    }

    public static void main(String[] args) throws Throwable{
        Game g = new Game("Y");
        System.out.println(Deck.getPoolSize());

    }
    
}
