package exception;

public class InvalidMarbleException extends GameException{
    InvalidMarbleException(){
        super();
    }
    InvalidMarbleException(String message){
        super(message);
    }
}
