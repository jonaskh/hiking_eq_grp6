package no.ntnu.hikingstore_6.exceptions;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
