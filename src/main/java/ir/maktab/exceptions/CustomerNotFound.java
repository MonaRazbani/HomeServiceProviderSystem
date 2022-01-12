package ir.maktab.exceptions;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound() {
        super("customer not found");
    }
}
