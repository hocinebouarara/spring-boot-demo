package com.hocinebouarara.springbootdemo.customer;

import com.hocinebouarara.springbootdemo.exception.RequestValidationException;
import com.hocinebouarara.springbootdemo.exception.ResourceNotFountException;
import com.sun.jdi.request.DuplicateRequestException;
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

    public void deleteCustomerById(Integer customerId){
        // check if the customer is exists
        if (!customerDao.existsPersonWithId(customerId)){
            throw new ResourceNotFountException(
                    "customer with id %s not found".formatted(customerId)
            );
        }

        //delete
        customerDao.deleteCustomerById(customerId);

    }

    public void updateCustomer(Integer customerId,
                               CustomerRegistrationRequest updateRequest){
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
            if (customerDao.existPersonWithEmail(updateRequest.email())){
                throw  new DuplicateRequestException(
                        "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (!changes){
            throw new RequestValidationException(
                    "no data changes found"
            );
        }

        customerDao.updateCustomer(customer);
    }
}
