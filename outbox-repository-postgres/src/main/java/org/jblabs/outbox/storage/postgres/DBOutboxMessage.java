package org.jblabs.outbox.storage.postgres;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DBOutboxMessage {
    private String messageId;
    private String aggregateName;
    private String aggregateId;
    private String destination;
    private String payload;
    private OffsetDateTime createdAt;
    private boolean isPublished;
}
