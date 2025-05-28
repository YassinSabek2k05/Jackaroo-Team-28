package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestView {
    public TestView(Stage stage) {

        TypingLabel typingLabel = new TypingLabel("Created by Team #28");

        Pane pane = new Pane();
        pane.getChildren().addAll(
                typingLabel.getLabel()
        );

        // Optional: position the label manually
        typingLabel.getLabel().setLayoutX(50);
        typingLabel.getLabel().setLayoutY(250);

        Scene scene = new Scene(pane, 300, 300);
        stage.setScene(scene);
    }
}
