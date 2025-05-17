package view.board.mappings;

import model.card.Card;

import javax.swing.text.html.ImageView;
import java.util.HashMap;

public class BidirectionalCardMap {
    private final HashMap<ImageView, Card> cardMap;
    private final HashMap<Card, ImageView> cardViewMap;

    public BidirectionalCardMap() {
        cardMap = new HashMap<>();
        cardViewMap = new HashMap<>();
    }

    public void put(Card card, ImageView imageView) {
        cardMap.put(imageView, card);
        cardViewMap.put(card, imageView);
    }

    public Card getCard(ImageView imageView) {
        return cardMap.get(imageView);
    }

    public ImageView getImageView(Card card) {
        return cardViewMap.get(card);
    }

    public boolean containsCard(Card card) {
        return cardViewMap.containsKey(card);
    }

    public boolean containsImageView(ImageView imageView) {
        return cardMap.containsKey(imageView);
    }


}
