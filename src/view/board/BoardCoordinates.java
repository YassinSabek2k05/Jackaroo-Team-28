package view.board;

import javafx.geometry.Point2D;
import view.GameView;

public class BoardCoordinates {
    private final Point2D[] trackCells;
    private final Point2D[] homeCells;
    private final Point2D[] safeZoneCells;
    public BoardCoordinates(GameView gameView) {
        this.trackCells = initializeTrackCells(gameView);
        this.homeCells = initializeHomeCells(gameView);
        this.safeZoneCells = initializeSafeCells(gameView);
    }
    public Point2D[] initializeTrackCells(GameView gameView) {
            int cellSize = gameView.getLayoutConfig().getCellSize();
            int paddingBottom = gameView.getLayoutConfig().getPaddingBottom();
            int width = gameView.getLayoutConfig().getBoardWidth();
            int height = gameView.getLayoutConfig().getBoardHeight();
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
    public Point2D[] initializeSafeCells(GameView gameView){
        int cellSize = gameView.getLayoutConfig().getCellSize();
        int paddingBottom = gameView.getLayoutConfig().getPaddingBottom();
        int width = gameView.getLayoutConfig().getBoardWidth();
        int height = gameView.getLayoutConfig().getBoardHeight();
        int middleVer = (height-cellSize*5)/2;
        int middleHor = (width-cellSize*5)/2;
        double halfSize = cellSize*0.7;
        return new Point2D[]{


                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*25.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*24.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*23.5),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*9-cellSize*9-halfSize*3-cellSize*2, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*22.5),

                new Point2D(middleHor-halfSize*3-cellSize*8, paddingBottom-cellSize*11.25-cellSize*2),
                new Point2D(middleHor-halfSize*3-cellSize*7, paddingBottom-cellSize*11.25-cellSize*2),
                new Point2D(middleHor-halfSize*3-cellSize*6, paddingBottom-cellSize*11.25-cellSize*2),
                new Point2D(middleHor-halfSize*3-cellSize*5, paddingBottom-cellSize*11.25-cellSize*2),

                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*14.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*12.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*2, paddingBottom-cellSize*11.25-cellSize*11.25),

                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*8, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*7, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*6, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*13.25),
                new Point2D(middleHor-halfSize*3+halfSize*3+cellSize*4+halfSize*3+cellSize*5, paddingBottom-cellSize*11.25-cellSize*15.25+cellSize*13.25),


        };
    }
    public Point2D[] initializeHomeCells(GameView gameView){
        int cellSize = gameView.getLayoutConfig().getCellSize();
        int borderPaneWidth = gameView.getLayoutConfig().getBoardWidth();
        int borderPaneHeight = gameView.getLayoutConfig().getBoardHeight();
        int paddingBottom = gameView.getLayoutConfig().getPaddingBottom();
        double gap = cellSize*0.75;
        return new Point2D[]{
                new Point2D((double) (borderPaneWidth * 3) /4-cellSize-gap, borderPaneHeight*3/4),
                new Point2D((double) (borderPaneWidth * 3) /4-cellSize+gap, borderPaneHeight*3/4),
                new Point2D((double) (borderPaneWidth * 3) /4-cellSize, (double) ((borderPaneHeight * 3) /4+gap)),
                new Point2D((double) (borderPaneWidth * 3) /4-cellSize, ((double) (borderPaneHeight * 3) /4-gap)),
                new Point2D((double) borderPaneWidth /4-cellSize-gap, borderPaneHeight*3/4),
                new Point2D((double) borderPaneWidth /4-cellSize+gap, borderPaneHeight*3/4),
                new Point2D((double) borderPaneWidth /4-cellSize, borderPaneHeight*3/4+gap),
                new Point2D(borderPaneWidth/4-cellSize, borderPaneHeight*3/4-gap),
                new Point2D(borderPaneWidth/4-gap, borderPaneHeight/4),
                new Point2D(borderPaneWidth/4, borderPaneHeight/4+gap),
                new Point2D(borderPaneWidth/4, borderPaneHeight/4-gap),
                new Point2D(borderPaneWidth/4+gap, borderPaneHeight/4),
                new Point2D(borderPaneWidth*3/4-cellSize-gap, borderPaneHeight/4),
                new Point2D(borderPaneWidth*3/4-cellSize+gap, borderPaneHeight/4),
                new Point2D(borderPaneWidth*3/4-cellSize, borderPaneHeight/4+gap),
                new Point2D(borderPaneWidth*3/4-cellSize, borderPaneHeight/4-gap),
        };
    }

    //getters
    public Point2D[] getTrackCells() {
        return trackCells;
    }
    public Point2D[] getHomeCells() {
        return homeCells;
    }
    public Point2D[] getSafeZoneCells() {
        return safeZoneCells;
    }
}
