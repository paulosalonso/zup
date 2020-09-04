package com.github.paulosalonso.zup.usecase.port.customer;

import com.github.paulosalonso.zup.domain.Customer;

public interface CreateCustomerPort {
    Customer create(Customer customer);
}
