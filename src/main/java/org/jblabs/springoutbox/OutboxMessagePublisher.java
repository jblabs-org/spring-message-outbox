package org.jblabs.springoutbox;

public interface OutboxMessagePublisher {
    void publish(OutboxMessage outboxMessage);
}
