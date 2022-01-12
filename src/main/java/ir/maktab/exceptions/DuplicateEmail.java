package ir.maktab.exceptions;

public class DuplicateEmail extends RuntimeException {
    public DuplicateEmail() {
        super("This email has been used before");
    }
}
