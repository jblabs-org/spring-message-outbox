package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.SerializationFailedException;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonSerializerTest {

    final JsonSerializer jsonSerializer = new JsonSerializer();
    String serializedObject = "{\"stringField\":\"test string\",\"intField\":123,\"java8TimestampField\":\"2021-02-11T15:06:02.094443-06:00\"}";

    @Test
    void serialize() {
        TestObject testObject = testObject();
        String serializedObject = jsonSerializer.serialize(testObject);
        assertThat(serializedObject, is(serializedObject));
    }

    @Test
    void serialize_shouldThrowExceptionOnFailure() {
        WillNotSerialize willNotSerialize = new WillNotSerialize();
        assertThrows(SerializationFailedException.class, () -> jsonSerializer.serialize(willNotSerialize));
    }

    private TestObject testObject() {
        TestObject testObject = new TestObject();
        testObject.stringField = "test string";
        testObject.intField = 123;
        testObject.java8TimestampField = OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00");
        return testObject;
    }

    private static class TestObject {
        public String stringField;
        public Integer intField;
        public OffsetDateTime java8TimestampField;
    }

    private static class WillNotSerialize {
        public WillNotSerialize getSelf() {
            return this;
        }
    }
}