package ir.maktab.exceptions;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound() {
        super("no order found ");
    }
}
