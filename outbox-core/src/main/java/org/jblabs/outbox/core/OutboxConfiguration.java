package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.JsonMessagePayloadSerializer;
import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main configuration class for the outbox core module.
 */
@Configuration
@ComponentScan("org.jblabs.outbox.core")
@EnableScheduling
public class OutboxConfiguration {

    /**
     * Create the main Outbox bean.
     * @param outboxMessageService injected outbox message service
     * @return Outbox class
     */
    @Bean
    public Outbox outbox(OutboxMessageService outboxMessageService) {
        return new Outbox(outboxMessageService);
    }

    /**
     * Create a Json message payload serializer if a MessagePayloadSerializer doesn't already exist
     * @return the Json serializer
     */
    @Bean
    @ConditionalOnMissingBean(MessagePayloadSerializer.class)
    public MessagePayloadSerializer defaultMessagePayloadSerializer() {
        return new JsonMessagePayloadSerializer();
    }
}
