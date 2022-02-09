package ir.maktab.exceptions;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound() {
        super("Transaction Not Found");
    }
}
