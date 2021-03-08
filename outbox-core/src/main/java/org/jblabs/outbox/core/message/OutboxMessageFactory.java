package org.jblabs.outbox.core.message;

import org.springframework.stereotype.Component;

/**
 * Factory class for constructing instances of {@link OutboxMessage}
 */
@Component
public class OutboxMessageFactory {
    private final MessagePayloadSerializer messagePayloadSerializer;

    public OutboxMessageFactory(MessagePayloadSerializer messagePayloadSerializer) {
        this.messagePayloadSerializer = messagePayloadSerializer;
    }

    /**
     * Construct an {@link OutboxMessage} with a String payload
     * @param messageType message type
     * @param aggregateName name of the aggregate
     * @param aggregateId id of the aggregate
     * @param destination destination of the message
     * @param payload message payload
     * @return new OutboxMessage instance
     */
    public OutboxMessage withStringPayload(String messageType, String aggregateName, String aggregateId, String destination, String payload) {
        return new OutboxMessage(messageType, aggregateName, aggregateId, destination, payload);
    }

    /**
     * Construct an {@link OutboxMessage} with an object payload
     * @param messageType message type
     * @param aggregateName name of the aggregate
     * @param aggregateId id of the aggregate
     * @param destination destination of the message
     * @param payload message payload
     * @return new OutboxMessage instance
     */
    public OutboxMessage withObjectPayload(String messageType, String aggregateName, String aggregateId, String destination,
                                               Object payload) {
        String serializedPayload = messagePayloadSerializer.serialize(payload);
        return new OutboxMessage(messageType, aggregateName, aggregateId, destination, serializedPayload);
    }
}
