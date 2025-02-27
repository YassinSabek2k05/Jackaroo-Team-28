package exception;

public class InvalidMarbleException extends GameException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InvalidMarbleException(){
        super();
    }
    InvalidMarbleException(String message){
        super(message);
    }
}
