package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomAlert extends Stage {

    public CustomAlert(String title, String message) {
        setTitle(title);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);

        Label label = new Label(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> close());

        VBox layout = new VBox(15, label, okButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        okButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        Scene scene = new Scene(layout, 300, 150);
        setScene(scene);
    }

    public static void show(String title, String message) {
        CustomAlert alert = new CustomAlert(title, message);
        alert.showAndWait();
    }
}
