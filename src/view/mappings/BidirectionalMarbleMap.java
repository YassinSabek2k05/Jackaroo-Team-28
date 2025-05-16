package view.mappings;

import javafx.scene.image.ImageView;
import model.Colour;
import model.player.Marble;

import java.util.HashMap;

public class BidirectionalMarbleMap {
    private final HashMap<Marble, ImageView> marbleToView = new HashMap<>();
    private final HashMap<ImageView, Marble> viewToMarble = new HashMap<>();

    /**
     * Associates a marble with its image view representation
     * @param marble The marble object
     * @param imageView The ImageView displaying the marble
     */
    public void put(Marble marble, ImageView imageView) {
        marbleToView.put(marble, imageView);
        viewToMarble.put(imageView, marble);
    }

    /**
     * Gets the ImageView for a specific marble
     * @param marble The marble to look up
     * @return The ImageView representing this marble
     */
    public ImageView getImageView(Marble marble) {
        return marbleToView.get(marble);
    }

    /**
     * Gets the Marble associated with an ImageView
     * @param imageView The ImageView to look up
     * @return The Marble represented by this ImageView
     */
    public Marble getMarble(ImageView imageView) {
        return viewToMarble.get(imageView);
    }

    /**
     * Gets the full mapping from marbles to image views
     * @return HashMap of Marble to ImageView mappings
     */
    public HashMap<Marble, ImageView> getMarbleToView() {
        return marbleToView;
    }

    /**
     * Gets the full mapping from image views to marbles
     * @return HashMap of ImageView to Marble mappings
     */
    public HashMap<ImageView, Marble> getViewToMarble() {
        return viewToMarble;
    }

    /**
     * Removes the mapping for a marble and its image view
     * @param marble The marble to remove
     * @return The removed ImageView, or null if not found
     */
    public ImageView remove(Marble marble) {
        ImageView view = marbleToView.get(marble);
        if (view != null) {
            marbleToView.remove(marble);
            viewToMarble.remove(view);
        }
        return view;
    }
    public Colour getColour() {
        if (marbleToView.isEmpty()) {
            return null;
        }
        Marble marble = marbleToView.keySet().iterator().next();
        return marble.getColour();
    }

    /**
     * Removes the mapping for an image view and its marble
     * @param imageView The image view to remove
     * @return The removed Marble, or null if not found
     */
    public Marble remove(ImageView imageView) {
        Marble marble = viewToMarble.get(imageView);
        if (marble != null) {
            viewToMarble.remove(imageView);
            marbleToView.remove(marble);
        }
        return marble;
    }
    /**
     * Gets all the marbles in the mapping
     * @return An array containing all marbles
     */
    public Marble[] getAllMarbles() {
        return marbleToView.keySet().toArray(new Marble[0]);
    }
}
