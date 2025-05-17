package view.board.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.card.Card;
import model.card.standard.Standard;
import model.card.wild.Wild;


public class CardFunctions {
    public static ImageView createCardImageView(Card card) {
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
        return new ImageView(img);
    }
}
