package org.jblabs.outbox.core.message;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@ToString
public class OutboxMessage {
    private final String messageId;
    private final String aggregateName;
    private final String aggregateId;
    private final String destination;
    private final String payload;
    private final OffsetDateTime createdAt;
    private boolean isPublished = false;

    /**
     * Constructor to create an OutboxMessage with a String payload and an auto-generated messageId.  For internal
     * use only.
     */
    OutboxMessage(String aggregateName, String aggregateId, String destination, String payload) {
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
     * Used to rehydrate an instance of this class from a persistence source such as a database.  For internal use only.
     */
    public static OutboxMessage rehydrate(String messageId, String aggregateName, String aggregateId, String destination,
                                          String payload, OffsetDateTime createdAt, boolean isPublished) {
        return new OutboxMessage(messageId, aggregateName, aggregateId, destination, payload, createdAt, isPublished);
    }

    /**
     * For internal use only
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
}
