package org.jblabs.outbox.storage.postgres;

import org.jblabs.outbox.core.message.OutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class OutboxMessageMapper {
    public OutboxMessage fromDB(DBOutboxMessage dbOutboxMessage) {
        return OutboxMessage.rehydrate(dbOutboxMessage.getMessageId(), dbOutboxMessage.getMessageType(),
                dbOutboxMessage.getAggregateName(), dbOutboxMessage.getAggregateId(), dbOutboxMessage.getDestination(),
                dbOutboxMessage.getPayload(), dbOutboxMessage.getCreatedAt(), dbOutboxMessage.isPublished());
    }

    public String toInsertString(OutboxMessage outboxMessage) {
        return "(" +
                "'" + outboxMessage.getMessageId() + "'" + ", " +
                "'" + outboxMessage.getMessageType() + "'" + ", " +
                "'" + outboxMessage.getAggregateName() + "'" + ", " +
                "'" + outboxMessage.getAggregateId() + "'" + ", " +
                "'" + outboxMessage.getDestination() + "'" + ", " +
                "'" + outboxMessage.getPayload() + "'" + ", " +
                "'" + outboxMessage.getCreatedAt() + "'" + ", " +
                "'" + outboxMessage.isPublished() + "'" +
                ")";
    }
}
