package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.OutboxMessage;

public class AggregateNameExchangeNameExtractor implements ExchangeNameExtractor {
    public String getExchangeName(OutboxMessage outboxMessage) {
        return outboxMessage.getAggregateName();
    }
}
