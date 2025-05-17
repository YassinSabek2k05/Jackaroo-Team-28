package view.board;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.GameView;

public class BoardBuilder {
    private final BoardMappings boardMappings;
    Pane pane;

    public BoardBuilder(GameView gameView, BoardMappings boardMappings, BorderPane root) {
        BoardCoordinates boardCoordinates = new BoardCoordinates(gameView);
        this.boardMappings = boardMappings;
        this.pane = new Pane();
        this.pane.setMaxSize(670, 670);
        this.pane.setMinSize(670, 670);
        pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/board.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));

        BoardCells boardCells = new BoardCells(gameView, boardMappings);
        boardCells.addAllCells(pane);



        root.setCenter(this.pane);
    }
}

