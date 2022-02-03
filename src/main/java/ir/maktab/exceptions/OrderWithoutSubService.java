package ir.maktab.exceptions;

public class OrderWithoutSubService extends RuntimeException{
    public OrderWithoutSubService() {
        super("select subService for your order");
    }
}
