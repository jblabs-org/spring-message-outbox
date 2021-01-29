package org.jblabs.outbox.storage.postgres;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Create your own PostgresOutboxRepository in a config and inject a jdbcTemplate if you don't want to use the default
 * or you have more than one jdbcTemplate bean.
 */
@Configuration
@ComponentScan
public class PostgresOutboxConfiguration {

    @Bean
    @ConditionalOnMissingBean(PostgresOutboxRepository.class)
    PostgresOutboxRepository postgresOutboxRepository(JdbcTemplate jdbcTemplate, OutboxMessageMapper outboxMessageMapper,
                                                      PostgresOutboxProperties postgresOutboxProperties) {
        return new PostgresOutboxRepository(postgresOutboxProperties, jdbcTemplate, outboxMessageMapper);
    }
}
