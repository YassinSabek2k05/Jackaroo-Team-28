package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.board.NextPlayer;

public class TestView {
    public TestView(Stage stage) {
        NextPlayer nextPlayer = new NextPlayer();
        Pane pane = new Pane();
        pane.getChildren().add(nextPlayer.getStackPane());
        Scene scene = new Scene(pane, 300, 300);
        stage.setScene(scene);
    }
}
