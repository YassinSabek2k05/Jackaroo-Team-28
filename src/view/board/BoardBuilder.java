package view.board;

import controller.MarbleSelection;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import model.Colour;
import model.card.Card;
import model.player.Player;
import view.GameView;
import view.LayoutConfig;
import view.PauseAlert;
import view.board.cards.CardFunctions;
import view.board.cards.CardSelection;
import view.board.cards.FirePitView;


import java.util.ArrayList;
import java.util.Stack;

import static javafx.geometry.Pos.*;

public class BoardBuilder {
    Label label;
    VBox computerPlayer1;
    HBox computerPlayer2;
    VBox computerPlayer3;
    private final BoardMappings boardMappings;
    ArrayList<Object[]> names;
    ArrayList<Pane> namePanes;
    Pane pane;
    CardSelection cardSelection;
    FirePitView firePitView;
    MarbleSelection marbleSelection;
    String playerName;
    GameView gameView;
    BoardCells boardCells;
    public BoardBuilder(GameView gameView, BoardMappings boardMappings, GridPane root) {
        this.computerPlayer1 = new VBox();
        this.computerPlayer1.setAlignment(CENTER);
        this.computerPlayer1.setSpacing(10);
        this.computerPlayer2 = new HBox();
        this.computerPlayer2.setAlignment(CENTER);
        this.computerPlayer2.setSpacing(10);
        this.computerPlayer3 = new VBox();
        this.computerPlayer3.setAlignment(CENTER);
        this.computerPlayer3.setSpacing(10);
        label = new Label();
        this.gameView = gameView;
        if(gameView.getGame()!=null) this.playerName = gameView.getGame().getPlayers().get(0).getName();
        else this.playerName = "Player 1";
        BoardCoordinates boardCoordinates = new BoardCoordinates(gameView);
        this.boardMappings = boardMappings;
        this.pane = new Pane();
        this.pane.setMaxSize(670, 670);
        this.pane.setMinSize(670, 670);
        boardCells = new BoardCells(gameView, boardMappings);
        this.names = this.initializeNames();
        firePitView = boardCells.getFirePitView();
        this.namePanes = this.initializeNamePanes();
        this.names = this.initializeNames();
        pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/board.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));

        boardCells.addAllCells(pane,namePanes);
        this.marbleSelection = new MarbleSelection(gameView, boardMappings);
        this.cardSelection = new CardSelection(gameView);

        HBox cardBox1 = cardSelection.getHumanPlayerHandBox();
        ArrayList<Card> cards = gameView.getGame().getPlayers().get(0).getHand();

        HBox cardBox = CardFunctions.createCardHBox(cards,50,100);
        HBox humanPlayer = cardSelection.getHumanPlayerHandBox();



        ColumnConstraints left = new ColumnConstraints();
        left.setPercentWidth(25);

        ColumnConstraints center = new ColumnConstraints();
        center.setPercentWidth(50);

        ColumnConstraints right = new ColumnConstraints();
        right.setPercentWidth(25);
        this.updateCpuHands();
        ImageView pause = new ImageView(new Image("resources/images/pause.png"));
        pause.setFitWidth(100);
        pause.setFitHeight(100);
        Button pauseButton = new Button(" ");
        pauseButton.setGraphic(pause);
        pauseButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        pauseButton.setOnAction(event -> {
            PauseAlert.showPauseAlert(gameView);
            System.out.println("Pause clicked");
        });
        GridPane.setHalignment(pauseButton, HPos.RIGHT);
        root.add(pauseButton, 2,0);
        root.getColumnConstraints().addAll(left, center, right);
        GridPane.setHalignment(this.pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(this.pane, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(computerPlayer1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(computerPlayer1, VPos.CENTER);
        GridPane.setHalignment(computerPlayer1, HPos.CENTER);
        GridPane.setHalignment(humanPlayer, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(humanPlayer, javafx.geometry.VPos.CENTER);
        humanPlayer.alignmentProperty().set(CENTER);
        computerPlayer1.alignmentProperty().set(CENTER_RIGHT);
        GridPane.setValignment(computerPlayer2, javafx.geometry.VPos.CENTER);
        GridPane.setHalignment(computerPlayer2, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(computerPlayer3, HPos.LEFT);
        GridPane.setValignment(computerPlayer3, VPos.CENTER);
        GridPane.setHalignment(cardBox, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(cardBox, javafx.geometry.VPos.CENTER);
        computerPlayer2.alignmentProperty().set(CENTER);
        computerPlayer3.alignmentProperty().set(CENTER_LEFT);
        root.add(this.pane,1,1);
        root.add(computerPlayer1,0,1);
        root.add(humanPlayer,1,3);
        root.add(computerPlayer2,1,0);
        root.add(computerPlayer3,2,1);
    }

    public void updateCpuHands() {
        LayoutConfig layoutConfig = gameView.getLayoutConfig();
        this.computerPlayer1.getChildren().clear();
        this.computerPlayer2.getChildren().clear();
        this.computerPlayer3.getChildren().clear();
        this.computerPlayer1.getChildren().addAll(createCardCPUV(gameView.getGame().getPlayers().get(1).getHand().size(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight()));
        this.computerPlayer2.getChildren().addAll(createCardCPUH(gameView.getGame().getPlayers().get(2).getHand().size(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight()));
        this.computerPlayer3.getChildren().addAll(createCardCPUV(gameView.getGame().getPlayers().get(3).getHand().size(), layoutConfig.getCardWidth(), layoutConfig.getCardHeight()));
    }
    public ArrayList<ImageView> createCardCPUV(int size, int width, int height) {
        Image image = new Image("resources/deck/card_back_black.png");
        ArrayList<ImageView> cardBox = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            cardBox.add(imageView);
            imageView.setRotate(90);
        }

        return cardBox;
    }
    public ArrayList<ImageView> createCardCPUH(int size, int width, int height) {
        Image image = new Image("resources/deck/card_back_black.png");
        ArrayList<ImageView> cardBox = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            cardBox.add(imageView);
        }

        return cardBox;
    }
    public BoardMappings getBoardMappings() {
        return boardMappings;
    }
    public CardSelection getCardSelection() {
        return cardSelection;
    }

    public FirePitView getFirePitView() {
        return firePitView;
    }

    public void updateHand(){
        this.cardSelection.updateHandView();
    }

    public MarbleSelection getMarbleSelection() {
        return marbleSelection;
    }
    private ArrayList<Pane> initializeNamePanes() {
        ArrayList<Pane> namePanes = new ArrayList<>();
        for (Object[] name : names) {
            VBox namePane = new VBox();
            namePane.setAlignment(CENTER);
            namePane.setSpacing(10);
            namePane.getChildren().add(new Label((String) name[0]));
            ImageView imageView = new ImageView((Image) name[1]);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            namePane.getChildren().add(imageView);
            namePanes.add(namePane);
        }
        return namePanes;
    }

    public ArrayList<Object[]> initializeNames() {
        ArrayList<Object[]> names = new ArrayList<>();
        names.add(new Object[]{this.playerName, new Image("resources/images/profile/player1.png")});
        names.add(new Object[]{"Ismaeil", new Image("resources/images/profile/player2.png")});
        names.add(new Object[]{"Malak", new Image("resources/images/profile/player3.png")});
        names.add(new Object[]{"Raghad", new Image("resources/images/profile/player4.png")});
        return names;
    }
    public ArrayList<String> getNamesList() {
        ArrayList<String> namesList = new ArrayList<>();
        for (Object[] name : names) {
            namesList.add((String) name[0]);
        }
        return namesList;
    }

    public Pane[] getNamePanes() {
        if (namePanes == null) {
            namePanes = initializeNamePanes();
        }
        return namePanes.toArray(new Pane[0]);
    }

    public String getNames(int currentPlayerIndex) {
        if (names == null) {
            names = initializeNames();
        }
        return (String) names.get(currentPlayerIndex)[0];
    }
    public int[] getCurrentPlayerArray() {
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int i = 0;
        for (; i < players.size(); i++) {
            if (players.get(i).getColour()==gameView.getGame().getActivePlayerColour()) {
                break;
            }
        }
        return new int[]{i,(i+1)%4};
    }
    public BoardCells getBoardCells() {
        return boardCells;
    }
    public Colour getCurrentPlayerColour() {
        return gameView.getGame().getActivePlayerColour();
    }
    public Colour getNextPlayerColour() {
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int i =-1;
        for(Player p : players) {
            if(p.getColour()==gameView.getGame().getActivePlayerColour()) {
                i = players.indexOf(p);
                break;
            }
        }
        if(i == players.size()-1) {
            i = 0;
        }
        else {
            i++;
        }
        return players.get(i).getColour();
    }
    public int getCurrentPlayerIndex() {
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int i = 0;
        for (; i < players.size(); i++) {
            if (players.get(i).getColour()==gameView.getGame().getActivePlayerColour()) {
                break;
            }
        }
        return i;
    }
}

