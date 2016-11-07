package com.capgemini.repository;

import com.capgemini.models.Customer;

import java.util.Set;

public interface CustomerRepository {

    public Set<Customer> findAll();

    public Customer addCustomer(Customer customer);
}
