package exceptions;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail() {
        super("invalid email");
    }
}
