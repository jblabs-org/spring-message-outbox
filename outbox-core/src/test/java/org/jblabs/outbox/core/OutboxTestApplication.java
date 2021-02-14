package org.jblabs.outbox.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OutboxTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(OutboxTestApplication.class, args);
    }

//    @Bean
//    public OutboxMessagePublisher outboxMessagePublisher() {
//        return Mockito.mock(OutboxMessagePublisher.class);
//    }
//
//    @Bean
//    public OutboxMessageRepository outboxMessageRepository() {
//        return Mockito.mock(OutboxMessageRepository.class);
//    }
}
