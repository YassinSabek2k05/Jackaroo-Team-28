package view.board.cards;


import javafx.scene.layout.HBox;
import model.card.Card;
import view.GameView;

public class CardSelection {
    HBox humanPlayerHandBox;
    GameView gameView;
    Card selectedCard;
    public  CardSelection(GameView gameView) {
        this.gameView = gameView;
        this.humanPlayerHandBox = CardFunctions.createCardHBox(gameView.getGame().getPlayers().get(0).getHand(),50,100);;
    }
}
