package com.hocinebouarara.springbootdemo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    public List<Customer> selectAllCustomers();

    public Optional<Customer> selectCustomerById(Integer id);
}
