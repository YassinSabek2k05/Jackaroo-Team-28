package engine.board;

import model.Colour;

import java.util.ArrayList;

public class SafeZone {
    private final Colour colour;
    private final ArrayList<Cell> cells;

    public SafeZone(Colour colour) {
        this.colour = colour;
        this.cells = new ArrayList<>();
        for (int i = 0; i < 4; i++) 
            this.cells.add(new Cell(CellType.SAFE));
    }

    public Colour getColour() {
        return this.colour;
    }

    public ArrayList<Cell> getCells() {
        return this.cells;
    }
    
    public boolean isFull() {
        for (Cell cell : this.cells) {
            if (cell.getMarble() == null) 
                return false;
        }
            
        return true;
    }

}
