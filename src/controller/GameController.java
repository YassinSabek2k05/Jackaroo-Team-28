package controller;

import engine.Game;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import view.CustomAlert;
import view.GameView;
import view.board.Sync;

public class GameController {
    private GameView gameView;

    public GameController(GameView gameView) {
        this.gameView = gameView;
    }
    public void playHumanTurn() {

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
                game.endPlayerTurn();
                Sync.updateTrackCells(gameView, gameView.getBoardView().getBoardMappings());
                gameView.getBoardView().getBoardBuilder().getCardSelection().updateHandView();

            }
            catch (InvalidCardException a){
//                System.out.println("Error: " + a.getMessage());
                CustomAlert.show("!!!!!", a.getMessage());
            }catch (InvalidMarbleException b){
                CustomAlert.show("!!!!!", b.getMessage());
                game.endPlayerTurn();
//            }
//            catch (InvalidMarbleException c){
//
//            }catch (InvalidMarbleException d){
//
//            }catch (InvalidMarbleException e){

            }catch (Exception z) {
                CustomAlert.show("!!!!!", z.getMessage());
                game.endPlayerTurn();
            }

        }
        gameView.getBoardView().getBoardBuilder().getFirePitView().updateFirePit();
    }
    public void playComputerTurn() {
        if(gameView.getBoardView() == null||gameView.getGame() == null) {
            System.out.println("Game is not initialized.");
            return;
        }
        System.out.println("Computer turn");
        PauseTransition pause3 = new PauseTransition(Duration.seconds(3));

        pause3.setOnFinished(event3 ->{
            try {
                gameView.getGame().playPlayerTurn();
            } catch (GameException e) {
                e.printStackTrace();
            }

        });
        Sync.updateTrackCells(gameView, gameView.getBoardView().getBoardMappings());
        gameView.getBoardView().getBoardBuilder().getCardSelection().updateHandView();
    }
    public void playGame() {
        while(gameView.getGame().checkWin() != null){
            if(gameView.getGame().getCurrentPlayerIndex()==0){
                System.out.println(gameView.getGame().getCurrentPlayerIndex());
                playHumanTurn();
            }else{
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(event->{
                    playComputerTurn();
                });
            }
            gameView.getBoardView().getBoardBuilder().getFirePitView().updateFirePit();
        }
    }
}
