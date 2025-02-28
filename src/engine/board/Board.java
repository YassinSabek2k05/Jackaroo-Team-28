package engine.board;

import java.util.ArrayList;

import engine.GameManager;
import model.Colour;

public class Board
{
    @SuppressWarnings("unused")
    private final GameManager gameManager;
    @SuppressWarnings("unused")
    private final ArrayList<Cell> track;
    @SuppressWarnings("unused")
    private final ArrayList<SafeZone> safeZones;
    @SuppressWarnings("unused")
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager)
    {
        this.gameManager = gameManager;
        this.track = new ArrayList<Cell>();
        this.safeZones = new ArrayList<SafeZone>();
        this.splitDistance = 3;
        
    }    
}