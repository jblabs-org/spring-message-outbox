package org.jblabs.outbox;

import lombok.Getter;

@Getter
public class OutboxMessage {
    private String id;
    private String destination;
    private String payload;
}
