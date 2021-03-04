package org.jblabs.outbox.core.message;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class OutboxMessageTest {

    @Test
    void testConstructor() {
        OutboxMessage outboxMessage = new OutboxMessage("testMessageType", "testName", "testId", "testDest", "testPayload");

        assertThat(outboxMessage.getMessageId(), notNullValue());
    }

}