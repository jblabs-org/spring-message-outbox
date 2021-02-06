package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;

import java.util.List;

public class Outbox {
    final OutboxMessageRepository outboxMessageRepository;

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
