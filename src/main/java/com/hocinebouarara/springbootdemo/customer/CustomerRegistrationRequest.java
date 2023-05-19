package com.hocinebouarara.springbootdemo.customer;

public record CustomerRegistrationRequest(
       String name,
       String email,
       Integer age
){

}
