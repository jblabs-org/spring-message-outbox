package org.jblabs.outbox.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jblabs.outbox.core.message.SerializationFailedException;

/**
 * Uses a Jackson ObjectMapper to serialize objects to Json.
 */
public class JsonSerializer {
    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public String serialize(Object objectToSerialize) {
        try {
            return objectMapper.writeValueAsString(objectToSerialize);
        } catch (JsonProcessingException e) {
            throw new SerializationFailedException(e);
        }
    }
}
