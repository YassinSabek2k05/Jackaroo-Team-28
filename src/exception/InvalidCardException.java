package exception;

abstract public class InvalidCardException extends InvalidSelectionException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InvalidCardException(){
        super();
    }
    InvalidCardException(String message){
        super(message);
    }
}
