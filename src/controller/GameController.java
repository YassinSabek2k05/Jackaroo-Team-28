package controller;

import engine.Game;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import model.player.Marble;
import view.CustomAlert;
import view.GameView;
import view.SevenAlert;
import view.board.BoardBuilder;
import view.board.Sync;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameController {
    private GameView gameView;

    public GameController(GameView gameView) {
        this.gameView = gameView;
    }
    public void playHumanTurn() {
        if(!gameView.getGame().canPlayTurn()){
            gameView.getGame().endPlayerTurn();
            return;
        }
        MarbleSelection marblesS = gameView.getBoardView().getBoardBuilder().getMarbleSelection();
        LinkedList<Marble> marbles = marblesS.getSelection();
        if(marbles.size() == 2&&gameView.getGame().getPlayers().get(0).getSelectedCard().getName().equals("Seven")) {
            SevenAlert.show(gameView);
        }
        for (Marble marble: marbles){
            try{
                gameView.getGame().getPlayers().get(0).selectMarble(marble);
            }
            catch(InvalidMarbleException e){}

        }
        if(gameView.getBoardView() == null||gameView.getGame() == null) {
            System.out.println("Game is not initialized.");
            return;
        }
        Game game = gameView.getGame();
        // Check if the game is over and display a message if it is
//        if (game.checkWin() != null) {
//            com.yourapp.ui.CustomAlert.show("Game Over", "Player " + game.checkWin() + " wins!");
//            return;
//        }
        if (game.getCurrentPlayerIndex() == 0) {
            try {
                game.playPlayerTurn();
                Sync.updateTrackCells(gameView, gameView.getBoardView().getBoardMappings());
                gameView.getBoardView().getBoardBuilder().getCardSelection().updateHandView();
                gameView.getBoardView().getBoardBuilder().updateCpuHands();

            }
            catch (InvalidCardException a){
//                System.out.println("Error: " + a.getMessage());
                CustomAlert.show("!!!!!", a.getMessage());

            }catch (InvalidMarbleException b){
                CustomAlert.show("!!!!!", b.getMessage());
//            }
//            catch (InvalidMarbleException c){
//
//            }catch (InvalidMarbleException d){
//
//            }catch (InvalidMarbleException e){

            }catch (Exception z) {
                CustomAlert.show("!!!!!", z.getMessage());
            }
            game.endPlayerTurn();


        }
        gameView.getBoardView().getBoardBuilder().getFirePitView().updateFirePit();
        gameView.getBoardView().getBoardBuilder().updateCpuHands();
        gameView.getBoardView().getBoardBuilder().updateLabel();
        marblesS.clearSelection();
    }
    public void playComputerTurn() {
        gameView.getBoardView().getBoardBuilder().updateCpuHands();
        PauseTransition pauseOuter = new PauseTransition();
        pauseOuter.setDuration(Duration.seconds(2));
        pauseOuter.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event1 -> {
                BoardBuilder boardBuilder = gameView.getBoardView().getBoardBuilder();
                try {
                    gameView.getGame().playPlayerTurn();
                } catch (GameException ignored) {
                }
                gameView.getGame().endPlayerTurn();
                Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
                gameView.getBoardView().getBoardBuilder().updateHand();
                boardBuilder.getFirePitView().updateFirePit();
                gameView.getBoardView().getBoardBuilder().updateCpuHands();
                gameView.getBoardView().getBoardBuilder().updateLabel();

                // Second transition
                PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
                pause2.setOnFinished(event2 -> {
                    try {
                        gameView.getGame().playPlayerTurn();
                    } catch (GameException ignored) {
                    }
                    gameView.getGame().endPlayerTurn();
                    Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
                    gameView.getBoardView().getBoardBuilder().updateHand();
                    boardBuilder.getFirePitView().updateFirePit();
                    gameView.getBoardView().getBoardBuilder().updateCpuHands();
                    gameView.getBoardView().getBoardBuilder().updateLabel();

                    // Third transition
                    PauseTransition pause3 = new PauseTransition(Duration.seconds(2));
                    pause3.setOnFinished(event3 -> {
                        try {
                            gameView.getGame().playPlayerTurn();
                        } catch (GameException ignored) {
                        }
                        boardBuilder.getCardHand().updateComputerHand();
                        gameView.getGame().endPlayerTurn();
                        Sync.updateTrackCells(gameView, boardBuilder.getBoardMappings());
                        gameView.getBoardView().getBoardBuilder().updateHand();
                        boardBuilder.getFirePitView().updateFirePit();

                        // Use Platform.runLater to show alert after animation completes
                        gameView.getBoardView().getBoardBuilder().updateLabel();
                        gameView.getBoardView().getBoardBuilder().updateCpuHands();

                        javafx.application.Platform.runLater(() -> {
                            CustomAlert.show("Your Turn", "Please select your marbles.");
                        });
                    });
                    pause3.play();
                });
                pause2.play();
            });
            pause.play();
        });
        pauseOuter.play();
    }

//    public void playGame() {
//
//    }
}
