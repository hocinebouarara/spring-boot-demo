package com.hocinebouarara.springbootdemo;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.hocinebouarara.springbootdemo.AbstractTestContainers.postgresSQLContainer;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestcontainersTest extends AbstractTestContainers{
    @Test
    void canStartPostgresDB() {
        assertThat(postgresSQLContainer.isRunning()).isTrue();
        assertThat(postgresSQLContainer.isCreated()).isTrue();
    }

}
