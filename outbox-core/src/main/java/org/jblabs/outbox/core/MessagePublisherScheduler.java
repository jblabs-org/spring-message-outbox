package org.jblabs.outbox.core;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler for the message polling mechanism.
 */
@Component
public class MessagePublisherScheduler {
    final OutboxMessageService outboxMessageService;

    public MessagePublisherScheduler(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }

    /**
     * The Spring scheduler calls this method to trigger message polling.
     */
    @Scheduled(fixedDelayString = "${outbox.storage.polling.frequencyInMillis:1000}")
    public void publishMessages() {
        outboxMessageService.publishMessages();
    }
}
