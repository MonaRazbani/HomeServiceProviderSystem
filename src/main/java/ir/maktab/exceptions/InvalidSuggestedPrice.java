package ir.maktab.exceptions;

public class InvalidSuggestedPrice extends RuntimeException {
    public InvalidSuggestedPrice() {
        super("invalid SuggestedPrice , SuggestedPrice must be more than service base cost " );
    }
}
