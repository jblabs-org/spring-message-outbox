package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.OutboxMessage;

import java.util.List;

/**
 * Public API for the message outbox.
 */
public class Outbox {
    final OutboxMessageService outboxMessageService;

    public Outbox(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }

    /**
     * Publish a single message.  An open transaction must exist before this method is called.  The message will be
     * saved to the outbox in the existing transaction, then published to a publisher asynchronously.
     * @param message the message to be published.
     */
    public void publish(OutboxMessage message) {
        outboxMessageService.saveMessage(message);
    }

    /**
     * Publish multiple messages.  An open transaction must exist before this method is called.  The messages will be
     * saved to the outbox in the existing transaction, then published to a publisher asynchronously.
     * @param messages the messages to be published.
     */
    public void publish(List<OutboxMessage> messages) {
        outboxMessageService.saveMessages(messages);
    }
}
