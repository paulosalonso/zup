package com.github.paulosalonso.zup.adapter.api.controller;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static JdbcDatabaseContainer databaseContainer;

    static {
        initializePostgreDatabaseContainer();
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "spring.datasource.url=" + databaseContainer.getJdbcUrl(),
                "spring.datasource.username=" + databaseContainer.getUsername(),
                "spring.datasource.password=" + databaseContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }

    private static final void initializePostgreDatabaseContainer() {
        databaseContainer = new PostgreSQLContainer()
                .withDatabaseName("integration-tests-db")
                .withUsername("sa")
                .withPassword("sa");

        databaseContainer.start();
    }
}
