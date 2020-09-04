package com.github.paulosalonso.zup.usecase.port.customer;

import com.github.paulosalonso.zup.domain.Customer;

public interface UpdateCustomerPort {
    Customer update(Customer customer);
}
