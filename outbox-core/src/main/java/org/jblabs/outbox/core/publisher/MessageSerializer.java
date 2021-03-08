package org.jblabs.outbox.core.publisher;

import org.jblabs.outbox.core.message.OutboxMessage;

/**
 * Serializes a message for publishing.
 */
public interface MessageSerializer {
    /**
     * Serialize an {@link OutboxMessage}
     * @param message message to serialize
     * @return serialized message
     */
    String serialize(OutboxMessage message);
}
