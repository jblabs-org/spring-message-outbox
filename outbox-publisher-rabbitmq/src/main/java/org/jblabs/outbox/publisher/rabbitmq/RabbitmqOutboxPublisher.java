package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqOutboxPublisher implements OutboxMessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqPublisherProperties rabbitmqPublisherProperties;

    private final MessagePayloadSerializer messageMessagePayloadSerializer;

    public RabbitmqOutboxPublisher(RabbitTemplate rabbitTemplate, RabbitmqPublisherProperties rabbitmqPublisherProperties,
                                   MessagePayloadSerializer messagePayloadSerializer) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitmqPublisherProperties = rabbitmqPublisherProperties;
        this.messageMessagePayloadSerializer = messagePayloadSerializer;
    }

    @Override
    public void publish(OutboxMessage outboxMessage) throws MessagePublishingException {
        rabbitTemplate.convertAndSend(rabbitmqPublisherProperties.getExchangeName(),
                outboxMessage.getAggregateName(), messageMessagePayloadSerializer.serialize(outboxMessage));
    }
}
