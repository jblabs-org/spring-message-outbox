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

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMessage(OutboxMessage outboxMessage) {
        outboxMessageRepository.saveMessage(outboxMessage);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMessages(List<OutboxMessage> outboxMessages) {
        outboxMessageRepository.saveMessages(outboxMessages);
    }

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
