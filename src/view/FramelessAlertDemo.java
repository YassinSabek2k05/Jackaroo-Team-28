package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FramelessAlertDemo extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button showAlertBtn = new Button("Show Frameless Alert");
        showAlertBtn.setOnAction(e -> showCustomFramelessAlert("Frameless Alert", "This is a frameless custom alert!"));

        VBox root = new VBox(showAlertBtn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Main Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCustomFramelessAlert(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED); // Frameless

        // Close Button
        Button closeButton = new Button("✕");
        closeButton.setFont(Font.font(14));
        closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: red;");
        closeButton.setOnAction(e -> alertStage.close());

        // Title Bar (can be dragged)
        HBox titleBar = new HBox(closeButton);
        titleBar.setAlignment(Pos.TOP_RIGHT);
        titleBar.setStyle("-fx-background-color: #333333; -fx-padding: 5;");

        // Drag logic
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            alertStage.setX(event.getScreenX() - xOffset);
            alertStage.setY(event.getScreenY() - yOffset);
        });

        // Content
        Label label = new Label(message);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font(16));

        VBox layout = new VBox(titleBar, label);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #444; -fx-padding: 20; -fx-border-color: #888; -fx-border-width: 2;");
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 150);
        alertStage.setScene(scene);
        alertStage.showAndWait();
    }
}

