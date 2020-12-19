package org.jblabs.outbox.storage.postgres;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Create your own PostgresOutboxRepository in a config and inject a jdbcTemplate if you don't want to use the default
 * or you have more than one jdbcTemplate bean.
 */
@Configuration
@ConditionalOnMissingBean(PostgresOutboxRepository.class)
public class PostgresOutboxConfiguration {

    @Bean
    PostgresOutboxRepository postgresOutboxRepository(JdbcTemplate jdbcTemplate) {
        return new PostgresOutboxRepository(jdbcTemplate);
    }
}
