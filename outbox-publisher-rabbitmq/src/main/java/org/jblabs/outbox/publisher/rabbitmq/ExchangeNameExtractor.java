package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.OutboxMessage;

/**
 * Interface for classes that determine the exchange name to send an OutboxMessage.
 */
public interface ExchangeNameExtractor {

    /**
     * Determines the exchange to send an {@link OutboxMessage}
     * @param outboxMessage message to publish
     * @return name of the exchange to publish the message.
     */
    String getExchangeName(OutboxMessage outboxMessage);
}
