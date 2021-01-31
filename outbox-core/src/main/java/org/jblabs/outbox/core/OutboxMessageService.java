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
    private static final int NUM_MESSAGES_TO_POLL = 100;

    OutboxMessageRepository outboxMessageRepository;
    OutboxMessagePublisher outboxMessagePublisher;

    public OutboxMessageService(OutboxMessageRepository outboxMessageRepository, OutboxMessagePublisher outboxMessagePublisher) {
        this.outboxMessageRepository = outboxMessageRepository;
        this.outboxMessagePublisher = outboxMessagePublisher;
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
        log.debug("Publishing outbox messages");
        List<OutboxMessage> outboxMessages = outboxMessageRepository.getMessages(NUM_MESSAGES_TO_POLL);
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
