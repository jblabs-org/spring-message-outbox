package org.jblabs.outbox;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OutboxMessage {
    private String id;
    private String destination;
    private String payload;
}
