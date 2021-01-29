package org.jblabs.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OutboxMessageJsonSerializer implements OutboxMessageSerializer {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public byte[] serialize(OutboxMessage message) {
        try {
            return objectMapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            throw new SerializationFailedException(e);
        }
    }
}
