package engine.board;

import java.util.ArrayList;

import engine.GameManager;
import exception.CannotFieldException;
import exception.IllegalDestroyException;
import exception.IllegalMovementException;
import exception.IllegalSwapException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

@SuppressWarnings("unused")
public class Board implements BoardManager {
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
	private final GameManager gameManager;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.gameManager = gameManager;
        
        for (int i = 0; i < 100; i++) {
            this.track.add(new Cell(CellType.NORMAL));
            
            if (i % 25 == 0) 
                this.track.get(i).setCellType(CellType.BASE);
            
            else if ((i+2) % 25 == 0) 
                this.track.get(i).setCellType(CellType.ENTRY);
        }

        for(int i = 0; i < 8; i++)
            this.assignTrapCell();

        for (int i = 0; i < 4; i++)
            this.safeZones.add(new SafeZone(colourOrder.get(i)));

        splitDistance = 3;
    }

    public ArrayList<Cell> getTrack() {
        return this.track;
    }

    public ArrayList<SafeZone> getSafeZones() {
        return this.safeZones;
    }
    
    @Override
    public int getSplitDistance() {
        return this.splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
    private void assignTrapCell() {
        int randIndex = -1;
        
        do
            randIndex = (int)(Math.random() * 100); 
        while(this.track.get(randIndex).getCellType() != CellType.NORMAL || this.track.get(randIndex).isTrap());
        
        this.track.get(randIndex).setTrap(true);
    }
    //1
    private int getPositionInPath(ArrayList<Cell> path, Marble marble){
            for(int i=0;i<path.size();i++){
                if(path.get(i).getMarble()==marble)
                    return i;
                
            }
            return -1;                              
    }
    
    //2
    private ArrayList<Cell> getSafeZone(Colour colour){
        for(SafeZone safeZone : this.safeZones)
            if(safeZone.getColour()==colour)
                return safeZone.getCells();
        return null;
    }
    //3
    private int getBasePosition(Colour colour){
        int i=-1;
        for(SafeZone safeZone: this.safeZones){
            if(safeZone.getColour()==colour)
                i=this.getSafeZones().indexOf(safeZone);
        }
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 25;   
            case 2: 
                return 50;
            case 3:
                return 75;             
            default:
                return -1;
        }
    }
    //4
    private int getEntryPosition(Colour colour){
        int i=-1;
        for(SafeZone safeZone: this.safeZones){
            if(safeZone.getColour()==colour)
                i=this.getSafeZones().indexOf(safeZone);
        }
        switch (i) {
            case 0:
                return 98;
            case 1:
                return 23;   
            case 2: 
                return 48;
            case 3:
                return 73;             
            default:
                return -1;
        }
    }
    //5
    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws IllegalMovementException {
        if (steps <= 0) {
            throw new IllegalMovementException("Steps must be greater than zero.");
        }
        boolean five = steps==5;
        ArrayList<Cell> path = new ArrayList<>();
        ArrayList<Cell> marbleSafeZone = this.getSafeZone(marble.getColour());
        int entry = getEntryPosition(marble.getColour());
        int current = getPositionInPath(path, marble);
        if(current!=-1){
            for(;steps>=0;steps--,current++){
                if(current>=100)
                    current=0;
                if(current==entry&&!five){
                    if(steps>4)
                        throw new IllegalMovementException("Cannot enter the safe zone without rolling a 5.");
                    else
                        for(int i=0;steps>=0;steps--,i++){
                            path.add(marbleSafeZone.get(i));
                        }
                }
                else{
                    path.add(this.track.get(current));
                }

            }
        }
        return path;
    }
    //13
    void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{

    }
    //14
    void destroyMarble(Marble marble) throws IllegalDestroyException{

    }
    //15
    void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException{

    }
    //16
    public void sendToSafe(Marble marble) throws InvalidMarbleException{

    }
    //17
    public ArrayList<Marble> getActionableMarbles(){
        ArrayList<Marble> marbles = new ArrayList<>();
        for(Cell cell:this.track){
            if(cell.getMarble()!=null){
                marbles.add(cell.getMarble());
            }
        }
        return marbles;
    }
}
  
