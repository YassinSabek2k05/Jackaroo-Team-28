package view.board.cards;


import exception.InvalidCardException;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.card.Card;
import model.card.standard.Standard;
import model.card.wild.Wild;
import view.GameView;
import view.LayoutConfig;

import java.util.ArrayList;
import java.util.Arrays;

public class CardSelection {
    ArrayList<Card> cards;
    HBox humanPlayerHandBox;
    GameView gameView;
    Boolean[] selectedCards = {false,false,false,false};
    public CardSelection(GameView gameView) {
        this.cards = gameView.getGame().getPlayers().get(0).getHand();
        this.gameView = gameView;
        this.humanPlayerHandBox = new HBox(20);
        updateHandView();

    }
    public HBox getHumanPlayerHandBox() {
        return humanPlayerHandBox;
    }
    public void updateHandView(){
        this.cards = gameView.getGame().getPlayers().get(0).getHand();
        this.humanPlayerHandBox.getChildren().clear();
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        for(Card card : cards) {
            if(card==null) continue;
            int index = cards.indexOf(card);
            Image img = createCardImage(card);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(layoutConfig.getCardWidth());
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(event -> {
                if(selectedCards[index]) {
                    clearSelectedCards();
                    selectedCards[index] = false;
                    imageView.setTranslateY(0);
                }
                else {
                    clearSelectedCards();
                    selectedCards[index] = true;
                    imageView.setTranslateY(-20);
                    try {
                        this.gameView.getGame().getPlayers().get(0).selectCard(card);
                    } catch (InvalidCardException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            this.humanPlayerHandBox.getChildren().add(imageView);

        }
    }
    public Image createCardImage(Card card) {
        if(card == null) return null;
        String cardName = card.getName();
        String rank = "";
        Image img = null;
        if(card instanceof Standard){
            String suit = ((Standard)card).getSuit()+"";
            suit = suit.toLowerCase();
            switch (cardName) {
                case "Ace":
                    rank = "ace";
                    break;
                case "Two":
                    rank = "2";
                    break;
                case "Three":
                    rank = "3";
                    break;
                case "Four":
                    rank = "4";
                    break;
                case "Five":
                    rank = "5";
                    break;
                case "Six":
                    rank = "6";
                    break;
                case "Seven":
                    rank = "7";
                    break;
                case "Eight":
                    rank = "8";
                    break;
                case "Nine":
                    rank = "9";
                    break;
                case "Ten":
                    rank = "10";
                    break;
                case "Jack":
                    rank = "jack";
                    break;
                case "Queen":
                    rank = "queen";
                    break;
                case "King":
                    rank = "king";
                    break;
                default:
                    break;
            }
            String path = "resources/deck/"+rank+"_of_"+suit+"s.png";
            img = new Image(path);
        }
        else if(card instanceof Wild){
            String path = "resources/deck/"+cardName+".png";
            img = new Image(path);
        }
        return img;
    }
    public void clearSelectedCards() {
        Arrays.fill(selectedCards, false);
        for(Node imageView : this.humanPlayerHandBox.getChildren()) {
            imageView.setTranslateY(0);
        }
    }
}
