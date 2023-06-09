package com.hocinebouarara.springbootdemo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsCustomerByEmail(String email);

    boolean existsCustomerById(Integer id);
}
