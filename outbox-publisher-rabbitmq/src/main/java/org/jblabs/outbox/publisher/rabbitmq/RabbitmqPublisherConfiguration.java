package org.jblabs.outbox.publisher.rabbitmq;

import org.jblabs.outbox.core.publisher.JsonMessageSerializer;
import org.jblabs.outbox.core.publisher.MessageSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitmqPublisherConfiguration {

    @ConditionalOnMissingBean(MessageSerializer.class)
    @Bean
    public MessageSerializer defaultMessageSerializer() {
        return new JsonMessageSerializer();
    }

    @ConditionalOnMissingBean(ExchangeNameExtractor.class)
    @Bean
    public ExchangeNameExtractor defaultExchangeNameExtractor() {
        return new DestinationExchangeNameExtractor();
    }
}
