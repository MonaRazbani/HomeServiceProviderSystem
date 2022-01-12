package ir.maktab.exceptions;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword() {
        super("password must contains words and numbers and password length's  must more than 8 ");
    }
}
