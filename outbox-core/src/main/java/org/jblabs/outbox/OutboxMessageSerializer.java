package org.jblabs.outbox;

public interface OutboxMessageSerializer {
    byte[] serialize(OutboxMessage message);
}
