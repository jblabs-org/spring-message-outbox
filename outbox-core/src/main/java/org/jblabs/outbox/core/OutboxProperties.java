package org.jblabs.outbox.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OutboxProperties {
    @Value("${outbox.storage.polling.batchSize}")
    private Integer batchSize;
}
