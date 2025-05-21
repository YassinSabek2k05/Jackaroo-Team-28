package view.board;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Colour;



public class NextPlayer {
    private int nextPlayerIndex;
    private int currentPlayerIndex;
    private int numberOfPlayers;
    StackPane stackPane;

    public NextPlayer() {
        this.currentPlayerIndex = 0;
        this.nextPlayerIndex = 1;
        Image image = new Image("resources/images/nextPlayer.png");
        Rectangle rectangle = new Rectangle(100,100);
        rectangle.setFill(Color.RED);
        Rectangle rectangle2 = new Rectangle(100,100);
        rectangle2.setFill(Color.BLUE);
        VBox vbox = new VBox(rectangle,rectangle2);
        this.stackPane = new StackPane();
        this.stackPane.getChildren().add(vbox);
        this.stackPane.getChildren().add(new ImageView(image));
        this.stackPane.setVisible(false);
    }

    public void next() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;
        nextPlayerIndex = (nextPlayerIndex + 1) % numberOfPlayers;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void update(){

    }
    public StackPane getStackPane() {
        return stackPane;
    }
}
