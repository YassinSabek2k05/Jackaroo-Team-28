package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseAlert extends Stage {
    public PauseAlert(GameView gameView) {
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);

        // Create GridPane with 4 columns and 2 rows
        GridPane layout = new GridPane();

        // Configure column constraints (10%, 40%, 40%, 10%)
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40);
        col2.setHgrow(Priority.ALWAYS);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(40);
        col3.setHgrow(Priority.ALWAYS);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(10);
        col4.setHgrow(Priority.ALWAYS);

        layout.getColumnConstraints().addAll(col1, col2, col3, col4);

        // Configure row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        row1.setPercentHeight(50);

        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        row2.setPercentHeight(50);

        layout.getRowConstraints().addAll(row1, row2);

        // Create buttons
        Button restart = new Button("Restart");
        Button cancel = new Button("X");
        Button exit = new Button("Exit");
        Label pauseLabel = new Label("Game Paused");
        restart.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 15px;"
                + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                + "-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");
        exit.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 15px;"
                + " -fx-font-weight: bold; -fx-font-family: 'Georgia'; -fx-font-style: italic;"
                + "-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0)" +
                "dropshadow(gaussian, gray, 8, 0.5, 5, 5)");

        // Set button actions
        cancel.setOnAction(e -> close());
        exit.setOnAction(e -> System.exit(0));
        restart.setOnAction(e -> {
            gameView.setToInputNameView();
            gameView.setGameNull();
            close();
        });

        // Add components to the grid
        layout.add(pauseLabel, 1, 0, 2, 1); // Spans columns 1 and 2
        layout.add(restart, 1, 1);
        layout.add(exit, 2, 1);
        layout.add(cancel, 3, 0);

        // Set alignments - Fix: use HPos instead of Pos
        GridPane.setHalignment(pauseLabel, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(restart, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(exit, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(cancel, javafx.geometry.HPos.CENTER);

        // Style components
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        pauseLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold;");


        cancel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        Scene scene = new Scene(layout, 300, 150);
        setScene(scene);
    }

    // Fix: rename method to avoid overriding final method
    public static void showPauseAlert(GameView gameView) {
        PauseAlert alert = new PauseAlert(gameView);
        alert.showAndWait();
    }
}