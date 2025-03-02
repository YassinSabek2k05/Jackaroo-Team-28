package exception;

abstract public class InvalidCardException extends InvalidSelectionException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidCardException(){
        super();
    }
    public InvalidCardException(String message){
        super(message);
    }
}
