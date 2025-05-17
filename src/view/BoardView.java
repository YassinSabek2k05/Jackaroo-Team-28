package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.board.BoardBuilder;
import view.board.BoardMappings;

public class BoardView {
    Scene scene;
    public BoardView(GameView gameView){
        BorderPane pane = new BorderPane();
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        BoardBuilder boardBuilder = new BoardBuilder(gameView,new BoardMappings(gameView.getGame(),layoutConfig.getCellSize(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight()), pane);
//        if(gameView.getGame()!=null){
//            Label label = new Label(gameView.getGame().getPlayers().get(0).getName());
//            pane.setCenter(label);
//        }
        pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/Background.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
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
