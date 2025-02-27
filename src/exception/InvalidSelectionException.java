package exception;

public class InvalidSelectionException extends GameException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InvalidSelectionException(){
        super();
    }
    InvalidSelectionException(String message){
        super(message);
    }
}
