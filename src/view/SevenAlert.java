package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SevenAlert extends Stage {
    GameView gameView;
    public SevenAlert(GameView gameView) {
        String title="Select Split Distance";
        String message="Please select the split distance for the 7 card. The distance can be between 2 and 6.";
        this.gameView = gameView;
        setTitle(title);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);

        Label label = new Label(message);
        Button num1 = new Button("2");
        num1.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(1);
            close();
        });
        Button num2 = new Button("2");
        num2.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(2);
            close();
        });
        Button num3 = new Button("3");
        num3.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(3);
            close();
        });
        Button num4 = new Button("4");
        num4.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(4);
            close();
        });
        Button num5 = new Button("5");
        num5.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(5);
            close();
        });
        Button num6 = new Button("6");
        num6.setOnAction(e -> {
            gameView.getGame().getBoard().setSplitDistance(6);
            close();
        });
        HBox buttonBox = new HBox(10, num2, num3, num4, num5, num6);
        VBox layout = new VBox(15, label, buttonBox);
        layout.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        num2.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        num3.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        num4.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        num5.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        num6.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        Scene scene = new Scene(layout, 300, 150);
        setScene(scene);
    }

    public static void show(GameView gameView) {
        SevenAlert alert = new SevenAlert(gameView);
        alert.showAndWait();
    }
}
