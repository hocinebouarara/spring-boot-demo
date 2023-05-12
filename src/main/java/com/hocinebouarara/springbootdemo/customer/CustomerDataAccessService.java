package com.hocinebouarara.springbootdemo.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService implements CustomerDao{

    // db
    private static List<Customer>  customers;

    static {
        customers = new ArrayList<>();
        Customer hocine = new Customer(
                1,
                "hocine",
                "hocine@gmail.com",
                27
        );
        customers.add(hocine);
        Customer walid = new Customer(
                2,
                "walid",
                "walid@gmail.com",
                26
        );

        customers.add(walid);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
       return customers.stream()
                .filter(c -> c.getId().equals(id) )
                .findFirst();
    }
}
