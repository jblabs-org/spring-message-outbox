package org.jblabs.outbox.core.publisher;

import org.jblabs.outbox.core.message.OutboxMessage;

public interface OutboxMessagePublisher {
    void publish(OutboxMessage outboxMessage) throws MessagePublishingException;
}
