package ir.maktab.exceptions;

public class BadFilterSearching extends RuntimeException{
    public BadFilterSearching() {
        super("search SubService is only for expert");
    }
}
