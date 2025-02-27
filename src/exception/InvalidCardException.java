package exception;

abstract public class InvalidCardException extends InvalidSelectionException{
    InvalidCardException(){
        super();
    }
    InvalidCardException(String message){
        super(message);
    }
}
