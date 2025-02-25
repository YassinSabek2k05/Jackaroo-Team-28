package exception;

public class IllegalMovementException extends ActionException{
    IllegalMovementException(){
        super();
    }
    IllegalMovementException(String message){
        super(message);
    }
}
