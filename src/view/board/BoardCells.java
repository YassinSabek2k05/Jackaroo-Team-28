package view.board;

import engine.board.SafeZone;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import model.player.Player;
import view.GameView;
import view.board.cards.FirePitView;
import view.board.mappings.BidirectionalCellMap;
import view.board.mappings.BidirectionalPlayerMap;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardCells {
    private final BoardCoordinates coordinates;
    private final GameView gameView;
    private final BoardMappings boardMappings;

    public BoardCells(GameView gameView, BoardMappings boardMappings) {
        this.gameView = gameView;
        this.boardMappings = boardMappings;
        this.coordinates = new BoardCoordinates(gameView);
    }
    public void addTrackCells(Pane pane) {
        this.coordinates.getTrackCells();
        Point2D[] positionsTrack = coordinates.getTrackCells();
        BoardMappings boardMappings = this.boardMappings;
        for (int i = 0; i < positionsTrack.length && i < gameView.getGame().getBoard().getTrack().size(); i++) {
            StackPane cellPane = boardMappings.getCellMaps().getPane(gameView.getGame().getBoard().getTrack().get(i));
            cellPane.setLayoutX(positionsTrack[i].getX());
            cellPane.setLayoutY(positionsTrack[i].getY());
            pane.getChildren().add(cellPane);
        }
    }
    public void addHomeCells(Pane pane) {
        ArrayList<HashMap<Player,StackPane[]>> playerMaps = boardMappings.getPlayerToHomeZoneMaps();
        ArrayList<Player> players = this.gameView.getGame().getPlayers();
        Point2D[] positionsHome = coordinates.getHomeCells();
        int k=0;
        for(Player player: players){
            HashMap<Player,StackPane[]> map = playerMaps.get(players.indexOf(player));
            StackPane[] homeCells = map.get(player);
            for(StackPane cell: homeCells){
                if(k>=positionsHome.length) break;
                cell.setLayoutX(positionsHome[k].getX());
                cell.setLayoutY(positionsHome[k].getY());
                pane.getChildren().add(cell);
                k++;
            }
        }
    }
    public void addSafeZoneCells(Pane pane) {
        Point2D[] positionsSafeZone = coordinates.getSafeZoneCells();
        System.out.println(boardMappings.getSafeZoneMaps().size());
        int k=0;
        int i=0;

        for(BidirectionalCellMap curr: boardMappings.getSafeZoneMaps()){
            System.out.println(curr);
            if(k>=positionsSafeZone.length) break;
            SafeZone safeZone = gameView.getGame().getBoard().getSafeZones().get(i++);
            for(int j=0; j<safeZone.getCells().size(); j++){
                if(k>=positionsSafeZone.length) break;
                StackPane imgPane = curr.getPane(safeZone.getCells().get(j));
                System.out.println(positionsSafeZone[k].getX());
                Circle blue = new Circle(20, javafx.scene.paint.Color.BLUE);
                imgPane.setOnMouseClicked(event -> {
                    System.out.println(gameView.getGame().getBoard().getTrack().indexOf(curr.getCell(imgPane)));
                    imgPane.getChildren().add(blue);
                });
                imgPane.setLayoutX(positionsSafeZone[k].getX());
                imgPane.setLayoutY(positionsSafeZone[k].getY());
                k++;
                pane.getChildren().add(imgPane);
            }
        }
    }

    public void addAllCells(Pane pane) {
        this.addTrackCells(pane);
        this.addHomeCells(pane);
        this.addSafeZoneCells(pane);
    }
    public void addFirePit(Pane pane) {
        StackPane stackPane = gameView.getBoardView().getBoardBuilder().getFirePitPane();
        stackPane.setLayoutX(((double) 670 /2)+30);
        stackPane.setLayoutX(((double) 670 /2)+30);
    }
}
