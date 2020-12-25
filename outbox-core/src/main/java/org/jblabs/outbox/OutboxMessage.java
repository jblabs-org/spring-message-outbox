package org.jblabs.outbox;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
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
}
