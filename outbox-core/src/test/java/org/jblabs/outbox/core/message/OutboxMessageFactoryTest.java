package org.jblabs.outbox.core.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OutboxMessageFactoryTest {
    private OutboxMessageFactory outboxMessageFactory;

    @Mock
    private MessagePayloadSerializer payloadSerializer;

    @BeforeEach
    void setUp() {
        outboxMessageFactory = new OutboxMessageFactory(payloadSerializer);
    }

    @Test
    void withStringPayload() {
        OutboxMessage testMessage = testMessage("testPayload");
        OutboxMessage createdMessage = outboxMessageFactory.withStringPayload(testMessage.getMessageType(),
                testMessage.getAggregateName(), testMessage.getAggregateId(), testMessage.getDestination(),
                testMessage.getPayload());

        assertThat(createdMessage.getMessageId(), notNullValue());
        assertThat(createdMessage.getAggregateId(), is(testMessage.getAggregateId()));
        assertThat(createdMessage.getAggregateName(), is(testMessage.getAggregateName()));
        assertThat(createdMessage.getDestination(), is(testMessage.getDestination()));
        assertThat(createdMessage.getPayload(), is(testMessage.getPayload()));
        assertThat(createdMessage.getCreatedAt(), notNullValue());
        assertThat(createdMessage.isPublished(), is(false));

    }

    @Test
    void withObjectPayload() {
        String serializedTestPayload = "{\"testField\":\"testField\"}";
        Mockito.when(payloadSerializer.serialize(any())).thenReturn(serializedTestPayload);
        TestPayload testPayload = new TestPayload("testField");
        OutboxMessage testMessage = testMessage(null);

        OutboxMessage createdMessage = outboxMessageFactory.withObjectPayload(testMessage.getMessageType(),
                testMessage.getAggregateName(), testMessage.getAggregateId(), testMessage.getDestination(), testPayload);

        assertThat(createdMessage.getMessageId(), notNullValue());
        assertThat(createdMessage.getAggregateId(), is(testMessage.getAggregateId()));
        assertThat(createdMessage.getAggregateName(), is(testMessage.getAggregateName()));
        assertThat(createdMessage.getDestination(), is(testMessage.getDestination()));
        assertThat(createdMessage.getPayload(), is(serializedTestPayload));
        assertThat(createdMessage.getCreatedAt(), notNullValue());
        assertThat(createdMessage.isPublished(), is(false));

    }

    private OutboxMessage testMessage(String payload) {
        return OutboxMessage.rehydrate("123", "testMessageType", "testAggregateName", "456", "testDestination", payload,
                OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00"), false);
    }

    private static class TestPayload {
        public TestPayload(String testField) {
            this.testField = testField;
        }

        public final String testField;
    }
}