package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StartMenuView {
    Scene scene;
    public StartMenuView(GameView gameView){
        BorderPane borderPane = new BorderPane();
        
        borderPane.setBackground(new javafx.scene.layout.Background(
            new javafx.scene.layout.BackgroundImage(
            new Image("resources/images/Background.png"),
            javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
            javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
            javafx.scene.layout.BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, false, true)
            )
        ));
        Button button = new Button("Start");
        button.setOnAction(event -> {
            // Add your click handler code here
            System.out.println("Button clicked");
            gameView.setToInputNameView();
        });
        borderPane.setCenter(button);
        this.scene = new Scene(borderPane,gameView.windowHeight,gameView.windowWidth);

    }
    public Scene getScene(){
        return this.scene;
    }

}
