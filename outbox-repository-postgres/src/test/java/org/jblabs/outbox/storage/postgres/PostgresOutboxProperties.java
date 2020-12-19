package org.jblabs.outbox.storage.postgres;

import org.springframework.context.annotation.PropertySource;

@PropertySource("outbox.storage.postgres")
public class PostgresOutboxProperties {
    private String tableName;
}
