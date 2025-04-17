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
    
    //2 🔍 REVIEW: Needs code review – Y
    private ArrayList<Cell> getSafeZone(Colour colour){
        for(SafeZone safeZone : this.safeZones)
            if(safeZone.getColour()==colour)
                return safeZone.getCells();
        return null;
    }
    //3 🔍 REVIEW: Needs code review – Y
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
    //4 🔍 REVIEW: Needs code review – Y
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
    //5 🧪 TESTING: Check for edge cases – Y
    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws IllegalMovementException {
        if (steps <= 0) {
            throw new IllegalMovementException("Steps must be greater than zero.");
        }
        Game game = (Game) this.gameManager;
        boolean five = (game.getActivePlayerColour()!=marble.getColour()); //if true the steps will be performed by one of the players on an opponent's marble
        ArrayList<Cell> path = new ArrayList<>();
        ArrayList<Cell> marbleSafeZone = this.getSafeZone(marble.getColour());
        int entry = getEntryPosition(marble.getColour());
        int current = getPositionInPath(this.track, marble);
        if(current!=-1){//the marble is on track
            for(;steps>0;steps--,current++){
                if(current>=this.track.size())
                    current=0;
                if(current==entry&&!five){
                    if(steps>marbleSafeZone.size())
                        throw new IllegalMovementException("the number of moves exceeds the available cells.");
                    for(int i=0;steps>0;steps--,i++){
                        path.add(marbleSafeZone.get(i));
                    }
                    return path;
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
                for(;steps>0&&posInSafeZone<safe.size();steps--,posInSafeZone++){
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
        
        if(i!=fullPath.size()-1)
        	counter++;
        
        
        	if(currColour==marble.getColour())
        		throw new IllegalMovementException("Self-Blocking: A marble cannot move if there is another marble owned by the same player either in its path or at the target position.");
       
    
        
        	if(currType== CellType.ENTRY )
        		throw new IllegalMovementException("Safe Zone Blockage: Cannot enter when a marble is at safezone Entry");
        
        
    
        	if(currType == CellType.BASE&& currColour!= marble.getColour())
        		throw new IllegalMovementException("Base Cell Blockage: another player in current player's base cell/path");}
            }

        
		if(counter>1)
			throw new IllegalMovementException("Path Blockage: More than one marble blocking path");}
	
}
	
    //7 🔍 REVIEW: Needs code review – R/Y
    private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException {
        Cell currentCell = fullPath.get(0);
        currentCell.setMarble(null);//remove the marble from its current cell
        Cell targetCell = fullPath.get(fullPath.size() - 1);
        fullPath.remove(0);

        //handle marble destroying keeping in mind the special cards(King)
        if (destroy) { //King:destroys all marbles in its path
            for(Cell cell: fullPath){
                if(cell.getMarble()!=null){
                    destroyMarble(cell.getMarble());
                }
            }
        }
        else{ // not a King: it destroys only the marble in the target cell if one exists
            if(fullPath.get(fullPath.size()-1).getMarble()!=null){
                destroyMarble(targetCell.getMarble());
            }
        }
        
        targetCell.setMarble(marble); //Place the marble in the calculated target cell
        
        if (targetCell.isTrap()) { //if the target cell is a trap
     
            destroyMarble(marble);// destroy the marble
            
            targetCell.setTrap(false); // Deactivate the trap
            assignTrapCell(); //assign a new trap cell
        }
    }
    //8
private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException {
    boolean marble1Exists=false;
	boolean marble2Exists=false;
	boolean marble1Base=false;
	boolean marble2Base=false;

    int i1= getPositioninPath(track,marble_1);
    int i2= getPositioninPath(track,marble_2);

    if(marble_1.getColour()==marble_2.getColour())
        throw new IllegalSwapException("same player");
	if(i1!=-1)
		{if(track.get(i).getCellType()!= CellType.BASE)
				marble1Exists=true;
			else
				marble1Base=true;}
	if(i2!=-1)
		{if(track.get(i).getCellType()!= CellType.BASE)
			marble2Exists=true;
		else
			marble2Base=true;}
		
		if(marble1Exists==true&& marble2Exists==true)
			break;
	
	if(marble1Exists==false||marble2Exists==false)
		throw new IllegalSwapException("one of the marbles not on track");

	if(marble1Base==true||marble2Base==true)
		throw new IllegalSwapException("Opponent in Base cell");}

    //9 🔍 REVIEW: Needs code review – R
    // Updated logic by Y – minor changes
    private void validateDestroy(int positionInPath) throws IllegalDestroyException {//🔴position in track?
        // if(positionInPath==-1 && this.track.get(positionInPath).getMarble()!=null) throw new IllegalDestroyException("Cannot destroy marble: Marble is not on track");
        if (positionInPath < 0 || positionInPath >= track.size()) {
	        throw new IllegalDestroyException("Invalid destroy: Position is outside the track.");
	    }
	    Cell targetCell = track.get(positionInPath);
	    
	    if (targetCell.getMarble() == null) {
	        throw new IllegalDestroyException("Invalid destroy: No marble found at the given track position.");
	    }
	    
	    if (targetCell.getCellType() == CellType.BASE) {
	        Colour marbleColour = targetCell.getMarble().getColour();
            int baseCellIndex = this.getBasePosition(marbleColour);
	        if (baseCellIndex==positionInPath) {
	            throw new IllegalDestroyException("Invalid destroy: Cannot destroy a marble that is safe in its own Base Cell.");
	        }
	    }
    }
    //10 🔍 REVIEW: Needs code review – R
    private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException {
	    Game game = (Game) this.gameManager;
	    Colour currentPlayerColour = game.getActivePlayerColour();
	    if (occupiedBaseCell.getCellType() == CellType.BASE) {    
	        if (occupiedBaseCell.getMarble() != null) {
	            Marble marbleInCell = occupiedBaseCell.getMarble();
	            if (marbleInCell.getColour() == currentPlayerColour) {
	                throw new CannotFieldException("Cannot place marble in the Base Cell: A marble of the same color is already there.");
	            }
	        }
	    }
	}
    //11 🔍 REVIEW: Needs code review – R
    private void validateSaving(int positionInSafeZone, int positionOnTrack) throws InvalidMarbleException {
        Game game = (Game) this.gameManager;
        Colour currentColour = game.getActivePlayerColour();

        if (this.track.get(positionOnTrack).getMarble() == null) {
            throw new InvalidMarbleException("Invalid marble: The selected marble is not on the track.");
        }
        if (this.getSafeZone(currentColour).get(positionInSafeZone) != null) {
            throw new InvalidMarbleException("Invalid marble: The selected marble is already in the Safe Zone.");
        }

    }
    //12
    public void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException {


    }
    //13
    public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
        validateSwap(marble_1,marble_2);
        int pos1= getPositionInPath(track,marble_1);
        int pos2= getPositionInPath(track,marble_2);
        track.get(pos1).setMarble(marble_2);
        track.get(pos2).setMarble(marble_1);



    }
    //14 🔍 REVIEW: Needs code review – Y
    public void destroyMarble(Marble marble) throws IllegalDestroyException{
        Game game = (Game) this.gameManager;
        Colour activeColour = game.getActivePlayerColour();
        if(marble.getColour()==activeColour){//if the marble belongs to the same player
            
        }
        this.validateDestroy(this.getPositionInPath(track, marble));
    }
    //15
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException{
    //    int targetPos= getBasePosition(marble.getColour());
    //    if(track.get(targetPos)!=null){
    //     validateFielding(Cell occupiedBaseCell);
    //     destroyMarble(track.get(targetPos).getMarble());
    //    }
    //    track.add(targetPos, marble);


    }
    //16 🔍 REVIEW: Needs code review – Y
    public void sendToSafe(Marble marble) throws InvalidMarbleException{
        ArrayList<Cell> safeZ = this.getSafeZone(marble.getColour());
        int posInSafeZone = this.getPositionInPath(safeZ, marble);
        int posInPath = this.getPositionInPath(this.track, marble);
        this.validateSaving(posInSafeZone, posInPath);//checking if 1.the marble was in the SafeZone
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
    //17 🔍 REVIEW: Needs code review – Y
    public ArrayList<Marble> getActionableMarbles(){
        Game gameRef = (Game)this.gameManager;
        Colour currentMarbleColour = gameRef.getActivePlayerColour();
        ArrayList<Marble> marbles = new ArrayList<>();
        for(Cell cell:this.track){
            if(cell.getMarble().getColour()==currentMarbleColour){
                marbles.add(cell.getMarble());
            }
        }
        for(Cell cell:this.getSafeZone(currentMarbleColour)){
            if(cell.getMarble()!=null)
                marbles.add(cell.getMarble());
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
  
