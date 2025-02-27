package exception;

public abstract class ActionException extends GameException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ActionException(){
        super();
    }
    ActionException(String message){
        super(message);
    }
}
