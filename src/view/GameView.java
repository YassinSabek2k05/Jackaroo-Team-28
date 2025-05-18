package view;

import engine.Game;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameView {
    private Stage stage;
    private Game game;
    private final LayoutConfig layoutConfig;
    private final StartMenuView startMenuView;
    private BoardView boardView;
    private final InputNameView inputNameView;
    private final HowToPlayView howToPlayView;
    int windowHeight = 1000;
    int windowWidth = 1600;

    public GameView(Stage primaryStage){
        this.startMenuView = new StartMenuView(this);
        this.inputNameView = new InputNameView(this);
        this.howToPlayView = new HowToPlayView(this);
        this.layoutConfig = new LayoutConfig();

        this.stage = primaryStage;
        stage.setHeight(windowHeight);
        stage.setWidth(windowWidth);
        stage.setMaxHeight(windowHeight);
        stage.setMaxWidth(windowWidth);
        stage.setMinHeight(windowHeight);
        stage.setMinWidth(windowWidth);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("resources/images/logo.png"));
        stage.setTitle("Jackaroo");
//        this.boardView = new BoardView(this);

        this.forFullScreen();
        this.stage.show();
        this.forMaximize();
    }

    //set scene
    public void setToStartMenuView(){
        startMenuView.logoView.setFitWidth(-200);
        startMenuView.logoView.setFitHeight(-400);
        startMenuView.logoView.setStyle("-fx-opacity: 0;");
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), startMenuView.logoView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2),startMenuView.logoView);
        scaleTransition.setToX(2.25);
        scaleTransition.setToY(2.25);
        scaleTransition.playFromStart();
        this.stage.setScene(this.startMenuView.getScene());
        this.stage.show();
    }
    public void setToInputNameView(){
        this.stage.setScene(this.inputNameView.getScene());
        this.stage.show();
    }
    public void setToBoardView(){
        if(game!=null){
            this.stage.setScene((new BoardView(this).getScene()));
            this.stage.show();
        }
        else{
            this.setToStartMenuView();
        }

    }
    public void setToHowToPlayView() {
        this.stage.setScene(this.howToPlayView.getScene());
        this.stage.show();
    }


    //layout
    public void forFullScreen(){
        this.stage.fullScreenProperty().addListener((obs, wasFullScreen, isNowFullScreen) -> {
            if (isNowFullScreen) {
                stage.setMinWidth(0);
                stage.setMinHeight(0);
                stage.setMaxWidth(Double.MAX_VALUE);
                stage.setMaxHeight(Double.MAX_VALUE);
            } else {
                stage.setMinWidth(windowWidth);
                stage.setMaxWidth(windowWidth);
                stage.setMinHeight(windowHeight);
                stage.setMaxHeight(windowHeight);
                stage.setWidth(windowWidth);
                stage.setHeight(windowHeight);
                stage.centerOnScreen();
            }
        });
    }
    public void forMaximize() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        this.stage.maximizedProperty().addListener((obs, wasMaximized, isNowMaximized) -> {
            if (isNowMaximized) {
                System.out.println("fdsf");
                stage.setWidth(screenWidth+25);
                stage.setHeight(screenHeight);
                stage.setMinWidth(0);
                stage.setMinHeight(0);
                stage.setMaxWidth(Double.MAX_VALUE);
                stage.setMaxHeight(Double.MAX_VALUE);
            } else {
                System.out.println("sdfsdfsdf");
                stage.setMinWidth(windowWidth);
                stage.setMaxWidth(windowWidth);
                stage.setMinHeight(windowHeight);
                stage.setMaxHeight(windowHeight);
                stage.setWidth(windowWidth);
                stage.setHeight(windowHeight);
                stage.centerOnScreen();
            }
        });
    }

    //setters
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setBoardView(BoardView boardView){
        this.boardView = new BoardView(this);
    }

    //getters
    public Game getGame(){
        return this.game;
    }
    public Stage getStage() {
        return this.stage;
    }
    public LayoutConfig getLayoutConfig() {
        return layoutConfig;
    }

}
