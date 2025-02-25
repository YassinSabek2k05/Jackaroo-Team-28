package engine.board;

import java.util.ArrayList;

import model.Colour;

public class SafeZone {
	private Colour colour ;
	private ArrayList<Cell> cells;
	
	public SafeZone(Colour colour){
		this.colour=colour;
		this.cells= new ArrayList<Cell>(4);
	}

	public Colour getColour() {
		return this.colour;
	}

	public ArrayList<Cell> getCells() {
		return this.cells;
	}

	
}
