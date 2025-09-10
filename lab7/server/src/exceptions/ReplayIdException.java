package exceptions;

public class ReplayIdException extends Exception{
    public ReplayIdException(int id){
        super("Id: "+ id + " is already used");
    }
}
