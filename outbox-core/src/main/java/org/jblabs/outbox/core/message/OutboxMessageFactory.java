package org.jblabs.outbox.core.message;

import org.springframework.stereotype.Component;

@Component
public class OutboxMessageFactory {
    private PayloadSerializer payloadSerializer;

    public OutboxMessageFactory(PayloadSerializer payloadSerializer) {
        this.payloadSerializer = payloadSerializer;
    }

    public OutboxMessage withStringPayload(String aggregateName, String aggregateId, String destination, String payload) {
        return new OutboxMessage(aggregateName, aggregateId, destination, payload);
    }

    public OutboxMessage withObjectPayload(String aggregateName, String aggregateId, String destination,
                                               Object payload) {
        String serializedPayload = payloadSerializer.serialize(payload);
        return new OutboxMessage(aggregateName, aggregateId, destination, serializedPayload);
    }
}
