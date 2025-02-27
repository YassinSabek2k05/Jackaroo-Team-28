package exception;

abstract public class GameException extends Exception {
    GameException() {
        super();
    }
    GameException(String message){
        super(message);
    }
}
