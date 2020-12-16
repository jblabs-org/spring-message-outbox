package org.jblabs.springoutbox;

import lombok.Getter;

@Getter
public class OutboxMessage {
    private String id;
    private String destination;
    private String payload;
}
