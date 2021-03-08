package org.jblabs.outbox.core.message;

/**
 * Indicates an error occurred while attempting to create an {@link OutboxMessage}
 */
public class MessageCreationException extends RuntimeException {
    public MessageCreationException(String message) {
        super(message);
    }
}
