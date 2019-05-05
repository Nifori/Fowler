package Exceptions;

public class InvalidPriceCodeException extends RuntimeException {

    public InvalidPriceCodeException() {
        super("Invalid Price Code");
    }

}
