package org.jblabs.outbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherScheduler {
    OutboxMessageService outboxMessageService;

    public MessagePublisherScheduler(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }

    @Scheduled(fixedDelay = 1000)
    public void publishMessages() {
        outboxMessageService.publishMessages();
    }
}
