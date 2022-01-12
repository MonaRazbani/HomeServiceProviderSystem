package ir.maktab.exceptions;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail() {
        super("invalid email");
    }
}
