package org.jblabs.outbox;

import org.jblabs.outbox.api.Outbox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboxConfiguration {

    @Bean
    public Outbox outbox(OutboxMessageRepository outboxMessageRepository) {
        return new Outbox(outboxMessageRepository);
    }
}
