package view.board;

import controller.MarbleSelection;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.card.Card;
import model.player.Player;
import view.GameView;
import view.LayoutConfig;
import view.board.cards.CardFunctions;
import view.board.cards.CardHand;
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
    CardHand cardHand;
    MarbleSelection marbleSelection;
    String playerName;
    GameView gameView;
    public BoardBuilder(GameView gameView, BoardMappings boardMappings, GridPane root) {
        label = new Label();
        this.gameView = gameView;
        if(gameView.getGame()!=null) this.playerName = gameView.getGame().getPlayers().get(0).getName();
        else this.playerName = "Player 1";
        BoardCoordinates boardCoordinates = new BoardCoordinates(gameView);
        this.boardMappings = boardMappings;
        this.pane = new Pane();
        this.pane.setMaxSize(670, 670);
        this.pane.setMinSize(670, 670);
        BoardCells boardCells = new BoardCells(gameView, boardMappings);
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

        marbleSelection = new MarbleSelection(gameView, boardMappings);
        this.cardSelection = new CardSelection(gameView);

        HBox cardBox1 = cardSelection.getHumanPlayerHandBox();
        ArrayList<Card> cards = gameView.getGame().getPlayers().get(0).getHand();


        HBox cardBox = CardFunctions.createCardHBox(cards,50,100);
        this.cardHand = new CardHand(gameView);
        HBox humanPlayer = cardSelection.getHumanPlayerHandBox();



        ColumnConstraints left = new ColumnConstraints();
        left.setPercentWidth(25);

        ColumnConstraints center = new ColumnConstraints();
        center.setPercentWidth(50);
        int[] a = this.getCurrentPlayerIndex();
        label.setText("NextPlayer:"+a[1]+"\n"+"CurrentPlayer:"+a[0]);
        label.setLayoutX(900);
        label.setLayoutY(500);
        root.getChildren().add(label);
        ColumnConstraints right = new ColumnConstraints();
        right.setPercentWidth(0);
        root.setGridLinesVisible(true);
        this.updateCpuHands();
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
        int cpu1 = this.gameView.getGame().getPlayers().get(1).getHand().size();
        int cpu2 = this.gameView.getGame().getPlayers().get(2).getHand().size();
        int cpu3 = this.gameView.getGame().getPlayers().get(3).getHand().size();
        System.out.println(cpu1);

        this.computerPlayer1 = createCardCPUV(cpu1, layoutConfig.getCardWidth(), layoutConfig.getCardHeight());
        this.computerPlayer2 = createCardCPUH(cpu2, layoutConfig.getCardWidth(), layoutConfig.getCardHeight());
        this.computerPlayer3 = createCardCPUV(cpu3, layoutConfig.getCardWidth(), layoutConfig.getCardHeight());
    }
    public VBox createCardCPUV(int size, int width, int height) {
        Image image = new Image("resources/deck/card_back_black.png");
        VBox cardBox = new VBox();
        cardBox.setSpacing(-10);
        cardBox.setAlignment(CENTER);
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            cardBox.getChildren().add(imageView);
            imageView.setRotate(90);
        }

        return cardBox;
    }
    public HBox createCardCPUH(int size, int width, int height) {
        Image image = new Image("resources/deck/card_back_black.png");
        HBox cardBox = new HBox();
        cardBox.setSpacing(20);
        cardBox.setAlignment(CENTER);
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            cardBox.getChildren().add(imageView);
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
    public CardHand getCardHand() {
        return this.cardHand;
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

    private ArrayList<Object[]> initializeNames() {
        ArrayList<Object[]> names = new ArrayList<>();
        names.add(new Object[]{this.playerName, new Image("resources/images/profile/player1.png")});
        names.add(new Object[]{"Ismaeil", new Image("resources/images/profile/player2.png")});
        names.add(new Object[]{"Malak", new Image("resources/images/profile/player3.png")});
        names.add(new Object[]{"Raghad", new Image("resources/images/profile/player4.png")});
        return names;
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
    public int[] getCurrentPlayerIndex() {
        ArrayList<Player> players = gameView.getGame().getPlayers();
        int i = 0;
        for (; i < players.size(); i++) {
            if (players.get(i).getColour()==gameView.getGame().getActivePlayerColour()) {
                break;
            }
        }
        int[] a = {i,(i+1)%4};
        return a;
    }
    public void updateLabel(){
        int[] a = this.getCurrentPlayerIndex();
        label.setText("NextPlayer:"+a[1]+"\n"+"CurrentPlayer:"+a[0]);
    }
}

