package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.JsonPayloadSerializer;
import org.jblabs.outbox.core.message.PayloadSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitmqPublisherConfiguration {

    @ConditionalOnMissingBean(PayloadSerializer.class)
    @Bean
    public PayloadSerializer defaultMessageSerializer() {
        return new JsonPayloadSerializer();
    }
}
