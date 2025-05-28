package view;

import controller.GameController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import view.board.BoardBuilder;
import view.board.BoardMappings;
import view.board.Sync;

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
            this.boardBuilder.getBoardCells().updateNextPlayer();
            Sync.updateHomeCells(gameView, boardMappings);
            Sync.updateTrackCells(gameView, boardMappings);
            Sync.updateSafeCells(gameView, boardMappings);
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

            if(gameView.getGame().checkWin()==null) {
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F11) {
                        gameView.getStage().setFullScreen(!gameView.getStage().isFullScreen());
                    }

                    if (event.getCode() == KeyCode.ENTER) {
                        if(!gameView.getGame().canPlayTurn()&&gameView.getGame().getCurrentPlayerIndex()==0){
                            gameView.getGame().endPlayerTurn();
                        }
                        controller.playHumanTurn();
                        controller.playComputerTurn();

                    }
                        Sync.updateAll(gameView, boardBuilder.getBoardMappings());
                        gameView.getBoardView().getBoardBuilder().updateHand();
                        boardBuilder.getFirePitView().updateFirePit();
                });
            }
//            Sync.updateNextPlayer(gameView);


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
