package com.capgemini.services.impl;

import com.capgemini.helpers.GenerateRandomValues;
import com.capgemini.models.Customer;
import com.capgemini.repository.CustomerRepository;
import com.capgemini.repository.impl.CustomerRepositoryImpl;
import com.capgemini.services.CustomerService;

import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public Set<Customer> findAll() {
        customerRepository = new CustomerRepositoryImpl();
        return customerRepository.findAll();
    }

    @Override
    public Customer addNewCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(GenerateRandomValues.randomNameGenerator());
        customer.setLastName(GenerateRandomValues.randomNameGenerator());
        return customer;
    }
}
