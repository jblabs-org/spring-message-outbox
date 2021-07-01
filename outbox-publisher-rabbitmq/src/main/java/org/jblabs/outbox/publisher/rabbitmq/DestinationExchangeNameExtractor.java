package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.OutboxMessage;

public class DestinationExchangeNameExtractor implements ExchangeNameExtractor {
    public String getExchangeName(OutboxMessage outboxMessage) {
        return outboxMessage.getDestination();
    }
}
