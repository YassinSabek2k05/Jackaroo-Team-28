package view.board;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    Rectangle rectangle1;
    Rectangle rectangle2;
    StackPane currentPlayer;


    public NextPlayer() {
        this.currentPlayerIndex = 0;
        this.nextPlayerIndex = 1;
        Image image = new Image("resources/images/nextPlayer.png");
        rectangle1 = new Rectangle(190,210);
        rectangle2 = new Rectangle(160,50);
        this.currentPlayer = new StackPane(rectangle1);

        VBox vbox = new VBox(currentPlayer,rectangle2);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        this.stackPane = new StackPane();
        this.stackPane.getChildren().add(vbox);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);
        stackPane.setLayoutY(imageView.getLayoutY());
        stackPane.setLayoutX(imageView.getLayoutX());
        this.stackPane.getChildren().add(imageView);
    }

    public void next() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;
        nextPlayerIndex = (nextPlayerIndex + 1) % numberOfPlayers;
    }

    public void update(String name, Colour colour1, Colour colour2, int currentPlayerIndex) {
        // First clear any previous content except the rectangle
        if (currentPlayer.getChildren().size() > 1) {
            currentPlayer.getChildren().remove(1, currentPlayer.getChildren().size());
        }

        // Create a new VBox with labels
        VBox vbox = new VBox();
        Label nameLabel = new Label(name);
        Label indexLabel = new Label("" + currentPlayerIndex);
        indexLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        vbox.getChildren().addAll(nameLabel, indexLabel);
        vbox.setAlignment(Pos.CENTER);

        // Add the VBox to the StackPane
        currentPlayer.getChildren().add(vbox);

        // Update colors
        rectangle1.setFill(colourToColor(colour1));
        rectangle2.setFill(colourToColor(colour2));
    }
    public StackPane getStackPane() {
        return stackPane;
    }
    public Color colourToColor(Colour colour) {
        switch (colour) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case YELLOW:
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }
}
