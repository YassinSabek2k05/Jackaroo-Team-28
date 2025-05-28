package view;

import javafx.application.Application;
import javafx.stage.Stage;
//--module-path "C:\Program Files\J --add-modules=javafx.controls,javafx.fxml --enable-native-access=javafx.graphics --sun-misc-unsafe-memory-access=allow
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameView gameView = new GameView(primaryStage);
        gameView.setToBoardView();
//        TestView testView = new TestView(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
