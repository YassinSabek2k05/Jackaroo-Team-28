package exception;

public abstract class GameException extends Exception {
    GameException() {
        super();
    }
    GameException(String message){
        super(message);
    }
}
