package org.jblabs.outbox.core.message;

import org.jblabs.outbox.core.JsonSerializer;

/**
 * Implementation of {@link MessagePayloadSerializer} using a Jackson ObjectMapper to serialize to Json.
 */
public class JsonMessagePayloadSerializer extends JsonSerializer implements MessagePayloadSerializer {
    @Override
    public String serialize(Object payload) {
        return super.serialize(payload);
    }
}
