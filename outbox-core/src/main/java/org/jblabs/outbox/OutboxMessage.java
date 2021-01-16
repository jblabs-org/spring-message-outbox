package org.jblabs.outbox;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@ToString
public class OutboxMessage {
    private String messageId;
    private String aggregateName;
    private String aggregateId;
    private String destination;
    private String payload;
    private OffsetDateTime createdAt;
    private boolean isPublished = false;

    public OutboxMessage(String aggregateName, String aggregateId, String destination, String payload) {
        if (!StringUtils.hasText(destination)) {
            throw new MessageCreationException("Destination cannot be empty");
        }

        this.messageId = UUID.randomUUID().toString();
        this.aggregateName = aggregateName == null ? "" : aggregateName;
        this.aggregateId = aggregateId == null ? "" : aggregateId;
        this.destination = destination;
        this.payload = payload;
        this.createdAt = OffsetDateTime.now();
    }

    /**
     * Used internally for rehydration from persistence
     */
    private OutboxMessage(String messageId, String aggregateName, String aggregateId, String destination, String payload,
                         OffsetDateTime createdAt, boolean isPublished) {
        this.messageId = messageId;
        this.aggregateName = aggregateName;
        this.aggregateId = aggregateId;
        this.destination = destination;
        this.payload = payload;
        this.createdAt = createdAt;
        this.isPublished = isPublished;
    }

    public static OutboxMessage rehydrate(String messageId, String aggregateName, String aggregateId, String destination,
                                          String payload, OffsetDateTime createdAt, boolean isPublished) {
        return new OutboxMessage(messageId, aggregateName, aggregateId, destination, payload, createdAt, isPublished);
    }
}
