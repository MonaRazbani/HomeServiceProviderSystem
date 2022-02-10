package ir.maktab.exceptions;

public class PaymentFail extends RuntimeException{
    public PaymentFail() {
        super("Payment Fail");
    }
}
