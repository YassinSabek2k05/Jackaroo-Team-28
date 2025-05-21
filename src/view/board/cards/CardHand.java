package view.board.cards;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.player.Player;
import view.GameView;
import view.LayoutConfig;

import java.util.ArrayList;

public class CardHand {
    private HBox humanPlayer;
    private VBox computerPlayer1;
    private HBox computerPlayer2;
    private VBox computerPlayer3;
    GameView gameView;

    public CardHand(GameView gameView) {
        this.gameView = gameView;
        ArrayList<Player> players = gameView.getGame().getPlayers();
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        int cardWidth = layoutConfig.getCardWidth();
        int cardHeight = layoutConfig.getCardHeight();
        this.humanPlayer = CardFunctions.createCardHBox(players.get(0).getHand(), cardWidth, cardHeight);
        this.computerPlayer1 = CardFunctions.createCardCPU1VBox(players.get(1).getHand().size(), cardWidth, cardHeight);;
        this.computerPlayer2 = CardFunctions.createCardCPU2HBox(players.get(1).getHand().size(), cardWidth, cardHeight);;
        this.computerPlayer3 = CardFunctions.createCardCPU3VBox(players.get(1).getHand().size(), cardWidth, cardHeight);;
    }
    public HBox getHumanPlayer() {
        return humanPlayer;
    }
    public VBox getComputerPlayer1() {
        return computerPlayer1;
    }
    public HBox getComputerPlayer2() {
        return computerPlayer2;
    }
    public VBox getComputerPlayer3() {
        return computerPlayer3;
    }
    public void updateHand() {
        try {
            gameView.getGame().discardCard();
        }
        catch (Exception e) {}
        this.humanPlayer.getChildren().clear();
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int cardWidth = gameView.getLayoutConfig().getCardWidth();
        int cardHeight = gameView.getLayoutConfig().getCardHeight();
        this.humanPlayer = CardFunctions.createCardHBox(players.get(0).getHand(), cardWidth, cardHeight);
    }
    public void updateComputerHand() {
        this.computerPlayer1.getChildren().clear();
        this.computerPlayer2.getChildren().clear();
        this.computerPlayer3.getChildren().clear();
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int cardWidth = gameView.getLayoutConfig().getCardWidth();
        int cardHeight = gameView.getLayoutConfig().getCardHeight();
        this.computerPlayer1 = CardFunctions.createCardCPU1VBox(players.get(1).getHand().size(), cardWidth, cardHeight);
        this.computerPlayer2 = CardFunctions.createCardCPU2HBox(players.get(1).getHand().size(), cardWidth, cardHeight);
        this.computerPlayer3 = CardFunctions.createCardCPU3VBox(players.get(1).getHand().size(), cardWidth, cardHeight);
    }

}
