package com.hocinebouarara.springbootdemo.customer;

import com.hocinebouarara.springbootdemo.exception.ResourceNotFountException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(()->new ResourceNotFountException(
                        "Customer with id %s not found".formatted(id)));
    }

    public void addCustomer(
            CustomerRegistrationRequest customerRegistrationRequest){

        // check if the email exist
        if(customerDao.existPersonWithEmail(customerRegistrationRequest.email())){
            throw new ResourceNotFountException("email already taken");
        }
        // add

        Customer customer = new Customer();

        customer.setName(customerRegistrationRequest.name());
        customer.setEmail(customerRegistrationRequest.email());
        customer.setAge(customerRegistrationRequest.age());

        customerDao.insertCustomer(customer);

    }
}
