package org.jblabs.outbox.storage.postgres;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @BeforeEach
    void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }


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
    @FlywayTest
    @Sql("classpath:db/migration/createOutboxTable.sql")
    @Sql("classpath:db/migration/insertSingleMessage.sql")
    void getMessages() {
        List<OutboxMessage> messages = repository.getMessages(10);

        assertThat(messages.size(), is(1));
    }

    @Test
    @FlywayTest
    @Sql("classpath:db/migration/createOutboxTable.sql")
    @Sql("classpath:db/migration/insertSingleMessage.sql")
    void getMessages_should_read_single_message_once() throws InterruptedException {
        CompletableFuture.runAsync(() -> asyncGetMessagesAndSleep(1));
        Thread.sleep(500);
        List<OutboxMessage> messages = repository.getMessages(1);
        assertThat(messages.size(), is(0));
    }

    @Test
    @FlywayTest
    @Sql("classpath:db/migration/createOutboxTable.sql")
    @Sql("classpath:db/migration/insert4Messages.sql")
    void getMessages_should_read_multiple_messages_once() throws InterruptedException {
        CompletableFuture.runAsync(() -> asyncGetMessagesAndSleep(2));
        Thread.sleep(500);
        List<OutboxMessage> messages = repository.getMessages(10);
        assertThat(messages.size(), is(2));
    }

    @Test
    void markAsPublished() {
        //TODO implement
        assert(true);
    }

    private OutboxMessage outboxMessage() {
        return OutboxMessage.rehydrate("123", "testAggName", "456", "testDest", "test payload",
                OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00"), false);
    }

    private OutboxMessage outboxMessage2() {
        return OutboxMessage.rehydrate("1234", "testAggName", "4567", "testDest", "test payload",
                OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00"), false);
    }

    private void asyncGetMessagesAndSleep(int numMessages) {
        transactionTemplate.execute(status -> {
            List<OutboxMessage> messages = repository.getMessages(numMessages);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}