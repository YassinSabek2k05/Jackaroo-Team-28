package exception;

abstract public class GameException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameException() {
        super();
    }
    GameException(String message){
        super(message);
    }
}
