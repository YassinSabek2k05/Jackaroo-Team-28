package controller;

import javafx.scene.image.ImageView;
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
                    ImageView imageView = b.getImageView(m);
                    if(selectedMarbles.contains(m)) {
                        System.out.println("Marble deselected");
                        selectedMarbles.remove(m);
                        imageView.setScaleX(1);
                        imageView.setScaleY(1);
                    }
                    else {
                        System.out.println("Marble selected");
                        selectedMarbles.add(m);
                        imageView.setScaleX(1.2);
                        imageView.setScaleY(1.2);
                        if(selectedMarbles.size() == 3) {
                            Marble c = selectedMarbles.remove(0);
                            ImageView imageView1 = b.getImageView(c);
                            imageView1.setScaleX(1);
                            imageView1.setScaleY(1);
                        }
                    }
                });
            }
        }
    }
    public void clearSelection() {
        for(BidirectionalMarbleMap b : bm) {
            for(Marble m : b.getAllMarbles()) {
                ImageView imageView = b.getImageView(m);
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        }
        selectedMarbles.clear();
    }

    public LinkedList<Marble> getSelection() {
        return selectedMarbles;
    }
}
