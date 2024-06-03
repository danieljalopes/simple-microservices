package com.bueno.account;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresContainersConfiguration extends PostgreSQLContainer<PostgresContainersConfiguration> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgresContainersConfiguration container;

    private PostgresContainersConfiguration() {
        super(IMAGE_VERSION);
    }

    public static PostgresContainersConfiguration getInstance() {
        if (container == null) {
            container = new PostgresContainersConfiguration();
        }
        return container;
    }

    @Override
    public void stop() {
        // do nothing, JVM handles shut down
    }
}
