package view.board;

import javafx.geometry.Point2D;
import engine.Game;
import engine.board.Board;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardBuilder {
    private final BoardMappings boardMappings;
    Pane pane;
    public BoardBuilder(Game game, BoardMappings boardMappings, BorderPane root) {
        this.boardMappings = boardMappings;
        this.pane = new Pane();
        this.pane.setMaxSize(670, 670);
        this.pane.setMinSize(670, 670);
        pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(
                        new Image("resources/images/board.png"),
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
        int cellSize = boardMappings.getCellSize();
        int addPaddingAll = 30;
        int paddingTop = 25+addPaddingAll;
        int paddingBottom = 670-25-cellSize-addPaddingAll;
        int paddingRight=670-25-cellSize-addPaddingAll;
        int paddingLeft=25+addPaddingAll;
        Pane cells = new Pane();
        cells.setPrefSize(670, 670);
        Point2D[] positions = getPoints(paddingTop, paddingBottom,paddingLeft, paddingRight, cellSize,670,670);
        for (int i = 0; i < positions.length && i < game.getBoard().getTrack().size(); i++) {

            StackPane cellPane = boardMappings.getCellMaps().getPane(game.getBoard().getTrack().get(i));
            System.out.println(cellPane);
            cellPane.setLayoutX(positions[i].getX());
            cellPane.setLayoutY(positions[i].getY());
            System.out.println(i);
            cells.getChildren().add(cellPane);

        }
        pane.getChildren().add(cells);
        root.setCenter(this.pane);

    }
    public Point2D[] getPoints(int paddingTop, int paddingBottom,  int paddingLeft, int paddingRight, int cellSize, int width,int height){
        int middleVer = (height-cellSize*5)/2;
        int middleHor = (width-cellSize*5)/2;
        double halfSize = cellSize*0.7;
        return new Point2D[]{
                new Point2D(middleHor, paddingBottom),
                new Point2D(middleHor, paddingBottom-cellSize),
                new Point2D(middleHor, paddingBottom-cellSize*2),
                new Point2D(middleHor, paddingBottom-cellSize*3),
                new Point2D(middleHor, paddingBottom-cellSize*4),
                new Point2D(middleHor, paddingBottom-cellSize*5),
                new Point2D(middleHor, paddingBottom-cellSize*6),
                new Point2D(middleHor, paddingBottom-cellSize*7),
                new Point2D(middleHor, paddingBottom-cellSize*8),
                new Point2D(middleHor, paddingBottom-cellSize*9),
                new Point2D(middleHor-halfSize, paddingBottom-cellSize*9.75),
                new Point2D(middleHor-halfSize*2, paddingBottom-cellSize*10.5),
                new Point2D(middleHor-halfSize*3, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*3, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*4, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*5, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*6, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*7, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*8, paddingBottom-cellSize*11.25),
                new Point2D(middleHor-halfSize*3-cellSize*9, paddingBottom-cellSize*11.25),


                new Point2D(middleHor-halfSize*3-cellSize*9, paddingBottom-cellSize*11.25-cellSize),
                new Point2D(middleHor-halfSize*3-cellSize*9, paddingBottom-cellSize*11.25-cellSize*2),
                new Point2D(middleHor-halfSize*3-cellSize*9, paddingBottom-cellSize*11.25-cellSize*3),
                new Point2D(middleHor-halfSize*3-cellSize*9, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*8, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*7, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*6, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*5, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*4, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*3, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3-cellSize, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3, paddingBottom-cellSize*11.25-cellSize*4),
                new Point2D(middleHor-halfSize*3+halfSize, paddingBottom-cellSize*11.25-cellSize*4.75),
                new Point2D(middleHor-halfSize*3+halfSize*2, paddingBottom-cellSize*11.25-cellSize*5.5),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*6.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*7.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*8.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*9.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*10.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*12.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*14.25),
                new Point2D(middleHor-halfSize*3+halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize, paddingBottom-cellSize*11.25-cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*3, paddingBottom-cellSize*11.25-cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*2),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*3),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*4),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*6),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*7),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*8),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*9),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*9.75),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*10.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*5, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*6, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*7, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*8, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*11.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*12.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*14.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*4, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*5, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*6, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*7, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*8, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*15.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*16),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*16.75),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*17.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*18.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*19.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*20.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*21.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*22.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*23.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*24.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*25.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*26.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*26.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*26.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*3, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*26.5),
        };
    }
}

