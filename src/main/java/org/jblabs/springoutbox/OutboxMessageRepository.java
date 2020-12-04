package org.jblabs.springoutbox;

import java.util.List;

public interface OutboxMessageRepository {
    void saveMessage(OutboxMessage outboxMessage);
    List<OutboxMessage> getMessages(int numberOfMessages);
    void markAsPublished(List<String> messageIds);
}
