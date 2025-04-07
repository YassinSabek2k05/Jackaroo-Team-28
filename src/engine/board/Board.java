package engine.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import engine.Game;
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
        boolean five = steps == -1; //if true the steps will be performed by one of the players on an opponent's marble
        steps = !five?steps:5;
        ArrayList<Cell> path = new ArrayList<>();
        ArrayList<Cell> marbleSafeZone = this.getSafeZone(marble.getColour());
        int entry = getEntryPosition(marble.getColour());
        int current = getPositionInPath(path, marble);
        if(current!=-1){//the marble is on track
            for(;steps>=0;steps--,current++){
                if(current>=100)
                    current=0;
                if(current==entry&&!five){
                    if(steps>4)
                        throw new IllegalMovementException("the number of moves exceeds the available cells.");
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
        else {//the marble is either in the safe zone or in the home zone
            ArrayList<Cell> safe = this.getSafeZone(marble.getColour());
            int posInSafeZone = getPositionInPath(safe, marble);
            if(posInSafeZone!=-1){//the marble is in the safezone
                for(;steps>=0&&posInSafeZone<4;steps--,posInSafeZone++){
                    path.add(safe.get(posInSafeZone));
                }
                if(steps!=0)
                    throw new IllegalMovementException("the number of moves exceeds the available cells.");

            }
            else{//the marble is in the homezone
                throw new IllegalMovementException("To add the cell from the homezone to the track, the Card must be a King or an Ace.");
            }
        }
        return path;
    }
    //6
    private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException {
        int counter=0;
	for(int i=0;i<fullPath.size();i++){
        if(fullPath.get(i)!=null&& destroy==false){
        	
        Colour currColour=fullPath.get(i).getMarble().getColour();
        CellType currType= fullPath.get(i).getCellType();
        
        if(currColour!= marble.getColour()&& i!=fullPath.size()-1)
        	counter++;
        
        try{
        	if(currColour==marble.getColour())
        		throw new IllegalMovementException("Self-Blocking: A marble cannot move if there is another marble owned by the same player either in its path or at the target position.");}
        catch(IllegalMovementException e){
       System.out.println("Exception:"+ e.getMessage());}
        

        try{
        	if(currType== CellType.ENTRY )
        		throw new IllegalMovementException("Safe Zone Blockage: Cannot enter when a marble is at safezone Entry");}
        catch(IllegalMovementException e){
        	System.out.println("Exception:"+ e.getMessage());}
        
        
        try{
        	if(currType == CellType.BASE&& currColour!= marble.getColour())
        		throw new IllegalMovementException("Base Cell Blockage: another player in current player's base cell/path");}
        catch(IllegalMovementException e){
        	System.out.println("Exception:"+ e.getMessage());
        }
        
        
	
	}}
	try{
		if(counter>1)
			throw new IllegalMovementException("Path Blockage: More than one marble of opponent blocking path");
	}
	catch(IllegalMovementException e){
		System.out.println("Exception:"+ e.getMessage());}

    }
    //7
    private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException {

    }
    //8
    private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException {
        boolean marble1Exists=false;
	boolean marble2Exists=false;
	boolean marble1Base=false;
	boolean marble2Base=false;
	for(int i =0;i<track.size();i++){
		if(track.get(i).getMarble()==marble_1 )
			{if(track.get(i).getCellType()!= CellType.BASE)
				marble1Exists=true;
			else
				marble1Base=true;}
		if(track.get(i).getMarble()==marble_2)
		{if(track.get(i).getCellType()!= CellType.BASE)
			marble2Exists=true;
		else
			marble2Base=true;}
		
		if(marble1Exists==true&& marble2Exists==true)
			break;
	}
	try{
	if(marble1Exists==false||marble2Exists==false)
		throw new IllegalSwapException("one of the marbles not on track");}
	catch(IllegalSwapException e){System.out.println("Exception:"+ e.getMessage());}
	
	try{
		if(marble1Base==true||marble2Base==true)
			throw new IllegalSwapException("Opponent in Base cell");}
	catch(IllegalSwapException e){System.out.println("Exception:"+ e.getMessage());}
		
	
}


    }
    //9
    private void validateDestroy(int positionInPath) throws IllegalDestroyException {

    }
    //10
    private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException {



    }
    //11
    private void validateSaving(int positionInSafeZone, int positionOnTrack) throws InvalidMarbleException {
        if(this.track.get(positionOnTrack).getMarble()==null || )
    }
    //12
    void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException {

    }
    //13
    void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{

    }
    //14
    void destroyMarble(Marble marble) throws IllegalDestroyException{

    }
    //15
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException{
       int targetPos= getBasePosition(marble.getColour());
       if(track.get(targetPos)!=null){
        validateFielding(Cell occupiedBaseCell);
        destroyMarble(track.get(targetPos).getMarble());
       }
       track.add(targetPos, marble);


    }
    //16
    public void sendToSafe(Marble marble) throws InvalidMarbleException{
        ArrayList<Cell> safeZ = this.getSafeZone(marble.getColour());
        int posInSafeZone = this.getPositionInPath(safeZ, marble);
        int posInPath = this.getPositionInPath(this.track, marble);
        this.validateSaving(posInSafeZone, posInPath);//checking if 1.the marble was in the SafeZone or 2. wasn't on the track
        ArrayList<Cell> unoccupied = new ArrayList<>();
        for(Cell cell: safeZ)
            if(cell.getMarble()==null) 
                unoccupied.add(cell);
        if(unoccupied.size()==0) 
            throw new InvalidMarbleException();   
        int rand = (int)(Math.random() * unoccupied.size());
        Cell target = unoccupied.get(rand);
        target.setMarble(marble);
        this.track.get(posInPath).setMarble(null);
        
    }
    //17
    public ArrayList<Marble> getActionableMarbles(){
        Game gameRef = (Game)this.gameManager;
        Colour currentMarbleColour = gameRef.getActivePlayerColour();
        ArrayList<Marble> marbles = new ArrayList<>();
        for(Cell cell:this.track){
            if(cell.getMarble().getColour()==currentMarbleColour){
                marbles.add(cell.getMarble());
            }
        }
        return marbles;
    }
    public static void main(String[] args) throws IOException {
        Game game = new Game("Yassin");
        ArrayList<Colour> colourOrder = new ArrayList<>();
        colourOrder.add(Colour.RED);
        colourOrder.add(Colour.BLUE);
        colourOrder.add(Colour.GREEN);
        colourOrder.add(Colour.YELLOW);

        Board board = new Board(colourOrder, game);


    }
}
  
