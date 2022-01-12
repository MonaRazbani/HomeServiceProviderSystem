package ir.maktab.exceptions;

public class NoCategoryServiceForService extends RuntimeException{
    public NoCategoryServiceForService() {
        super("service most have service Category ");
    }
}
