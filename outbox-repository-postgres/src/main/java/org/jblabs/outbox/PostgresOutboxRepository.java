package org.jblabs.outbox;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostgresOutboxRepository implements OutboxMessageRepository {
    private static final String INSERT_SQL = "insert into %s (message_id, aggregate_name, aggregate_id, destination, " +
            "payload, created_at, is_published) " +
            "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";

    private PostgresOutboxProperties postgresOutboxProperties;
    private JdbcTemplate jdbcTemplate;
    private OutboxMessageMapper outboxMessageMapper;


    public PostgresOutboxRepository(PostgresOutboxProperties postgresOutboxProperties, JdbcTemplate jdbcTemplate, OutboxMessageMapper outboxMessageMapper) {
        this.postgresOutboxProperties = postgresOutboxProperties;
        this.jdbcTemplate = jdbcTemplate;
        this.outboxMessageMapper = outboxMessageMapper;
    }

    @Override
    public void saveMessage(OutboxMessage outboxMessage) {
        DBOutboxMessage dbOutboxMessage = outboxMessageMapper.toDB(outboxMessage);
        jdbcTemplate.execute(String.format(INSERT_SQL, postgresOutboxProperties.getTableName(), dbOutboxMessage.getMessageId(),
                dbOutboxMessage.getAggregateName(), outboxMessage.getAggregateId(), outboxMessage.getDestination(),
                outboxMessage.getPayload(), outboxMessage.getCreatedAt(), outboxMessage.isPublished()));
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
