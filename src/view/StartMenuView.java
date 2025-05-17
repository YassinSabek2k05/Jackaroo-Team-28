package view;

import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StartMenuView {
    Scene scene;
    ImageView logoView;
    public StartMenuView(GameView gameView){
        StackPane stackPane = getStackPane();

        //Logo
        Image logo = new Image("resources/images/logo_longx.png");
        logoView = new ImageView(logo);
        logoView.setFitHeight(1228);
        logoView.setFitWidth(1228);
        logoView.setPreserveRatio(true);
        logoView.setTranslateY(-300);



        //START
        Button button = new Button("START");
        button.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 40px;"
                + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        button.setPrefWidth(300);
        button.setPrefHeight(100);
        Image rotatedBoard= new Image("resources/images/frontPages.png");
        ImageView BoardImage= new ImageView(rotatedBoard);
        BoardImage.setFitWidth(900);
        BoardImage.setFitHeight(800);
        BoardImage.setTranslateY(400);
        button.setPrefSize(300,100);
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #847C54 ; -fx-text-fill: black; -fx-font-size: 40px; "
                    + "-fx-font-weight: bold; -fx-font-family: 'Verdana'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        // Revert color on exit
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 40px;"
                    + " -fx-font-weight: bold; -fx-font-family: 'Verdana'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(3),logoView);
        button.setOnAction(event -> {
            gameView.setToInputNameView();
        });


        //TUTORIAL
        Button tutorial = new Button("How To Play?");
        tutorial.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20px;"
                + " -fx-font-family: 'Verdana'; -fx-font-style: italic;"
                + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");

        tutorial.setOnMouseEntered(event -> {
            tutorial.setStyle("-fx-background-color: #847C54 ; -fx-text-fill: black; -fx-font-size: 20px; "
                    + " -fx-font-family: 'Verdana'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        // Revert color on exit
        tutorial.setOnMouseExited(event -> {
            tutorial.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20px;"
                    + " -fx-font-family: 'Verdana'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        tutorial.setPrefWidth(300);
        tutorial.setTranslateY(80);
        stackPane.getChildren().addAll(button,tutorial,BoardImage);
        stackPane.getChildren().add(logoView);

        tutorial.setOnAction(event -> {
            System.out.println("Button clicked");
            gameView.setToHowToPlayView();
        });

        this.scene = new Scene(stackPane);

    }

    private static StackPane getStackPane() {
        StackPane stackPane = new StackPane();

        stackPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/Background.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
        return stackPane;
    }

    public Scene getScene(){
        return this.scene;
    }

}
