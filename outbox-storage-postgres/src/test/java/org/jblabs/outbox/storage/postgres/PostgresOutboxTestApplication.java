package org.jblabs.outbox.storage.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PostgresOutboxConfiguration.class)
public class PostgresOutboxTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostgresOutboxTestApplication.class, args);
    }

    //TODO remove
//    @Bean
//    public PostgresOutboxProperties postgresOutboxProperties() {
//        return PostgresOutboxProperties.builder()
//                .tableName("outbox")
//                .build();
//    }
}
