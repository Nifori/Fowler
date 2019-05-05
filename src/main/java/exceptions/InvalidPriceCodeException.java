package exceptions;

public class InvalidPriceCodeException extends RuntimeException {

    public InvalidPriceCodeException() {
        super("Invalid Price Code");
    }

}
