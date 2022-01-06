package exceptions;

public class InvalidTime extends RuntimeException {
    public InvalidTime() {
        super("invalid time, it must be like 12:00");
    }
}
