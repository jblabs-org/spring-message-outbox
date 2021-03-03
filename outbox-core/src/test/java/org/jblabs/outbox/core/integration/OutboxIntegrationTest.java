package org.jblabs.outbox.core.integration;

import org.jblabs.outbox.core.Outbox;
import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.message.OutboxMessageFactory;
import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OutboxIntegrationTest {

    @Autowired
    Outbox outbox;

    @Autowired
    OutboxMessageFactory outboxMessageFactory;

    @MockBean
    OutboxMessagePublisher outboxMessagePublisher;

    @MockBean
    OutboxMessageRepository outboxMessageRepository;


    @Test
    void publishMessage() throws MessagePublishingException {
        OutboxMessage outboxMessage = outboxMessageFactory.withStringPayload("testAggregateName", "testAggregateId",
                "testDest", "testPayload");
        when(outboxMessageRepository.getMessages(anyInt())).thenReturn(Arrays.asList(outboxMessage));
        outbox.publish(outboxMessage);

        verify(outboxMessagePublisher, timeout(2000).atLeast(1)).publish(outboxMessage);
        verify(outboxMessageRepository, timeout(2000).atLeast(1)).saveMessage(outboxMessage);
        verify(outboxMessageRepository, timeout(2000).atLeast(1)).markAsPublished(Arrays.asList(outboxMessage.getMessageId()));
    }
}
