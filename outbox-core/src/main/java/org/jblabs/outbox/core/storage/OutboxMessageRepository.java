package org.jblabs.outbox.core.storage;

import org.jblabs.outbox.core.message.OutboxMessage;

import java.util.List;

public interface OutboxMessageRepository {
    void saveMessage(OutboxMessage outboxMessage);
    void saveMessages(List<OutboxMessage> outboxMessages);
    List<OutboxMessage> getMessages(int numberOfMessages);
    void markAsPublished(List<String> messageIds);
}
