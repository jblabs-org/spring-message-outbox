package org.jblabs.outbox.storage.postgres;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "outbox.storage")
@Configuration
@Getter
@Setter
public class PostgresOutboxProperties {
    private String tableName = "message_outbox";
}
