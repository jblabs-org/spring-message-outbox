package org.jblabs.outbox.storage.postgres;

import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class PostgresOutboxRepository implements OutboxMessageRepository {
    private static final String INSERT_SQL = "insert into %s (message_id, message_type, aggregate_name, aggregate_id, " +
            "destination, payload, created_at, is_published) " +
            "values %s";
    private static final String SELECT_SQL = "select * from %s where is_published = false " +
            "order by created_at desc limit %d for update skip locked";
    private static final String MARK_PROCESSED_SQL = "update %s set is_published = true where message_id in (%s)";

    private final PostgresOutboxProperties postgresOutboxProperties;
    private final JdbcTemplate jdbcTemplate;
    private final OutboxMessageMapper outboxMessageMapper;


    public PostgresOutboxRepository(PostgresOutboxProperties postgresOutboxProperties, JdbcTemplate jdbcTemplate, OutboxMessageMapper outboxMessageMapper) {
        this.postgresOutboxProperties = postgresOutboxProperties;
        this.jdbcTemplate = jdbcTemplate;
        this.outboxMessageMapper = outboxMessageMapper;
    }

    @Override
    public void saveMessage(OutboxMessage outboxMessage) {
        jdbcTemplate.execute(String.format(INSERT_SQL, postgresOutboxProperties.getTableName(),
                outboxMessageMapper.toInsertString(outboxMessage)));
    }

    @Override
    public void saveMessages(List<OutboxMessage> outboxMessages) {
        List<String> insertValueList = outboxMessages.stream()
                .map(outboxMessageMapper::toInsertString)
                .collect(Collectors.toList());
        String insertValues = String.join(",", insertValueList);
        jdbcTemplate.execute(String.format(INSERT_SQL, postgresOutboxProperties.getTableName(), insertValues));
    }

    @Override
    public List<OutboxMessage> getMessages(int numberOfMessages) {
        return this.getDBOutboxMessages(numberOfMessages).stream()
                .map(outboxMessageMapper::fromDB)
                .collect(Collectors.toList());
    }

    private List<DBOutboxMessage> getDBOutboxMessages(int numberOfMessages) {
        return jdbcTemplate.query(String.format(SELECT_SQL, postgresOutboxProperties.getTableName(), numberOfMessages),
               new BeanPropertyRowMapper<>(DBOutboxMessage.class));
    }

    @Override
    public void markAsPublished(List<String> messageIds) {
        jdbcTemplate.execute(String.format(MARK_PROCESSED_SQL, postgresOutboxProperties.getTableName(),
                getSqlStringList(messageIds)));
    }

    private String getSqlStringList(List<String> items) {
        return items.stream()
                .map(item -> "'" + item + "'")
                .collect(Collectors.joining(","));
    }
}
