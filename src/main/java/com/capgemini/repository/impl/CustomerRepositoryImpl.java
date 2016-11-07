package com.capgemini.repository.impl;

import com.capgemini.models.Customer;
import com.capgemini.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static Set<Customer> customers = new HashSet();

    public Set<Customer> findAll() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Vibhor");
        customer1.setLastName("Kansal");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Amit");
        customer2.setLastName("Rawat");
        customers.add(customer2);

        return customers;
    }

    public Customer addCustomer(Customer customer) {
        return null;
    }
}
