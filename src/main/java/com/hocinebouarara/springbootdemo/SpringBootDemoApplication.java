package com.hocinebouarara.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @GetMapping("/")
    public GreetResponse greet(){

        GreetResponse response = new GreetResponse(
                "Hello world!",
                List.of("Java","C++","Python"),
                new Person(
                        "Hocine Bouarara",
                        27,
                        7000
                )
        );

        return response;
    }

    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
    ){}

    record Person(
            String name,
            int age,
            float salary
    ){

    }

//    class GreetResponse{
//        private final String greet;
//
//        GreetResponse(String greet) {
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
