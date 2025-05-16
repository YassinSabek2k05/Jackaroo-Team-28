package view;

import engine.Game;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameView {
    private Stage stage;
    private Game game;
    private final StartMenuView startMenuView;
    private BoardView boardView;
    private final InputNameView inputNameView;
    int windowHeight = 720;
    int windowWidth = 1280;

    public GameView(Stage primaryStage){
        this.startMenuView = new StartMenuView(this);
        this.inputNameView = new InputNameView(this);
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

        this.forFullScreen();
        this.stage.show();
        this.forMaximize();
    }
    public void setToStartMenuView(){
        this.stage.setScene(this.startMenuView.getScene());
        this.stage.show();
    }
    public void setToInputNameView(){
        this.stage.setScene(this.inputNameView.getScene());
        this.stage.show();
    }
    public void setToBoardView(){
        if(game!=null){
            this.stage.setScene(this.boardView.getScene());
            this.stage.show();
        }
        else{
            this.setToStartMenuView();
        }

    }
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

    public Stage getStage() {
        return this.stage;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Game getGame(){
        return this.game;
    }
    public void setBoardView(BoardView boardView){
        this.boardView = new BoardView(this);
    }
}
