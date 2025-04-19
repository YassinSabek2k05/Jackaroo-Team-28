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
    //milestone2
    //1
    private int getPositionInPath(ArrayList<Cell> path, Marble marble){
            if(marble==null) return -1;
            for(int i=0;i<path.size();i++){
                if(path.get(i).getMarble()==marble)
                    return i;       
            }
            return -1;                              
    }
    
    //2 🔍 REVIEW: Needs code review – Y
    private ArrayList<Cell> getSafeZone(Colour colour){
        if(colour != Colour.BLUE && colour != Colour.RED && colour != Colour.GREEN && colour != Colour.YELLOW)
            return null;
        for(SafeZone safeZone : this.safeZones)
            if(safeZone.getColour()==colour)
                return safeZone.getCells();
        return null;
    }
    //3 🔍 REVIEW: Needs code review – Y
    private int getBasePosition(Colour colour){
        if(colour != Colour.BLUE && colour != Colour.RED && colour != Colour.GREEN && colour != Colour.YELLOW)
            return -1;
        int i=-1;
        for(SafeZone safeZone: this.safeZones){
            if(safeZone.getColour()==colour)
                i=this.getSafeZones().indexOf(safeZone);
        }
        int size = this.track.size();
        switch (i) {
            case 0:
                return 0;
            case 1:
                return size/4;   
            case 2: 
                return size/2;
            case 3:
                return size*3/4;             
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
        int size = this.track.size();
        switch (i) {
            case 0:
                return size-2;
            case 1:
                return (size/4)-2;   
            case 2: 
                return (size/2)-2;
            case 3:
                return (size*3/4)-2;             
            default:
                return -1;
        }
    }
    //5 🧪 TESTING: Check for edge cases – Y
    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws IllegalMovementException {

        Game game = (Game) this.gameManager;
        boolean five = (game.getActivePlayerColour()!=marble.getColour()); //if true the steps will be performed by one of the players on an opponent's marble
        boolean backwards = steps<0;
        ArrayList<Cell> path = new ArrayList<>();
        ArrayList<Cell> marbleSafeZone = this.getSafeZone(marble.getColour());
        int entry = getEntryPosition(marble.getColour());
        int current = getPositionInPath(this.track, marble);
        if(current!=-1){//the marble is on track

            if(!backwards)
                for(;steps>=0;steps--,current++){
                    if(current>=this.track.size())
                        current=0;
                    if(current==entry&&!five){
                        path.add(this.track.get(current));
                        steps--;
                        if(steps>=marbleSafeZone.size())
                            throw new IllegalMovementException("the number of moves exceeds the available cells.");
                        for(int i=0;steps>=0;steps--,i++){
                            path.add(marbleSafeZone.get(i));
                        }
                        return path;
                    }
                    else{
                        path.add(this.track.get(current));
                    }
            }
            else{
                steps*=-1;
                for(;steps>=0;steps--,current--){
                    if(current<0){
                        current = this.track.size()-1;
                    }
                    path.add(this.track.get(current));
                }
            }
        }
        else {//the marble is either in the safe zone or in the home zone
            ArrayList<Cell> safe = this.getSafeZone(marble.getColour());
            int posInSafeZone = getPositionInPath(safe, marble);
            if(posInSafeZone!=-1){//the marble is in the safezone

                if(backwards)
                    throw new IllegalMovementException("Cannot move backwards in Safe Zone.");
                for(;steps>=0&&posInSafeZone<marbleSafeZone.size();steps--,posInSafeZone++){
                    path.add(safe.get(posInSafeZone));
                    // System.out.println(steps==0);
                }
                // System.out.println(steps);
                if(steps==0)
                    throw new IllegalMovementException("the number of moves exceeds the available cells.");

            }
            else{//the marble is in the homezone
                throw new IllegalMovementException("To add the cell from the homezone to the track, the Card must be a King or an Ace.");
            }
        }
        return path;
    }
    //6
    private void validatePath1(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException {
        int counter=0;


	for(int i=0;i<fullPath.size();i++){
        if(fullPath.get(i)!=null&& destroy==false){
        	
        	Colour currColour=fullPath.get(i).getMarble().getColour(); //NullPointerException
        	CellType currType= fullPath.get(i).getCellType();
        
        if(i!=fullPath.size()-1)
        	counter++;
        
        
        	if(currColour==marble.getColour()) //will always throw an exception because the for loop starts at index 0 which contains the marble we want to move
        		throw new IllegalMovementException("Self-Blocking: A marble cannot move if there is another marble owned by the same player either in its path or at the target position.");
       
    
        
        	if(currType== CellType.ENTRY )
        		throw new IllegalMovementException("Safe Zone Blockage: Cannot enter when a marble is at safezone Entry");
        
        
    
        	if(currType == CellType.BASE&& currColour!= marble.getColour())
        		throw new IllegalMovementException("Base Cell Blockage: another player in current player's base cell/path");}

        //check not sure of the safe zone pt 
        if(fullPath.get(i)!=null&& fullPath.get(i).getCellType()==CellType.SAFE)
        	throw new IllegalMovementException("cannot bypass or land on Safe Zone marble");

        
		if(counter>1)
			throw new IllegalMovementException("Path Blockage: More than one marble blocking path");}
	
    }
    private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException {
        boolean movingOp = false;
        if(marble.getColour()!=this.gameManager.getActivePlayerColour())
            movingOp = true;
        int i=0;
        int otherFreq = 0; 
        for(;i<fullPath.size();i++){
            Cell cell= fullPath.get(i);
            Marble currentMarble = cell.getMarble();
            // if(cell==this.track.get(this.getEntryPosition(this.gameManager.getActivePlayerColour()))&&i<fullPath.size()-1&&fullPath.get(i+1).getCellType()!=CellType.SAFE)
            //     throw new IllegalMovementException();
            if(i!=0){
            if(currentMarble!=null){
                if(cell.equals(this.track.get(this.getBasePosition(currentMarble.getColour()))))//Base Cell Blockage
                    throw new IllegalMovementException("A marble's movement is blocked if another player's marble is in its player's Base cell, either in the path or target position.");

                if(!destroy){//not a king: selfblocking, path-blockage and safezone entry
                
                    if(currentMarble.getColour()==this.gameManager.getActivePlayerColour())
                        throw new IllegalMovementException("a player cannot bypass or destroy his own marble.");
                    otherFreq++;//path-blockage
                    if(cell.equals(this.track.get(this.getEntryPosition(this.gameManager.getActivePlayerColour()))) 
                    && currentMarble != null 
                    && i < fullPath.size() - 1 
                    && fullPath.get(i + 1).getCellType() == CellType.SAFE)
                     throw new IllegalMovementException("A marble cannot enter its player's Safe Zone if any marble is stationed at its player's Safe Zone Entry.");
                 
                }
                else{//king: bypasses selfblocking, path-blockage and safezone entry
                    if(cell.getCellType()==CellType.SAFE&&currentMarble.getColour()==this.gameManager.getActivePlayerColour())
                        throw new IllegalMovementException("a player cannot bypass or destroy his own marbles in the safezone.");                   
                }


            }
        }

        }
        if(!destroy){
            // Marble currentMarble = fullPath.get(i).getMarble();
                // if(currentMarble.getColour()==marble.getColour())
                //     throw new IllegalMovementException("Cannot destroy a marble of the same colour");
                if(otherFreq>1)
                    throw new IllegalMovementException("Movement is invalid if there is more than one marble (owned by any player) blocking the path.");
        }
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
        int marb1Index = this.getPositionInPath(track, marble_1);
        int marb2Index = this.getPositionInPath(track, marble_2);
        // Track Marbles: Swapping is prohibited if either of the involved marbles are
        // not on the general track (i.e. in Home Zone or Safe Zone).
        if(marb1Index==-1 || marb2Index==-1){
            throw new IllegalSwapException("Cannot Swap: At least one of the two marbles is not on the general track");
        }
        // –Base Cell Position: swapping is invalid if the other marble (belonging to the
        //  other player) is positioned in its Base cell.
        boolean order = this.gameManager.getActivePlayerColour()==marble_1.getColour();//if true then the marble of the active player is marble_1
        boolean mar1InBase = marb1Index==getBasePosition(marble_1.getColour());
        boolean mar2InBase = marb2Index==getBasePosition(marble_2.getColour());
        if(order&&mar2InBase || !order&&mar1InBase){
            throw new IllegalSwapException("Cannot swap with a marble positioned in its Base cell.");
        }

    }

    //9 🔍 REVIEW: Needs code review – R
    // Updated logic by Y – minor changes
    private void validateDestroy(int positionInPath) throws IllegalDestroyException {//🔴position in track?
        // if(positionInPath==-1 && this.track.get(positionInPath).getMarble()!=null) throw new IllegalDestroyException("Cannot destroy marble: Marble is not on track");
        
        if (positionInPath < 0 || positionInPath >= track.size()) {
	        throw new IllegalDestroyException("Invalid destroy: Position is outside the track.");
	    }  

	    Cell targetCell = track.get(positionInPath);
        Marble marble = targetCell.getMarble();
	    if(targetCell.getCellType()!=CellType.BASE) return;

	    if (marble == null) {
	        // throw new IllegalDestroyException("Invalid destroy: No marble found at the given track position.");
            return;
	    }
	    if(this.getBasePosition(marble.getColour())==positionInPath)
            throw new IllegalDestroyException("Invalid destroy: Cannot destroy a marble that is safe in its own Base Cell.");

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
        if(positionInSafeZone==-1 && positionOnTrack==-1)
            throw new InvalidMarbleException("The Selected Marble is already in the safezone");
        
        if (positionOnTrack != -1 && this.track.get(positionOnTrack).getMarble() == null) {
            throw new InvalidMarbleException("Invalid marble: The selected marble is not on the track.");
        }
        if (positionInSafeZone != -1 &&this.getSafeZone(currentColour).get(positionInSafeZone) != null) {
            throw new InvalidMarbleException("Invalid marble: The selected marble is already in the Safe Zone.");
        }

    }
    //12 🔍 REVIEW: Needs code review – Y
    @Override
    public void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException {
        ArrayList<Cell> fullPath = this.validateSteps(marble, steps);
        this.validatePath(marble, fullPath, destroy);
        move(marble, fullPath, destroy);
    }
    //13
    @Override
    public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
        validateSwap(marble_1,marble_2);
        int pos1= getPositionInPath(track,marble_1);
        int pos2= getPositionInPath(track,marble_2);
        track.get(pos1).setMarble(marble_2);
        track.get(pos2).setMarble(marble_1);
    }
    //14 🔍 REVIEW: Needs code review – Y
    @Override
    public void destroyMarble(Marble marble) throws IllegalDestroyException{
        Game game = (Game) this.gameManager;
        Colour activeColour = game.getActivePlayerColour();
        if(!(marble.getColour()==activeColour)){//if the marble belongs to the same player
            this.validateDestroy(this.getPositionInPath(track, marble));
        }
        this.track.get(getPositionInPath(track, marble)).setMarble(null);
        this.gameManager.sendHome(marble);
    }
    //15
    @Override
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException{
        
        if(marble==null)
            throw new CannotFieldException("No Marble is selected");
        Colour colour = marble.getColour();
        if(colour != Colour.BLUE && colour != Colour.RED && colour != Colour.GREEN && colour != Colour.YELLOW)
            return;
        int targetPos= getBasePosition(colour);
        if(targetPos>=track.size()||targetPos<0)
            return;
        Cell occupiedBaseCell = this.track.get(targetPos);
        if(track.get(targetPos).getMarble()!=null){
            validateFielding(occupiedBaseCell);
            destroyMarble(track.get(targetPos).getMarble());
        }
        track.get(targetPos).setMarble(marble);


    }
    //16 🔍 REVIEW: Needs code review – Y
    @Override
    public void sendToSafe(Marble marble) throws InvalidMarbleException{
        ArrayList<Cell> safeZ = this.getSafeZone(marble.getColour());
        boolean full=true;
        for(Cell cell: safeZ){
            if(cell.getMarble()==null) full=false;
        }
        if(full) throw new InvalidMarbleException("SafeZone is full");
        int posInSafeZone = this.getPositionInPath(safeZ, marble);
        int posInPath = this.getPositionInPath(this.track, marble);
        this.validateSaving(posInSafeZone, posInPath);//checking if 1.the marble was in the SafeZone
        ArrayList<Integer> unoccupied = new ArrayList<>();
        for(Cell cell: safeZ)
            if(cell.getMarble()==null) 
                unoccupied.add(safeZ.indexOf(cell));
        if(unoccupied.size()==0) 
            throw new InvalidMarbleException();   
        int rand = (int)(Math.random() * unoccupied.size());
        Cell target = safeZ.get(unoccupied.get(rand));
        target.setMarble(marble);
        this.track.get(getPositionInPath(this.track, marble)).setMarble(null);
        
    }
    //17 🔍 REVIEW: Needs code review – Y
    @Override
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
    public static void main(String[] args) throws IOException, IllegalMovementException {
        Game game = new Game("Yassin");
        ArrayList<Colour> colourOrder = new ArrayList<>();
        colourOrder.add(Colour.RED);
        colourOrder.add(Colour.BLUE);
        colourOrder.add(Colour.GREEN);
        colourOrder.add(Colour.YELLOW);

        Board board = new Board(colourOrder, game);
        board.track.get(5).setMarble(new Marble(Colour.RED));
        Colour currColour=board.track.get(5).getMarble().getColour();
        Marble mar = new Marble(currColour);
        Marble mar2 = new Marble(currColour);
        // board.track.get(board.getEntryPosition(mar.getColour())).setMarble(mar);
        board.getSafeZone(mar.getColour()).get(0).setMarble(mar);
        board.track.get(99).setMarble(mar2);

        ArrayList<Cell> a  = (board.validateSteps(mar, 1));
        for(Cell b : a){
            System.out.println(b.getMarble());
            System.out.println(board.track.indexOf(b));
        }

    }
}
  
