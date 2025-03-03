package model.card;
import engine.GameManager;
import engine.board.BoardManager;

abstract public class Card {

	private final String name;
	private final String description;
	protected final BoardManager boardManager;
	protected final GameManager gameManager;
	
	public Card(String name, String description, BoardManager boardManager,GameManager gameManager) 
	{
		this.name = name;
		this.description = description;
		this.boardManager = boardManager;
		this.gameManager = gameManager;
	}

	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description ;
	}
}
