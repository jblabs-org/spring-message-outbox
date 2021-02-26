package org.jblabs.outbox.storage.postgres;

import org.jblabs.outbox.core.message.OutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class OutboxMessageMapper {
    public OutboxMessage fromDB(DBOutboxMessage dbOutboxMessage) {
        return OutboxMessage.rehydrate(dbOutboxMessage.getMessageId(), dbOutboxMessage.getAggregateName(),
                dbOutboxMessage.getAggregateId(), dbOutboxMessage.getDestination(), dbOutboxMessage.getPayload(),
                dbOutboxMessage.getCreatedAt(), dbOutboxMessage.isPublished());
    }

    public String toInsertString(OutboxMessage outboxMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("(") ;
        sb.append("'").append(outboxMessage.getMessageId()).append("'").append(", ");
        sb.append("'").append(outboxMessage.getAggregateName()).append("'").append(", ");
        sb.append("'").append(outboxMessage.getAggregateId()).append("'").append(", ");
        sb.append("'").append(outboxMessage.getDestination()).append("'").append(", ");
        sb.append("'").append(outboxMessage.getPayload()).append("'").append(", ");
        sb.append("'").append(outboxMessage.getCreatedAt()).append("'").append(", ");
        sb.append("'").append(outboxMessage.isPublished()).append("'");
        sb.append(")");
        return sb.toString();
    }
}
