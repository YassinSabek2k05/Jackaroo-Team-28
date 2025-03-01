package engine.board;

import java.util.ArrayList;

import model.Colour;

public class SafeZone 
{
	private final Colour colour ;
	private final ArrayList<Cell> cells;
	
	public SafeZone(Colour colour){
<<<<<<< HEAD
		this.colour=colour;
		this.cells= new ArrayList<Cell>(4);
		for (Cell cell : cells) {
			cell = new Cell(CellType.SAFE);
		}
=======
		this.colour = colour;
		this.cells = new ArrayList<Cell>(4);
>>>>>>> 8d6bfdd4f7b7a97af883abb7b0c879996c7d7703
	}

	public Colour getColour() {
		return this.colour;
	}

	public ArrayList<Cell> getCells() {
		return this.cells;
	}
}
