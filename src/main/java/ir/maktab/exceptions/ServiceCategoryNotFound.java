package ir.maktab.exceptions;

public class ServiceCategoryNotFound extends RuntimeException{
    public ServiceCategoryNotFound() {
        super("serviceCategory not found");
    }
}
