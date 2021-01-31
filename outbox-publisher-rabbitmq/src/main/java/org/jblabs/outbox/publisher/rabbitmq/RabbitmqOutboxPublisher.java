package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.jblabs.outbox.core.message.PayloadSerializer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqOutboxPublisher implements OutboxMessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqPublisherProperties rabbitmqPublisherProperties;

    private final PayloadSerializer messageSerializer;

    public RabbitmqOutboxPublisher(RabbitTemplate rabbitTemplate, RabbitmqPublisherProperties rabbitmqPublisherProperties,
                                   PayloadSerializer payloadSerializer) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitmqPublisherProperties = rabbitmqPublisherProperties;
        this.messageSerializer = payloadSerializer;
    }

    @Override
    public void publish(OutboxMessage outboxMessage) throws MessagePublishingException {
        rabbitTemplate.convertAndSend(rabbitmqPublisherProperties.getExchangeName(),
                outboxMessage.getAggregateName(), messageSerializer.serialize(outboxMessage));
    }
}
