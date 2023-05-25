package com.hocinebouarara.springbootdemo;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public abstract class AbstractTestContainers {

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                postgresSQLContainer.getJdbcUrl(),
                postgresSQLContainer.getUsername(),
                postgresSQLContainer.getPassword()
        ).load();

        flyway.migrate();
        System.out.println();
    }

    @Container
    protected static PostgreSQLContainer<?> postgresSQLContainer
            = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("hocine-dao-unit-test")
            .withUsername("postgres")
            .withPassword("hocine09");

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry){
        registry.add(
                "spring.datasource.url",
                () -> postgresSQLContainer.getJdbcUrl()
        );
        registry.add(
                "spring.datasource.username",
                () -> postgresSQLContainer.getUsername()
        );
        registry.add(
                "spring.datasource.password",
                () -> postgresSQLContainer.getPassword()
        );

    }

    private static DataSource getDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create()
                .driverClassName(postgresSQLContainer.getDriverClassName())
                .url(postgresSQLContainer.getJdbcUrl())
                .username(postgresSQLContainer.getUsername())
                .password(postgresSQLContainer.getPassword())
                ;

        return builder.build();
    }

    protected static JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

}
