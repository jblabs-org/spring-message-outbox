package org.jblabs.outbox.publisher.logging;

import lombok.extern.slf4j.Slf4j;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.springframework.stereotype.Component;
import org.jblabs.outbox.core.message.OutboxMessage;

@Component
@Slf4j
public class LoggingOutboxPublisher implements OutboxMessagePublisher {

    @Override
    public void publish(OutboxMessage outboxMessage) {
        log.info("Publishing message to log for message id {} : {}", outboxMessage.getMessageId(), outboxMessage);
    }
}
