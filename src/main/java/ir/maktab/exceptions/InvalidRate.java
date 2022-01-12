package ir.maktab.exceptions;

public class InvalidRate extends RuntimeException{
    public InvalidRate() {
        super(" invalid rate , rate must between 5-0");
    }
}
