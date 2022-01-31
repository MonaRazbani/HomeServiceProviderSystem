package ir.maktab.exceptions;

public class AccessDenied extends RuntimeException{
    public AccessDenied() {
        super("accessDenied");
    }
}
