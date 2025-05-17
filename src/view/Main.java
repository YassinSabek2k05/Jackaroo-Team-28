package view;

import javafx.application.Application;

import javafx.stage.Stage;
import view.GameView;
//--module-path "C:\Program Files\Java\javafx-sdk-24.0.1\lib" --add-modules=javafx.controls,javafx.fxml --enable-native-access=javafx.graphics --sun-misc-unsafe-memory-access=allow
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameView gameView = new GameView(primaryStage);
        gameView.setToBoardView();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
