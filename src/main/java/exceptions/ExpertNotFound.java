package exceptions;

public class ExpertNotFound extends RuntimeException{
    public ExpertNotFound() {
        super("expert not found");
    }
}
