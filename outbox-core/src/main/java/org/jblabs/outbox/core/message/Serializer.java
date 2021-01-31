package org.jblabs.outbox.core.message;

public interface Serializer {
    String serialize(Object payload);
}
