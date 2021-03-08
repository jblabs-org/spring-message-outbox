package org.jblabs.outbox.core.publisher;

import org.jblabs.outbox.core.JsonSerializer;
import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.jblabs.outbox.core.message.OutboxMessage;

/**
 * Implementation of {@link MessagePayloadSerializer} using a Jackson ObjectMapper to serialize to Json.
 */
public class JsonMessageSerializer extends JsonSerializer implements MessageSerializer {
    @Override
    public String serialize(OutboxMessage message) {
        return super.serialize(message);
    }
}
