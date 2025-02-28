package engine.board;

import java.util.ArrayList;

import engine.GameManager;
import model.Colour;

public class Board
{
    private final GameManager gameManager;
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager)
    {
        this.gameManager = gameManager;
        this.track = new ArrayList<Cell>();
        this.safeZones = new ArrayList<SafeZone>();
        this.splitDistance = 3;
        
    }    
}