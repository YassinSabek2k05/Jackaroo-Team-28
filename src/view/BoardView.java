package view;

import controller.GameController;
import exception.GameException;
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
import view.board.cards.CardSelection;

public class BoardView {
    Scene scene;
    GameController controller;
    BoardMappings boardMappings;
    BoardBuilder boardBuilder;
    public BoardView(GameView gameView){
        GridPane pane = new GridPane();
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        if(gameView.getGame()!=null) {
            this.boardMappings = new BoardMappings(gameView.getGame(), layoutConfig.getCellSize(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight());
            this.boardBuilder = new BoardBuilder(gameView, this.boardMappings, pane);
            controller = gameView.getController();

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

            this.scene = new Scene(pane, gameView.windowWidth, gameView.windowHeight);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F11) {
//                    cardHand.updateHand(gameView);
                    gameView.getStage().setFullScreen(!gameView.getStage().isFullScreen());
                }
            });

            if(gameView.getGame().checkWin()==null) {
//                if (gameView.getGame().getCurrentPlayerIndex() == 0){
//                    scene.setOnKeyPressed(event -> {
//                        if (event.getCode() == KeyCode.ENTER) {
//                            controller.playHumanTurn();
//                            for(int i =0;(gameView.getGame().getCurrentPlayerIndex()!=0)&&i<3;i++){
//                                controller.playComputerTurn();
//                                Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
//                                boardBuilder.getFirePitView().updateFirePit();
//                            }
//
//                        }
//                    });
//                }
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        try {
                            gameView.getGame().playPlayerTurn();
                        } catch (GameException e) {
                            System.out.println(e.getMessage());
                        }
                        if(gameView.getGame().getCurrentPlayerIndex()==3){
                            CustomAlert.show("!!!!","Your Turn");
                        }
                        gameView.getGame().endPlayerTurn();

                    }
                    if(event.getCode() == KeyCode.ALT){
                        this.controller.playHumanTurn();
                    }

                        Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
                        gameView.getBoardView().getBoardBuilder().updateHand();
                        boardBuilder.getFirePitView().updateFirePit();
                });



            }

        }
    }
    public Scene getScene(){
        return this.scene;
    }
    public BoardMappings getBoardMappings() {
        return boardMappings;
    }
    public BoardBuilder getBoardBuilder() {
        return boardBuilder;
    }

}
