package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;

import java.net.URL;

public class HowToPlayView {
    private final Scene scene;

    public HowToPlayView(GameView gameView) {
        BorderPane root = new BorderPane();

        // === Background ===
        BackgroundImage bgImage = new BackgroundImage(
                new Image("resources/images/Background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        root.setBackground(new Background(bgImage));

        // === WebView for Tutorial ===
        WebView webView = new WebView();
        webView.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Load HTML from resources
        URL htmlResource = getClass().getClassLoader().getResource("resources/howtoplay.html");
        if (htmlResource != null) {
            webView.getEngine().load(htmlResource.toExternalForm());
        } else {
            webView.getEngine().loadContent("<html><body><h1>Tutorial Not Found</h1></body></html>");
        }

        root.setCenter(webView);

        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-background-color: #BF9E64; -fx-text-fill: white; -fx-font-size: 16px;" +
                " -fx-font-weight: bold; -fx-font-family: 'Georgia';");
        backButton.setOnAction(e -> gameView.setToStartMenuView());

        HBox bottomBar = new HBox(backButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER_LEFT);

        root.setBottom(bottomBar);

        // === Scene ===
        this.scene = new Scene(root, gameView.windowWidth, gameView.windowHeight);
    }

    public Scene getScene() {
        return scene;
    }
}
