package org.jblabs.outbox.core.storage;

import org.jblabs.outbox.core.message.OutboxMessage;

import java.util.List;

/**
 * Interface containing all methods that operate on the Outbox storage.
 */
public interface OutboxMessageRepository {
    /**
     * Save an {@link OutboxMessage} to the message storage
     * @param outboxMessage message to save
     */
    void saveMessage(OutboxMessage outboxMessage);

    /**
     * Save multiple {@link OutboxMessage} objects to the message storage
     * @param outboxMessages messages to save
     */
    void saveMessages(List<OutboxMessage> outboxMessages);

    /**
     * Get a list of {@link OutboxMessage OutboxMessages} from storage that are ready for publishing.
     * @param numberOfMessages number of message to get from storage
     * @return messages fetched from storage
     */
    List<OutboxMessage> getMessages(int numberOfMessages);

    /**
     * Mark multiple messages as published in message storage
     * @param messageIds ids of the messages to mark as published
     */
    void markAsPublished(List<String> messageIds);
}
