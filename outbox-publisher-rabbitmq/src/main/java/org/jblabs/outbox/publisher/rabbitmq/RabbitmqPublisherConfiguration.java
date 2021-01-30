package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.OutboxMessageJsonSerializer;
import org.jblabs.outbox.OutboxMessageSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitmqPublisherConfiguration {

    @ConditionalOnMissingBean(OutboxMessageSerializer.class)
    @Bean
    public OutboxMessageSerializer defaultMessageSerializer() {
        return new OutboxMessageJsonSerializer();
    }
}
