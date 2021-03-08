package org.jblabs.outbox.core.publisher;

import org.jblabs.outbox.core.message.OutboxMessage;

/**
 * All {@link OutboxMessage} publishers implement this interface
 */
public interface OutboxMessagePublisher {
    /**
     * Publish an {@link OutboxMessage}
     * @param outboxMessage message to publish
     * @throws MessagePublishingException if message publishing fails
     */
    void publish(OutboxMessage outboxMessage) throws MessagePublishingException;
}
