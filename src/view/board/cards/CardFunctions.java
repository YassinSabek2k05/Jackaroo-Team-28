package view.board.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.card.Card;
import model.card.standard.Standard;
import model.card.wild.Wild;

import java.util.ArrayList;


public class CardFunctions {
    static Image img = new Image("resources/deck/card_back_black.png");

    public static ImageView createCardImageView(Card card, int width, int height) {
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
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        return imgView;
    }
    public static HBox createCardHBox(ArrayList<Card> cards, int width, int height) {
        HBox cardHBox = new HBox(20);
        for (Card card : cards) {
            ImageView cardImageView = createCardImageView(card, width, height);
            cardImageView.setOnMouseClicked(event -> {
                System.out.println("Card selected: " + card.getName());
                try {
                    cardImageView.setTranslateY(-20); // Raises the card slightly when clicked
                } catch (Exception e) {
                    System.out.println("Error playing card: " + e.getMessage());
                }
            });
            cardHBox.getChildren().add(cardImageView);
        }
        return cardHBox;
    }
    public static VBox createCardCPU1VBox(int cardsAmount, int width, int height) {
        VBox cardVBox = new VBox(-30);
        for(int i=0; i<cardsAmount; i++) {
            ImageView cardImageView = new ImageView(img);
            cardImageView.setFitWidth(width);
            cardImageView.setFitHeight(height);
            cardImageView.setRotate(-90);
            cardVBox.getChildren().add(cardImageView);
        }

        return cardVBox;
    }
    public static HBox createCardCPU2HBox(int cardsAmount, int width, int height) {
        HBox cardVBox = new HBox(20);
        for(int i=0; i<cardsAmount; i++) {
            ImageView cardImageView = new ImageView(img);
            cardImageView.setFitWidth(width);
            cardImageView.setFitHeight(height);
            cardVBox.getChildren().add(cardImageView);
        }
        cardVBox.setRotate(180);
        return cardVBox;
    }
    public static VBox createCardCPU3VBox(int cardsAmount, int width, int height) {
        VBox cardVBox = new VBox(-30);
        for(int i=0; i<cardsAmount; i++) {
            ImageView cardImageView = new ImageView(img);
            cardImageView.setFitWidth(width);
            cardImageView.setFitHeight(height);
            cardImageView.setRotate(-90);
            cardVBox.getChildren().add(cardImageView);
        }

        return cardVBox;
    }
}
