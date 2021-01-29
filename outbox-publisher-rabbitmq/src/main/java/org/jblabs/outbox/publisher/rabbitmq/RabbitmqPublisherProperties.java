package org.jblabs.outbox.publisher.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "outbox.publisher.rabbitmq")
@Configuration
@Getter
@Setter
public class RabbitmqPublisherProperties {
    private String exchangeName;
}
