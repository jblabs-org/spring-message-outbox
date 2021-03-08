package org.jblabs.outbox.core.message;

/**
 * Indicates a serialization failure
 */
public class SerializationFailedException extends RuntimeException {
    public SerializationFailedException() {
    }

    public SerializationFailedException(String message) {
        super(message);
    }

    public SerializationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationFailedException(Throwable cause) {
        super(cause);
    }

    public SerializationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
