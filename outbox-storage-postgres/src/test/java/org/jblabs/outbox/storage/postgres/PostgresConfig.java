package org.jblabs.outbox.storage.postgres;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@AutoConfigureBefore(DataSource.class)
@Configuration
public class PostgresConfig {


    @Bean
    public void embeddedPostgres() throws IOException {
        System.out.println("********************************************");
        EmbeddedPostgres pg = EmbeddedPostgres.builder()
                .setPort(8765)
                .start();
    }
}

