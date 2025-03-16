package engine.board;

import java.util.ArrayList;
import java.util.Random;

import engine.GameManager;
import model.Colour;

public class Board implements BoardManager
{
    @SuppressWarnings("unused")
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

        for (int i = 0; i < 100; i++)//assigns cell types to the cells in the track
        {
            if(i % 25 == 0)
                track.add(new Cell(CellType.BASE)); //adding base cells every 25 positions(0,25,50,75)
            else if((i+2) % 25 == 0)
                track.add(new Cell(CellType.ENTRY)); //adding entry cells every 25 positions(98,23,48,73)
            else
                track.add(new Cell(CellType.NORMAL)); //adding normal cells in all other postions
        }
        for(int i=0;i<8;i++)
        assignTrapCell();
        
        for (int i = 0; i < 4; i++) 
            safeZones.add(new SafeZone(colourOrder.get(i))); // Store SafeZone in Board's safeZones list
    }    

    private void assignTrapCell()
    {
        Random random = new Random();
        while (true)
        {
            int x = random.nextInt(this.track.size());
            Cell tmp = track.get(x);
            if (tmp.getCellType() == CellType.NORMAL && !tmp.isTrap())
            {
                tmp.setTrap(true);
                return;
            }
        }
    }

    // We will create a new ArrayList called safeZones, which will store our 4 SafeZones
    // Each SafeZone will contain 4 cells of type SAFE
    // after creating each safzone object and initializing all its cells with the correct celltype(SAFE) we then add it to our safeZones arraylist that will have all 4 safezones

    public ArrayList<Cell> getTrack(){
        return this.track;
    }

    public ArrayList<SafeZone> getSafeZones(){
        return this.safeZones;
    }
    
    @Override
    public int getSplitDistance(){
        return this.splitDistance;
    }

    public void setSplitDistance(int splitDistance){
        this.splitDistance = splitDistance;
    }

    
    
}

/*
    4 CELL TYPES:

    Normal: - the regular cells that the player moves thorugh during the game
            - there are 100 total cells in the bords's track(not including the safe zones)

    Safe: -  once a marbel enters the safe zone it is safe and cannot return back to the general track
          -  marbels may only move forward in the safe zone to the next empty safe cell
          -  each safe zone consists of 4 cells EXCLUDING THE ENTRY CELL
          -  THESE CELLS WONT BE PART OF THE CATUAL 100 CELL TRACK, BUT RATHER AS PART OF THEIR OWN SAFE ZONE

    Base: - the initial position on the track where marbles begin their journey
          - base cells for each player are 25 cells apart on the track
          - meaning that each base cell has 24 non base cells between it and the next base cell
          
    Entry: - This is a single track cell located just before a player’s Safe Zone. 
           - It is positioned two cells before a player’s Base cell(as the marbles move clockwise throughout the game)

    EXTRA CELL TYPES:

    Trap: - THERE ARE 8 TRAP CELLS, whose positions are randomly determined at the start of the game.
          - Landing on a trap cell destroys a marble sending it back to its player’s Home Zone.
          
*/          


