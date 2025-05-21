package controller;

import model.player.Marble;
import view.GameView;
import view.board.BoardMappings;
import view.board.mappings.BidirectionalMarbleMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class MarbleSelection {
    ArrayList<BidirectionalMarbleMap> bm;
    LinkedList<Marble> selectedMarbles;
    public MarbleSelection(GameView gameView, BoardMappings boardMappings) {
        this.selectedMarbles = new LinkedList<>();
        this.bm = boardMappings.getMarbleMaps();
        for(BidirectionalMarbleMap b : bm) {
            for(Marble m : b.getAllMarbles()) {
                b.getImageView(m).setOnMouseClicked(event -> {
                    if(selectedMarbles.contains(m)) {
                        selectedMarbles.remove(m);
                        javafx.scene.effect.DropShadow borderGlow = new javafx.scene.effect.DropShadow();
                        borderGlow.setColor(javafx.scene.paint.Color.GOLD);
                        borderGlow.setWidth(30);
                        borderGlow.setHeight(30);
                    }
                    else {
                        selectedMarbles.add(m);
                        javafx.scene.effect.DropShadow borderGlow = new javafx.scene.effect.DropShadow();
                        borderGlow.setColor(javafx.scene.paint.Color.GOLD);
                        borderGlow.setWidth(30);
                        borderGlow.setHeight(30);
                    }
                });
            }
        }
    }
}
