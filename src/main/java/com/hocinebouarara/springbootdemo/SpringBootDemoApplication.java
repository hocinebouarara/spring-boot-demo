package com.hocinebouarara.springbootdemo;

import com.hocinebouarara.springbootdemo.customer.Customer;
import com.hocinebouarara.springbootdemo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {

            Customer hocine = new Customer(
                    1,
                    "hocine",
                    "hocine@gmail.com",
                    27
            );
            Customer walid = new Customer(
                    2,
                    "walid",
                    "walid@gmail.com",
                    26
            );

            List<Customer> customers= List.of(hocine,walid);
            customerRepository.saveAll(customers);

        };
    }
}
