package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.MessagePublishingException;
import org.jblabs.outbox.OutboxMessage;
import org.jblabs.outbox.OutboxMessagePublisher;
import org.jblabs.outbox.OutboxMessageSerializer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqOutboxPublisher implements OutboxMessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqPublisherProperties rabbitmqPublisherProperties;

    private final OutboxMessageSerializer messageSerializer;

    public RabbitmqOutboxPublisher(RabbitTemplate rabbitTemplate, RabbitmqPublisherProperties rabbitmqPublisherProperties,
                                   OutboxMessageSerializer outboxMessageSerializer) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitmqPublisherProperties = rabbitmqPublisherProperties;
        this.messageSerializer = outboxMessageSerializer;
    }

    @Override
    public void publish(OutboxMessage outboxMessage) throws MessagePublishingException {
        rabbitTemplate.convertAndSend(rabbitmqPublisherProperties.getExchangeName(),
                outboxMessage.getAggregateName(), messageSerializer.serialize(outboxMessage));
    }
}
