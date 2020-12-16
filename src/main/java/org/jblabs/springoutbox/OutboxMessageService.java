package org.jblabs.springoutbox;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        List<OutboxMessage> outboxMessages = outboxMessageRepository.getMessages(NUM_MESSAGES_TO_POLL);
        List<String> successfullyPublishedIds = new ArrayList<>();
        for (OutboxMessage outboxMessage : outboxMessages) {
            try {
                outboxMessagePublisher.publish(outboxMessage);
                successfullyPublishedIds.add(outboxMessage.getId());
            } catch (MessagePublishingException e) {
                //publish failed; don't mark id as published
            }
        }
        if (!successfullyPublishedIds.isEmpty()) {
            outboxMessageRepository.markAsPublished(successfullyPublishedIds);
        }
    }
}
