package ir.maktab.exceptions;

public class WrongPassword extends RuntimeException{
    public WrongPassword() {
        super("wrong Password");
    }
}
