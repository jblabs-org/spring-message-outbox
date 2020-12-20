package org.jblabs.outbox;

import org.jblabs.outbox.OutboxMessage;
import org.jblabs.outbox.OutboxMessageRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostgresOutboxRepository implements OutboxMessageRepository {

    private JdbcTemplate jdbcTemplate;

    public PostgresOutboxRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveMessage(OutboxMessage outboxMessage) {

    }

    @Override
    public void saveMessages(List<OutboxMessage> outboxMessages) {

    }

    @Override
    public List<OutboxMessage> getMessages(int numberOfMessages) {
        return null;
    }

    @Override
    public void markAsPublished(List<String> messageIds) {

    }
}
