package ir.maktab.exceptions;

public class AddressNotFound extends RuntimeException{
    public AddressNotFound() {
        super("Address Not Found");
    }
}
