package ir.maktab.exceptions;

public class DuplicateSubService extends RuntimeException{
    public DuplicateSubService() {
        super("this subService already exists");
    }
}
