package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.board.BoardBuilder;
import view.board.BoardMappings;

public class BoardView {
    Scene scene;
    int cellSize = 20;
    public BoardView(GameView gameView){
        BorderPane pane = new BorderPane();
        BoardBuilder boardBuilder = new BoardBuilder(gameView.getGame(),new BoardMappings(gameView.getGame(),this.cellSize), pane);
//        if(gameView.getGame()!=null){
//            Label label = new Label(gameView.getGame().getPlayers().get(0).getName());
//            pane.setCenter(label);
//        }
//        pane.setStyle("-fx-background-color: #050A30;");
        this.scene = new Scene(pane,gameView.windowHeight,gameView.windowWidth);
        scene.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.F11) {
            gameView.getStage().setFullScreen(!gameView.getStage().isFullScreen());
            System.out.println("F11 pressed. Fullscreen: " + gameView.getStage().isFullScreen());
        }
        });


    }
    public Scene getScene(){
        return this.scene;
    }
}
