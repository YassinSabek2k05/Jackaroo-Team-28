package exception;

public class GameException extends Exception {
    private GameException() {
        super();
    }
    private GameException(String message){
        super(message);
    }
}
