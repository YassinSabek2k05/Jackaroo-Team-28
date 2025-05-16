package view.board;

import engine.Game;
import engine.board.Cell;
import engine.board.SafeZone;
import exception.InvalidMarbleException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Colour;
import model.player.Marble;
import model.player.Player;
import view.mappings.BidirectionalCellMap;
import view.mappings.BidirectionalMarbleMap;
import view.mappings.BidirectionalPlayerMap;

import java.util.ArrayList;

public class BoardMappings {
    private final BidirectionalCellMap cellMap;
    private final ArrayList<BidirectionalMarbleMap> marbleMaps;
    private final ArrayList<BidirectionalPlayerMap> playerHomeZoneMaps;
    public BoardMappings(Game game, int cellSize) {
        this.cellMap = createCellMapping(game, cellSize);
        this.playerHomeZoneMaps = getBidirectionalPlayerMaps(game, cellSize);
        this.marbleMaps = createMarblesMapping(game, cellSize);
    }
    public BidirectionalCellMap getCellMaps() {
        return cellMap;
    }

    public ArrayList<BidirectionalMarbleMap> getMarbleMaps() {
        return marbleMaps;
    }

    public BidirectionalMarbleMap getMarbleMap(int index) {
        if (index < 0 || index >= marbleMaps.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return marbleMaps.get(index);
    }
    public BidirectionalMarbleMap getMarbleMap(Colour colour) {
        for (BidirectionalMarbleMap mapping : marbleMaps) {
            if (mapping.getColour().equals(colour)) {
                return mapping;
            }
        }
        return null;
    }
    public ArrayList<BidirectionalPlayerMap> getPlayerMaps() {
        return playerHomeZoneMaps;
    }
    public BidirectionalPlayerMap getPlayerMap(int index) {
        if (index < 0 || index >= playerHomeZoneMaps.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return playerHomeZoneMaps.get(index);
    }
    public BidirectionalPlayerMap getPlayerMap(Colour colour) {
        for (BidirectionalPlayerMap mapping : playerHomeZoneMaps) {
            if (mapping.getColour().equals(colour)) {
                return mapping;
            }
        }
        return null;
    }

    /**
     * Creates a mapping of cells to their corresponding StackPanes
     * @param game The game object containing the board and players
     * @param cellSize The size of each cell in pixels
     * @return A BidirectionalCellMap mapping cells to StackPanes
     */
    public static BidirectionalCellMap createCellMapping(Game game, int cellSize) {
        ArrayList<Image> order = new ArrayList<>();
        for(Player player: game.getPlayers()) {
            switch (player.getColour()) {
                case RED:
                    order.add(new Image("resources/images/redSafe.png"));
                    break;
                case BLUE:
                    order.add(new Image("resources/images/blueSafe.png"));
                    break;
                case GREEN:
                    order.add(new Image("resources/images/greenSafe.png"));
                    break;
                case YELLOW:
                    order.add(new Image("resources/images/yellowSafe.png"));
                    break;
                default:
                    break;
            }
        }
        ArrayList<Cell> cells = game.getBoard().getTrack();
        BidirectionalCellMap mapping = new BidirectionalCellMap();
        Image cellImg = new Image("resources/images/CELL.png");
        if(order.size()!=4) return null;
        for(Cell cell : cells) {
            int cellIndex = cells.indexOf(cell);
            switch (cellIndex) {
                case 0:
                    cellImg = order.get(0);
                    break;
                case 25:
                    cellImg = order.get(1);
                    break;
                case 50:
                    cellImg = order.get(2);
                    break;
                case 75:
                    cellImg = order.get(3);
                    break;
                default:
                    cellImg = new Image("resources/images/CELL.png");
            }
            ImageView imageView = new ImageView(cellImg);
            imageView.setFitWidth(cellSize);
            imageView.setFitHeight(cellSize);
            StackPane stackPane = new StackPane(imageView);
            mapping.put(cell, stackPane);

        }
        return mapping;
    }
    /**
     * Creates player to home zone cell mappings for all players in the game.
     * This method generates an ArrayList of BidirectionalPlayerMaps, where each map
     * connects a player to their home zone cells (represented as StackPanes).
     * Each player has 4 home cells which are visually represented using the default
     * cell image and organized in a consistent manner.
     * @param cellSize The size in pixels for rendering each cell
     * @return An ArrayList of BidirectionalPlayerMaps connecting players to their home zone cells
     */
    public static ArrayList<BidirectionalPlayerMap> getBidirectionalPlayerMaps(Game game,int cellSize){
        ArrayList<BidirectionalPlayerMap> maps = new ArrayList<>();
        Image cell = new Image("resources/images/CELL.png");

        ArrayList<Player> players = game.getPlayers();
        for(Player player:players){
            BidirectionalPlayerMap mapping = new BidirectionalPlayerMap();
            for(int i=0;i<=3;i++){
                ImageView img = new ImageView(cell);
                img.setFitHeight(cellSize);
                img.setFitWidth(cellSize);
                mapping.put(player, new StackPane(img));

            }
            maps.add(mapping);
        }
        return maps;
    }
    public static ArrayList<BidirectionalMarbleMap> createMarblesMapping(Game game, int cellSize) {
        ArrayList<Player> players = game.getPlayers();
        ArrayList<BidirectionalMarbleMap> mappings = new ArrayList<>();
        ArrayList<SafeZone> safeZones = game.getBoard().getSafeZones();
        ArrayList<Image> images = getMarbleImagesOrdered(safeZones);
        int marbleSize = cellSize*3 / 5;
        for(Player player : players){
            BidirectionalMarbleMap zoneMapping = new BidirectionalMarbleMap();
            for(Marble marble:player.getMarbles()){
                ImageView img = new ImageView(images.get(players.indexOf(player)));
                img.setOnMouseClicked(event ->{
                    System.out.println("Marble selected");
                    try {
                        game.selectMarble(marble);
                    } catch (InvalidMarbleException e) {
                        System.out.println("Invalid marble selected");
                    }
                });
                img.setFitHeight(marbleSize);
                img.setFitWidth(marbleSize);
                zoneMapping.put(marble, img);
            }
            mappings.add(zoneMapping);
        }
        return mappings;

    }
    public static ArrayList<Image> getMarbleImagesOrdered(ArrayList<SafeZone> safeZones) {
        ArrayList<Image> marbleImages = new ArrayList<>();

        for(SafeZone safeZone: safeZones){
            if(safeZone.getColour()==Colour.RED)
                marbleImages.add(new Image("resources/images/RED.png"));
            if(safeZone.getColour()==Colour.BLUE)
                marbleImages.add(new Image("resources/images/BLUE.png"));
            if(safeZone.getColour()==Colour.GREEN)
                marbleImages.add(new Image("resources/images/GREEN.png"));
            if(safeZone.getColour()==Colour.YELLOW)
                marbleImages.add(new Image("resources/images/YELLOW.png"));
        }

        return marbleImages;
    }

    public int getCellSize() {
        return cellMap.getCellSize();
    }
}

