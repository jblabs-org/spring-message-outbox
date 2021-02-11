package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("org.jblabs.outbox.core")
@EnableScheduling
public class OutboxConfiguration {

    @Bean
    public Outbox outbox(OutboxMessageRepository outboxMessageRepository) {
        return new Outbox(outboxMessageRepository);
    }

    @Bean
    @ConditionalOnMissingBean(MessagePayloadSerializer.class)
    public MessagePayloadSerializer defaultMessagePayloadSerializer() {
        return new JsonSerializer();
    }
}
