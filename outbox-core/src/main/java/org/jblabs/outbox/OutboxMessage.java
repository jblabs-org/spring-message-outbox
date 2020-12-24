package org.jblabs.outbox;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OutboxMessage {
    private String messageId;
    private String aggregateName;
    private String aggregateId;
    private String destination;
    private String payload;

    public OutboxMessage(String aggregateName, String aggregateId, String destination, String payload) {
        this.messageId = UUID.randomUUID().toString();
        this.aggregateName = aggregateName;
        this.aggregateId = aggregateId;
        this.destination = destination;
        this.payload = payload;
    }
}
