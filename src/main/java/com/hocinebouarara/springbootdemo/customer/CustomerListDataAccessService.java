package com.hocinebouarara.springbootdemo.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{

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

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream()
                .anyMatch(
                c -> c.getId().equals(id)
        );
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(customers::remove);

    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }


}
