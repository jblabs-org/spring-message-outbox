package org.jblabs.outbox;

public class MessageCreationException extends RuntimeException {
    public MessageCreationException(String message) {
        super(message);
    }
}
