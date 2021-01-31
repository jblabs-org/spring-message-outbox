package org.jblabs.outbox.core.message;

public class MessageCreationException extends RuntimeException {
    public MessageCreationException(String message) {
        super(message);
    }
}
