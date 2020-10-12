package com.github.paulosalonso.zup.usecase.event;

import com.github.paulosalonso.zup.domain.Customer;

import java.util.Objects;

public class CustomerCreated {

    private Customer customer;

    private CustomerCreated(Customer customer) {
        this.customer = customer;
    }

    public static CustomerCreated of(Customer customer) {
        return new CustomerCreated(customer);
    }

    public Customer getCustomer() {
        return customer;
    }
}
