package com.github.paulosalonso.zup.usecase.port.customer;

import com.github.paulosalonso.zup.domain.Customer;

public interface DeleteCustomerPort {
    void delete(Customer customer);
    void deleteById(Long id);
}
