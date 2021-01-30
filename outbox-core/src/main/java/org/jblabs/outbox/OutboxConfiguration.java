package org.jblabs.outbox;

import org.jblabs.outbox.OutboxMessageRepository;
import org.jblabs.outbox.api.Outbox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
public class OutboxConfiguration {

    @Bean
    public Outbox outbox(OutboxMessageRepository outboxMessageRepository) {
        return new Outbox(outboxMessageRepository);
    }
}
