package exception;

public class CannotFieldException extends ActionException{
    CannotFieldException(){
        super();
    }
    CannotFieldException(String message){
        super(message);
    }
}
