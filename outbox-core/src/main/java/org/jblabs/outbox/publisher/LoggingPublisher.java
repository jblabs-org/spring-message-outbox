package org.jblabs.outbox.publisher;

import lombok.extern.slf4j.Slf4j;
import org.jblabs.outbox.MessagePublishingException;
import org.jblabs.outbox.OutboxMessage;
import org.jblabs.outbox.OutboxMessagePublisher;
import org.springframework.stereotype.Component;

@Component //TODO Move to test package
@Slf4j
public class LoggingPublisher implements OutboxMessagePublisher {

    @Override
    public void publish(OutboxMessage outboxMessage) throws MessagePublishingException {
        log.info("Publishing message to log for message id {} : {}", outboxMessage.getMessageId(), outboxMessage);
    }
}
