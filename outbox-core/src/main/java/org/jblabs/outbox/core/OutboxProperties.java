package org.jblabs.outbox.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "outbox")
@Configuration
@Getter
@Setter
public class OutboxProperties {
    private Integer storagePollingSize = 100;
}
