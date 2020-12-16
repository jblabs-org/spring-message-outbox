package org.jblabs.springoutbox;

import org.springframework.scheduling.annotation.Scheduled;

public class MessagePublisherScheduler {
    OutboxMessageService outboxMessageService;

    @Scheduled(fixedDelay = 1000)
    public void publishMessages() {
        outboxMessageService.publishMessages();
    }
}
