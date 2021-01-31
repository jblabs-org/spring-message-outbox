package org.jblabs.outbox.core.message;

public interface PayloadSerializer {
    String serialize(Object payload);
}
