package org.jblabs.outbox.storage.postgres;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
class PostgresOutboxRepositoryTest {

    @Autowired
    PostgresOutboxRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PostgresOutboxProperties postgresOutboxProperties;


    @Test
    @FlywayTest
    @Sql("classpath:db/migration/createOutboxTable.sql")
    void saveMessage() {
        OutboxMessage message = outboxMessage();

        repository.saveMessage(message);

        Integer count = jdbcTemplate.queryForObject("select count(*) from " + postgresOutboxProperties.getTableName(),
                Integer.class);
        assertThat(count, is(1));
    }

    @Test
    @FlywayTest
    @Sql("classpath:db/migration/createOutboxTable.sql")
    void saveMessages() {
        OutboxMessage message1 = outboxMessage();
        OutboxMessage message2 = outboxMessage2();

        repository.saveMessages(Arrays.asList(message1, message2));

        Integer count = jdbcTemplate.queryForObject("select count(*) from " + postgresOutboxProperties.getTableName(),
                Integer.class);
        assertThat(count, is(2));
    }

    @Test
    void getMessages() {
    }

    @Test
    void markAsPublished() {
    }

    private OutboxMessage outboxMessage() {
        return OutboxMessage.rehydrate("123", "testAggName", "456", "testDest", "test payload",
                OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00"), false);
    }

    private OutboxMessage outboxMessage2() {
        return OutboxMessage.rehydrate("1234", "testAggName", "4567", "testDest", "test payload",
                OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00"), false);
    }
}