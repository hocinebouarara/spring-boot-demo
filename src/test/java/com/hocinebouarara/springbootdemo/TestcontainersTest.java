package com.hocinebouarara.springbootdemo;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestcontainersTest {

    @Container
    private static PostgreSQLContainer<?> postgresSQLContainer
            = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("hocine-dao-unit-test")
            .withUsername("postgres")
            .withPassword("hocine09");

    @Test
    void canStartPostgresDB() {
        assertThat(postgresSQLContainer.isRunning()).isTrue();
        assertThat(postgresSQLContainer.isCreated()).isTrue();
    }

    @Test
    void canApplyDbMigrationWithFlyway(){
        Flyway flyway = Flyway.configure().dataSource(
                postgresSQLContainer.getJdbcUrl(),
                postgresSQLContainer.getUsername(),
                postgresSQLContainer.getPassword()
        ).load();

        flyway.migrate();
        System.out.println();
    }
}
