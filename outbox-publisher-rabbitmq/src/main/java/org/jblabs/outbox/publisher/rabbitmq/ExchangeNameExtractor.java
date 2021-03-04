package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.OutboxMessage;

public interface ExchangeNameExtractor {
    String getExchangeName(OutboxMessage outboxMessage);
}
