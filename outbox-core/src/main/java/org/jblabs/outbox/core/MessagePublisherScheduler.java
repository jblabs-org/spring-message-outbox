package org.jblabs.outbox.core;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherScheduler {
    OutboxMessageService outboxMessageService;

    public MessagePublisherScheduler(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }

    @Scheduled(fixedDelayString = "${outbox.storage.polling.frequencyInMillis:1000}")
    public void publishMessages() {
        outboxMessageService.publishMessages();
    }
}
