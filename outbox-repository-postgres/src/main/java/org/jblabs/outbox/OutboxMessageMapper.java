package org.jblabs.outbox;

import org.springframework.stereotype.Component;

@Component
public class OutboxMessageMapper {
    public DBOutboxMessage toDB(OutboxMessage outboxMessage) {
        return new DBOutboxMessage(outboxMessage.getMessageId(), outboxMessage.getAggregateName(),
                outboxMessage.getAggregateId(), outboxMessage.getDestination(), outboxMessage.getPayload());
    }
}
