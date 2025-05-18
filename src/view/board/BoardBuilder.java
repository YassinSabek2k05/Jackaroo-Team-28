package view.board;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.card.Card;
import view.GameView;
import view.board.cards.CardFunctions;
import view.board.cards.CardHand;

import java.util.ArrayList;

import static javafx.geometry.Pos.*;

public class BoardBuilder {
    private final BoardMappings boardMappings;
    Pane pane;

    public BoardBuilder(GameView gameView, BoardMappings boardMappings, GridPane root) {
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

        ArrayList<Card> cards = gameView.getGame().getPlayers().get(0).getHand();

        HBox cardBox = CardFunctions.createCardHBox(cards,50,100);
        CardHand cardHand = new CardHand(gameView);
        HBox humanPlayer = cardHand.getHumanPlayer();
        VBox computerPlayer1 = cardHand.getComputerPlayer1();
        HBox computerPlayer2 = cardHand.getComputerPlayer2();
        VBox computerPlayer3 = cardHand.getComputerPlayer3();


        ColumnConstraints left = new ColumnConstraints();
        left.setPercentWidth(25);

        ColumnConstraints center = new ColumnConstraints();
        center.setPercentWidth(50);

        ColumnConstraints right = new ColumnConstraints();
        right.setPercentWidth(0);
        root.setGridLinesVisible(true);

        root.getColumnConstraints().addAll(left, center, right);
        GridPane.setHalignment(this.pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(this.pane, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(computerPlayer1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(computerPlayer1, VPos.CENTER);
        GridPane.setHalignment(computerPlayer1, HPos.CENTER);
        GridPane.setHalignment(humanPlayer, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(humanPlayer, javafx.geometry.VPos.CENTER);
        humanPlayer.alignmentProperty().set(CENTER);
        computerPlayer1.alignmentProperty().set(CENTER_RIGHT);
        GridPane.setValignment(computerPlayer2, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(computerPlayer2, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(computerPlayer3, HPos.LEFT);
        GridPane.setValignment(computerPlayer3, VPos.CENTER);
        GridPane.setHalignment(cardBox, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(cardBox, javafx.geometry.VPos.CENTER);
        computerPlayer2.alignmentProperty().set(CENTER);
        computerPlayer3.alignmentProperty().set(CENTER_LEFT);
        root.add(this.pane,1,1);
        root.add(computerPlayer1,0,1);
        root.add(humanPlayer,1,3);
        root.add(computerPlayer2,1,0);
        root.add(computerPlayer3,2,1);
    }

    public BoardMappings getBoardMappings() {
        return boardMappings;
    }
}

