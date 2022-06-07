package no.ntnu.hikingstore_6.exceptions;

public class OrderAlreadyCanceledException extends Throwable{
    public OrderAlreadyCanceledException(String message) {
        super(message);
    }
}