package org.jblabs.outbox;

import org.springframework.stereotype.Component;

@Component
public class OutboxMessageMapper {
    public DBOutboxMessage toDB(OutboxMessage outboxMessage) {
        return new DBOutboxMessage(outboxMessage.getMessageId(), outboxMessage.getAggregateName(),
                outboxMessage.getAggregateId(), outboxMessage.getDestination(), outboxMessage.getPayload(),
                outboxMessage.getCreatedAt(), outboxMessage.isPublished());
    }

    public OutboxMessage fromDB(DBOutboxMessage dbOutboxMessage) {
        return OutboxMessage.rehydrate(dbOutboxMessage.getMessageId(), dbOutboxMessage.getAggregateName(),
                dbOutboxMessage.getAggregateId(), dbOutboxMessage.getDestination(), dbOutboxMessage.getPayload(),
                dbOutboxMessage.getCreatedAt(), dbOutboxMessage.isPublished());
    }
}
