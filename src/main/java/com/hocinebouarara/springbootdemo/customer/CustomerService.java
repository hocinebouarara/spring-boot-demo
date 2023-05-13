package com.hocinebouarara.springbootdemo.customer;

import com.hocinebouarara.springbootdemo.exception.ResourceNotFount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(()->new ResourceNotFount(
                        "Customer with id %s not found".formatted(id)));
    }
}
