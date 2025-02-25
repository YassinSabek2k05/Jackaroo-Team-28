package exception;

public class IllegalDestroyException extends ActionException {
    IllegalDestroyException(){
        super();
    }
    IllegalDestroyException(String message){
        super(message);
    }
}
