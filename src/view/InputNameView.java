package view;



import java.io.IOException;

import engine.Game;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class InputNameView {
    private Scene scene;
    private String playerName;
    public InputNameView(GameView gameView){
        Label label = new Label("Enter your Name");
        TextField inpuTextField = new TextField();
        Button button = new Button("Enter");
        button.setOnAction(event ->{
            if(inpuTextField.getText()!=null){
                playerName = inpuTextField.getText();
                try {
                    gameView.setGame(new Game(playerName));
                    gameView.setBoardView(new BoardView(gameView));
                    gameView.setToBoardView();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
            inpuTextField.setOnKeyPressed(event -> {
        if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
            if(inpuTextField.getText()!=null){
                playerName = inpuTextField.getText();
                try {
                    gameView.setGame(new Game(playerName));
                    gameView.setBoardView(new BoardView(gameView));
                    gameView.setToBoardView();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         });

        VBox box = new VBox();
        box.setBackground(new javafx.scene.layout.Background(
            new javafx.scene.layout.BackgroundImage(
            new Image("resources/images/Background.png"),
            javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
            javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
            javafx.scene.layout.BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, false, true)
            )
        ));
        box.getChildren().addAll(label,inpuTextField,button);
        this.scene = new Scene(box,gameView.windowHeight,gameView.windowWidth);
    }
    public Scene getScene() {
        return this.scene;
    }
}
