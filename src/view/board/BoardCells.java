package view.board;

import engine.board.SafeZone;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
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
    FirePitView firePitView;

    public BoardCells(GameView gameView, BoardMappings boardMappings) {
        this.gameView = gameView;
        this.boardMappings = boardMappings;
        this.coordinates = new BoardCoordinates(gameView);
        this.firePitView = new FirePitView(gameView);
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
    public void addProfilePictures(Pane main,ArrayList<Pane> namePanes){
        Point2D[] positions = new Point2D[4];
        positions[2] = new Point2D(50, -100);
        positions[3] = new Point2D(760, -50);
        positions[1] = new Point2D(-100,600);
        positions[0] = new Point2D(760, 670);

        int i = 0;
        for(Pane pane: namePanes) {
            if (i < positions.length) {
                System.out.println(pane.getLayoutX());
                pane.setLayoutX(positions[i].getX());
                pane.setLayoutY(positions[i].getY());
                i++;
            }
             main.getChildren().add(pane);
        }


    }

    public void addAllCells(Pane pane, ArrayList<Pane> namePanes) {
        this.addTrackCells(pane);
        this.addHomeCells(pane);
        this.addSafeZoneCells(pane);
        this.addFirePit(pane);
        this.addProfilePictures(pane,namePanes);
    }
    public void addFirePit(Pane pane) {
        StackPane stackPane = this.firePitView.getStackPane();
        stackPane.setLayoutX(((double) 670 /2)-45);
        stackPane.setLayoutY(((double) 670 /2)-60);
        pane.getChildren().add(stackPane);
    }

    public FirePitView getFirePitView() {
        return firePitView;
    }
}
