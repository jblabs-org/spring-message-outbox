package org.jblabs.outbox.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jblabs.outbox.core.message.MessagePayloadSerializer;
import org.jblabs.outbox.core.message.SerializationFailedException;
import org.jblabs.outbox.core.publisher.MessageSerializer;

public class JsonSerializer implements MessageSerializer, MessagePayloadSerializer {
    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public String serialize(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new SerializationFailedException(e);
        }
    }
}
