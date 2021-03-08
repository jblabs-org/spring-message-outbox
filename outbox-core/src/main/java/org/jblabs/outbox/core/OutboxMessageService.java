package org.jblabs.outbox.core;

import lombok.extern.slf4j.Slf4j;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service used to interface with the outbox for saving and querying messages.
 */
@Slf4j
@Component
public class OutboxMessageService {

    final OutboxMessageRepository outboxMessageRepository;
    final OutboxMessagePublisher outboxMessagePublisher;
    final OutboxProperties outboxProperties;

    public OutboxMessageService(OutboxMessageRepository outboxMessageRepository, OutboxMessagePublisher outboxMessagePublisher,
                                OutboxProperties outboxProperties) {
        this.outboxMessageRepository = outboxMessageRepository;
        this.outboxMessagePublisher = outboxMessagePublisher;
        this.outboxProperties = outboxProperties;
    }

    /**
     * Save a single message to the outbox in an existing transaction.  An open transaction must exist before this
     * method is called.
     * @param outboxMessage the message to save to the outbox.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMessage(OutboxMessage outboxMessage) {
        outboxMessageRepository.saveMessage(outboxMessage);
    }

    /**
     * Save multiple message to the outbox in an existing transaction.  An open transaction must exist before this
     * method is called.
     * @param outboxMessages the message to save to the outbox.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMessages(List<OutboxMessage> outboxMessages) {
        outboxMessageRepository.saveMessages(outboxMessages);
    }

    /**
     * Read a batch of messages from the outbox and publish them, then mark them as published if successful.
     */
    @Transactional
    public void publishMessages() {
        log.debug("Polling outbox for messages to publish");
        List<OutboxMessage> outboxMessages = outboxMessageRepository.getMessages(outboxProperties.getBatchSize());
        List<String> successfullyPublishedIds = new ArrayList<>();
        for (OutboxMessage outboxMessage : outboxMessages) {
            try {
                outboxMessagePublisher.publish(outboxMessage);
                successfullyPublishedIds.add(outboxMessage.getMessageId());
            } catch (MessagePublishingException e) {
                log.info("Failed to publish message with message id " + outboxMessage.getMessageId());
                //publish failed; don't mark id as published
            }
        }
        if (!successfullyPublishedIds.isEmpty()) {
            log.debug(String.format("Marking %d messages as successfully published", successfullyPublishedIds.size()));
            outboxMessageRepository.markAsPublished(successfullyPublishedIds);
        }
    }
}
