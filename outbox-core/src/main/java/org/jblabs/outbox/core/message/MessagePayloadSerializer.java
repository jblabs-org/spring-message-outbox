package org.jblabs.outbox.core.message;

public interface MessagePayloadSerializer {
    String serialize(Object payload);
}
