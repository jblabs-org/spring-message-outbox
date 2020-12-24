package org.jblabs.outbox;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DBOutboxMessage {
    private String messageId;
    private String aggregateName;
    private String aggregateId;
    private String destination;
    private String payload;
}
