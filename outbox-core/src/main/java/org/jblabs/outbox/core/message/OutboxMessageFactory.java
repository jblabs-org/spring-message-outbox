package org.jblabs.outbox.core.message;

import org.springframework.stereotype.Component;

@Component
public class OutboxMessageFactory {
    private final MessagePayloadSerializer messagePayloadSerializer;

    public OutboxMessageFactory(MessagePayloadSerializer messagePayloadSerializer) {
        this.messagePayloadSerializer = messagePayloadSerializer;
    }

    public OutboxMessage withStringPayload(String messageType, String aggregateName, String aggregateId, String destination, String payload) {
        return new OutboxMessage(messageType, aggregateName, aggregateId, destination, payload);
    }

    public OutboxMessage withObjectPayload(String messageType, String aggregateName, String aggregateId, String destination,
                                               Object payload) {
        String serializedPayload = messagePayloadSerializer.serialize(payload);
        return new OutboxMessage(messageType, aggregateName, aggregateId, destination, serializedPayload);
    }
}
