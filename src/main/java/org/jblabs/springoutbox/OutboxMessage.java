package org.jblabs.springoutbox;

public class OutboxMessage {
    String id;
    String destination;
    String payload;
}
