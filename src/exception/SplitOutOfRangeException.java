package exception;

public class SplitOutOfRangeException extends GameException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SplitOutOfRangeException(){
        super();
    }
    SplitOutOfRangeException(String message){
        super(message);
    }
}
