package view;



import java.io.IOException;

import com.sun.deploy.si.SingleInstanceImpl;
import engine.Game;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import view.board.cards.CardSelection;

public class InputNameView {
    private final Scene scene;
    private Runnable validateInput;
    private String playerName;
    private StartMenuView startMenuView;

    public InputNameView(GameView gameView){

        //Name Label
        Label label = new Label("Enter your Name");
        label.setStyle("-fx-font-family: 'tex gyre termes'; " +
                "-fx-text-fill: #BF9E64;"+
                "-fx-font-size: 40px; " +
                "-fx-font-weight: bold; " +
                "-fx-font-style: italic;"+
                "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)"
        );
        label.setTranslateY(-80);

        //TextField
        TextField inpuTextField = new TextField();
        inpuTextField.setMaxWidth(300);
        inpuTextField.setMaxHeight(50);


        //Button
        Button button = new Button("Enter");
        button.setTranslateY(90);

        button.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 35px;"
                + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                + "-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");

        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #847C54 ; -fx-text-fill: white; -fx-font-size: 35px;"
                    + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        // Revert color on exit
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 35px;"
                    + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                    + "-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        button.setOnAction(event ->{
            validateInput.run();
        });
        inpuTextField.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                validateInput.run();
            }
        });
        this.validateInput = () -> {
            String input = inpuTextField.getText().trim();
            if (input.isEmpty()) {
                CustomAlert.show("Invalid Input", "Please enter a name.");
            } else if (input.length() < 2) {
                CustomAlert.show("Invalid Input", "Name must be at least 2 characters.");
            } else if (input.length() > 15) {
                CustomAlert.show("Invalid Input", "Name must be less than 15 characters.");
            } else {
                playerName = input;
                try {
                    gameView.setGame(playerName);


                } catch (IOException e) {
                    CustomAlert.show("Invalid Input", "Please enter a valid name.");
                    gameView.setToInputNameView();
                }
                gameView.initializeBoardView();
                if(gameView.getBoardView()==null)
                    gameView.setToInputNameView();

                gameView.setToBoardView();
            }
        };

        //Return back
        Button back = new Button();
        back.setStyle("-fx-background-color: transparent");

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #847C54 ;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        // Revert color on exit
        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: transparent;"
                    + "-fx-effect: dropshadow(gaussian, black, 15, 0, 0, 0)" +
                    "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        });

        back.setTranslateX(-900);
        back.setTranslateY(-600);
        Image backLogo = new Image("resources/images/image0.png");
        ImageView backView = new ImageView(backLogo);
        backView.setFitHeight(170);
        backView.setFitWidth(240);
        back.setGraphic(backView);
        back.setPrefSize(200,150);

        back.setOnAction(event -> {

            System.out.println("Button clicked");
            gameView.setToStartMenuView();
        });

        StackPane box = new StackPane();
        box.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/Background.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
        box.getChildren().addAll(label,inpuTextField,button,back,backView);
        BorderPane root = new BorderPane();
        root.setCenter(box);
        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-background-color: #142D43; -fx-text-fill: white; -fx-font-size: 16px;" +
                " -fx-font-weight: bold; -fx-font-family: 'Georgia';");
        backButton.setOnAction(e -> gameView.setToStartMenuView());

        HBox bottomBar = new HBox(backButton);
        bottomBar.setStyle("-fx-background-image: url('/resources/images/BackgoundSand.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center; " +
                "-fx-background-repeat: no-repeat;");
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        root.setBottom(bottomBar);
        this.scene = new Scene(root, gameView.windowWidth,gameView.windowHeight);
    }

    public Scene getScene() {
        return this.scene;
    }
}
