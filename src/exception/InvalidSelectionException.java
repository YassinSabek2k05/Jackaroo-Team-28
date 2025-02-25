package exception;

public class InvalidSelectionException extends GameException{
    InvalidSelectionException(){
        super();
    }
    InvalidSelectionException(String message){
        super(message);
    }
}
