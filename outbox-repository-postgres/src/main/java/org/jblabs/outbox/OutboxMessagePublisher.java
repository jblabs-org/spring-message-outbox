package org.jblabs.outbox;

public interface OutboxMessagePublisher {
    void publish(OutboxMessage outboxMessage) throws MessagePublishingException;
}
