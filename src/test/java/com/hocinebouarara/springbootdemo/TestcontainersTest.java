package com.hocinebouarara.springbootdemo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestcontainersTest {

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer
            = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("coderapp-doa-unit-test")
            .withUsername("postgres")
            .withPassword("hocine09");

    @Test
    void canStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }
}
