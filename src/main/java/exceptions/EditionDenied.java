package exceptions;

public class EditionDenied extends RuntimeException{
    public EditionDenied() {
        super("edition denied ");
    }
}
