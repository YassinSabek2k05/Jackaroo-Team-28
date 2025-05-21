package view.board;

import engine.board.Cell;
import engine.board.SafeZone;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.player.Marble;
import model.player.Player;
import view.GameView;
import view.board.mappings.BidirectionalCellMap;
import view.board.mappings.BidirectionalMarbleMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Sync {
    public static void updateTrackCells(GameView gameView, BoardMappings mappings) {
        ArrayList<Cell> trackCells = gameView.getGame().getBoard().getTrack();
        BidirectionalCellMap bidirectionalCellMap = mappings.getCellMaps();
        for(Cell cell : trackCells) {
            if(cell.getMarble()!=null){
                Marble marble = cell.getMarble();
                BidirectionalMarbleMap bidirectionalMarbleMap = mappings.getMarbleMap(marble.getColour());
                cleanCellView(cell, marble, bidirectionalMarbleMap, bidirectionalCellMap);
                if(bidirectionalMarbleMap.getImageView(marble)==null){
                    System.out.println(false);
                }
                if(bidirectionalMarbleMap.getImageView(marble)!=null)
                    bidirectionalCellMap.getPane(cell).getChildren().add(bidirectionalMarbleMap.getImageView(marble));
            }
            else {
                if(bidirectionalCellMap.getPane(cell) != null && bidirectionalCellMap.getPane(cell).getChildren().size() > 1){
                    bidirectionalCellMap.getPane(cell).getChildren().remove(1, bidirectionalCellMap.getPane(cell).getChildren().size());
                }
            }
        }

    }
    public static void updateSafeCells(GameView gameView, BoardMappings mappings) {
        ArrayList<SafeZone> safeZones = gameView.getGame().getBoard().getSafeZones();
        for(SafeZone safeZone : safeZones) {
            for(Cell cell : safeZone.getCells()) {
                if(cell.getMarble()!=null){
                    Marble marble = cell.getMarble();
                    BidirectionalMarbleMap bidirectionalMarbleMap = mappings.getMarbleMap(marble.getColour());
                    BidirectionalCellMap bidirectionalCellMap = mappings.getCellMaps();
                    cleanCellView(cell, marble, bidirectionalMarbleMap, bidirectionalCellMap);
                    if(bidirectionalMarbleMap.getImageView(marble)==null){
                        System.out.println(false);
                    }
                    if(bidirectionalMarbleMap.getImageView(marble)!=null)
                        bidirectionalCellMap.getPane(cell).getChildren().add(bidirectionalMarbleMap.getImageView(marble));
                }
                else {
                    if(mappings.getCellMaps().getPane(cell) != null && mappings.getCellMaps().getPane(cell).getChildren().size() > 1){
                        mappings.getCellMaps().getPane(cell).getChildren().remove(1, mappings.getCellMaps().getPane(cell).getChildren().size());
                    }
                }
            }
        }
    }
    public static void updateHomeCells(GameView gameView, BoardMappings mappings) {
        ArrayList<HashMap<Player, StackPane[]>> playerMaps = mappings.getPlayerToHomeZoneMaps();
        for(Player player : gameView.getGame().getPlayers()) {
            HashMap<Player, StackPane[]> map = playerMaps.get(gameView.getGame().getPlayers().indexOf(player));
            StackPane[] homeCells = map.get(player);
            for(StackPane homeCell : homeCells) {
                if(homeCell.getChildren().size() > 1){
                    homeCell.getChildren().remove(1, homeCell.getChildren().size());
                }
            }
            ArrayList<Marble> marbles = player.getMarbles();
            int k=0;
            for(Marble marble : marbles) {
                BidirectionalMarbleMap bidirectionalMarbleMap = mappings.getMarbleMap(marble.getColour());
                ImageView imageView = bidirectionalMarbleMap.getImageView(marble);
                homeCells[k++].getChildren().add(imageView);
            }


        }
    }

    private static void cleanCellView(Cell cell, Marble marble, BidirectionalMarbleMap bidirectionalMarbleMap, BidirectionalCellMap bidirectionalCellMap) {
        if(bidirectionalCellMap.getPane(cell) != null && bidirectionalCellMap.getPane(cell).getChildren().size() > 1){
            bidirectionalCellMap.getPane(cell).getChildren().remove(1, bidirectionalCellMap.getPane(cell).getChildren().size());
        }
    }
    public static void updateAll(GameView gameView, BoardMappings mappings){
        updateTrackCells(gameView, mappings);
        updateSafeCells(gameView, mappings);
        updateHomeCells(gameView, mappings);
        gameView.getBoardView().getBoardBuilder().getMarbleSelection().clearSelection();
        gameView.getBoardView().getBoardBuilder().getCardSelection().updateHandView();

    }

}
