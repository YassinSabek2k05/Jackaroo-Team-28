package exception;

public class CannotDiscardException extends ActionException{
    CannotDiscardException(){
        super();
    }
    CannotDiscardException(String message){
        super(message);
    }
}
