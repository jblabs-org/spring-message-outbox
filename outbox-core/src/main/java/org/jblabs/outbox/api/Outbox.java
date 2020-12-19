package org.jblabs.outbox.api;

import org.jblabs.outbox.OutboxMessage;
import org.jblabs.outbox.OutboxMessageRepository;

import java.util.List;

public class Outbox {
    OutboxMessageRepository outboxMessageRepository;

    public Outbox(OutboxMessageRepository outboxMessageRepository) {
        this.outboxMessageRepository = outboxMessageRepository;
    }

    public void publish(OutboxMessage message) {
        outboxMessageRepository.saveMessage(message);
    }

    public void publish(List<OutboxMessage> messages) {
        outboxMessageRepository.saveMessages(messages);
    }
}
