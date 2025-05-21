package view.board.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.card.Card;
import view.GameView;

import java.util.ArrayList;

public class FirePitView {
    GameView gameView;
    ArrayList<Card> cards;
    StackPane stackPane;
    public FirePitView(GameView gameView) {
        this.gameView = gameView;
        cards = gameView.getGame().getFirePit();
        ImageView img = new ImageView(new Image("resources/images/firepit.png"));
        img.setFitHeight(130);
        img.setFitWidth(90);
        stackPane = new StackPane(img);
    }
    public StackPane getStackPane() {
        return stackPane;
    }
    public void updateFirePit() {
        if (stackPane.getChildren().size() > 1) stackPane.getChildren().remove(1, stackPane.getChildren().size() - 1);
        if(!cards.isEmpty()) {
            ImageView cardImg = CardFunctions.createCardImageView(cards.get(cards.size()-1),80,116);
            stackPane.getChildren().add(cardImg);
        }
    }
}
