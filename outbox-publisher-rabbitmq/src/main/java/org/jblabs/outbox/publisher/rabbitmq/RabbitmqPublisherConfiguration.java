package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.message.JsonSerializer;
import org.jblabs.outbox.core.message.Serializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitmqPublisherConfiguration {

    @ConditionalOnMissingBean(Serializer.class)
    @Bean
    public Serializer defaultMessageSerializer() {
        return new JsonSerializer();
    }
}
