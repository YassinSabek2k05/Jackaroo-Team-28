package exception;

public class InvalidMarbleException extends GameException{

	public InvalidMarbleException(){
        super();
    }
    public InvalidMarbleException(String message){
        super(message);
    }
}
