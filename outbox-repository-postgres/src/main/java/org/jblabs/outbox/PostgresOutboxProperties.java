package org.jblabs.outbox;

import org.springframework.context.annotation.PropertySource;

@PropertySource("outbox.storage.postgres")
public class PostgresOutboxProperties {
    private String tableName;
}
