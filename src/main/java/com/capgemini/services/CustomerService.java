package com.capgemini.services;

import com.capgemini.models.Customer;

import java.util.Set;

public interface CustomerService {

    public Set<Customer> findAll();

    public Customer addNewCustomer();
}
