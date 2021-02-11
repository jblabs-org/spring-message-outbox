package org.jblabs.outbox.core;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class JsonSerializerTest {

    JsonSerializer jsonSerializer = new JsonSerializer();
    String serializedObject = "{\"stringField\":\"test string\",\"intField\":123,\"java8TimestampField\":\"2021-02-11T15:06:02.094443-06:00\"}";

    @Test
    void serialize() {
        TestObject testObject = testObject();
        String serializedObject = jsonSerializer.serialize(testObject);
        System.out.println(serializedObject);
        assertThat(serializedObject, is(serializedObject));
    }

    private TestObject testObject() {
        TestObject testObject = new TestObject();
        testObject.stringField = "test string";
        testObject.intField = 123;
        testObject.java8TimestampField = OffsetDateTime.parse("2021-02-11T15:06:02.094443-06:00");
        return testObject;
    }

    private class TestObject {
        public String stringField;
        public Integer intField;
        public OffsetDateTime java8TimestampField;
    }
}