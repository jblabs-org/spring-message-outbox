package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqOutboxPublisher implements OutboxMessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final MessagePayloadSerializer messageMessagePayloadSerializer;
    private final ExchangeNameExtractor exchangeNameExtractor;

    public RabbitmqOutboxPublisher(RabbitTemplate rabbitTemplate, MessagePayloadSerializer messageMessagePayloadSerializer,
                                   ExchangeNameExtractor exchangeNameExtractor) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageMessagePayloadSerializer = messageMessagePayloadSerializer;
        this.exchangeNameExtractor = exchangeNameExtractor;
    }

    @Override
    public void publish(OutboxMessage outboxMessage) throws MessagePublishingException {
        rabbitTemplate.convertAndSend(exchangeNameExtractor.getExchangeName(outboxMessage),
                outboxMessage.getAggregateName(), messageMessagePayloadSerializer.serialize(outboxMessage));
    }
}
