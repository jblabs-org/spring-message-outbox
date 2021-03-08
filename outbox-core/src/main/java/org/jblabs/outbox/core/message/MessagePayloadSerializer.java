package org.jblabs.outbox.core.message;

/**
 * Serializes an Object to be used as an {@link OutboxMessage} payload.
 */
public interface MessagePayloadSerializer {

    /**
     * Serializes the payload for use as an {@link OutboxMessage} payload.
     * @param payload object to use as an OutboxMessage payload
     * @return Serialized payload
     */
    String serialize(Object payload);
}
