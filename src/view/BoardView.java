package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Colour;
import model.player.Marble;
import view.board.BoardBuilder;
import view.board.BoardMappings;
import view.board.Sync;
import view.board.cards.CardHand;

public class BoardView {
    Scene scene;
    public BoardView(GameView gameView){
        GridPane pane = new GridPane();
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        BoardBuilder boardBuilder = new BoardBuilder(gameView,new BoardMappings(gameView.getGame(),layoutConfig.getCellSize(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight()), pane);
        if(gameView.getGame()==null){
            gameView.setToInputNameView();
        }
        try {
            gameView.getGame().fieldMarble();
            gameView.getGame().endPlayerTurn();
        }catch (Exception e){
            System.out.println("No field marble");
        }
        try {
            gameView.getGame().fieldMarble();
            gameView.getGame().discardCard();

        }catch (Exception e){
            System.out.println("No field marble");
        }
        Marble marble = gameView.getGame().getBoard().getTrack().get(0).getMarble();
        gameView.getGame().getBoard().getTrack().get(9).setMarble(marble);
        gameView.getGame().sendHome(marble);
        System.out.println(gameView.getGame().getBoard().getTrack().get(0).getMarble().getColour());
        Sync.updateHomeCells(gameView, boardBuilder.getBoardMappings());
        Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
        pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/Background.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
        CardHand cardHand = new CardHand(gameView);
        cardHand.updateHand(gameView);

        this.scene = new Scene(pane,gameView.windowWidth,gameView.windowHeight);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                cardHand.updateHand(gameView);
                gameView.getStage().setFullScreen(!gameView.getStage().isFullScreen());
        }
        });


    }
    public Scene getScene(){
        return this.scene;
    }
}
