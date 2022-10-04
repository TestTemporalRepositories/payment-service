package org.payment.payment.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@Data
@ConfigurationProperties(prefix = "application.temporal")
public class TemporalProperties {
    private String server;
    private String namespace;
    private Map<String, WorkerProperties> workers;
    private Map<String, String> activityQueues;
}
