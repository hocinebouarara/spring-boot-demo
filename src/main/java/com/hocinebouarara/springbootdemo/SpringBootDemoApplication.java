package com.hocinebouarara.springbootdemo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.hocinebouarara.springbootdemo.customer.Customer;
import com.hocinebouarara.springbootdemo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SpringBootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {

            var faker = new Faker();
            Random random = new Random();
            Name name = faker.name();

            String firstName = name.firstName();
            String lastName = name.lastName();


            Customer customer = new Customer(
                    firstName +" "+lastName,
                    firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com",
                    random.nextInt(17,90)
                );

            customerRepository.save(customer);

        };
    }
}
