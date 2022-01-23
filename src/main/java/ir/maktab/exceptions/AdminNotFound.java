package ir.maktab.exceptions;

public class AdminNotFound extends RuntimeException{
    public AdminNotFound() {
        super("Admin Not Found");
    }
}
