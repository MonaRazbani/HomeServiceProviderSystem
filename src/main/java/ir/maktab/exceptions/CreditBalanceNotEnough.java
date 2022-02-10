package ir.maktab.exceptions;

public class CreditBalanceNotEnough extends RuntimeException{
    public CreditBalanceNotEnough() {
        super("Credit balance is not enough");
    }
}
