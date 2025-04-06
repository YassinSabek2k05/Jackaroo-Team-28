package engine.board;

import java.util.ArrayList;

import engine.GameManager;
imporimport model.Co
import model.player.Marble;lour;

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
          private int getPositionInPath(ArrayList<Cell> path, Marble marble){
    	for(int i=0;i<path.size();i++){
    		if(path.get(i).getMarble()==marble)
    			return i;
    		
    	}return -1;
    	
    }
    private ArrayList<Cell> getSafeZone(Colour colour){
        for(SafeZone safeZone : this.safeZones)
            if(safeZone.getColour()==colour)
                return safeZone.getCells();
        return null;
    }
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
    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws
IllegalMovementException {
    boolean five = steps==5;
    ArrayList<Cell> path = new ArrayList<>();
    ArrayList<Cell> marbleSafeZone = this.getSafeZone(marble.getColour());
    int entry = getEntryPosition(marble.getColour());
    int current = getPosit
}
  }
