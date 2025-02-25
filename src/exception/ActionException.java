package exception;

public abstract class ActionException extends GameException{
    ActionException(){
        super();
    }
    ActionException(String message){
        super(message);
    }
}
