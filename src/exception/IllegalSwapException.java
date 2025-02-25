package exception;

public class IllegalSwapException extends ActionException {
    IllegalSwapException(){
        super();
    }
    IllegalSwapException(String message){
        super(message);
    }
}
