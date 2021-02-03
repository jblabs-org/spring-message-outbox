package org.jblabs.outbox.core.publisher;

public interface MessageSerializer {
    String serialize(Object message);
}
