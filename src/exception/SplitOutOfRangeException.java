package exception;

public class SplitOutOfRangeException extends GameException{
    SplitOutOfRangeException(){
        super();
    }
    SplitOutOfRangeException(String message){
        super(message);
    }
}
