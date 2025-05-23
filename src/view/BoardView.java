package view;

import controller.GameController;
import exception.GameException;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.Colour;
import model.player.Marble;
import view.board.BoardBuilder;
import view.board.BoardMappings;
import view.board.NextPlayer;
import view.board.Sync;
import view.board.cards.CardSelection;

public class BoardView {
    int splitDistance;
    Boolean seven = false;
    Scene scene;
    GameController controller;
    BoardMappings boardMappings;
    BoardBuilder boardBuilder;
    public BoardView(GameView gameView){
        GridPane pane = new GridPane();
        RowConstraints firstRowConstraints = new RowConstraints();
        firstRowConstraints.setPrefHeight(150); // Set your desired constant height
        firstRowConstraints.setMinHeight(150);  // Minimum height
        firstRowConstraints.setMaxHeight(150);  // Maximum height
        firstRowConstraints.setVgrow(Priority.NEVER); // Prevent growing
        pane.getRowConstraints().add(firstRowConstraints);
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        if(gameView.getGame()!=null) {
            this.splitDistance = gameView.getGame().getBoard().getSplitDistance();
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
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ALT) {
                        System.out.println("ALT");
                        int[] a = boardBuilder.getCurrentPlayerArray();
//                        NextPlayer nextPlayer = boardCe.getNextPlayer();
//                        nextPlayer.update("sfdf", Colour.BLUE, Colour.GREEN, a[0], a[1]);
                        Sync.updateNextPlayer(gameView);
                    }
                    if (event.getCode() == KeyCode.ENTER) {
                        controller.playHumanTurn();
                        controller.playComputerTurn();

                    }
                        Sync.updateAll(gameView, boardBuilder.getBoardMappings());
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
