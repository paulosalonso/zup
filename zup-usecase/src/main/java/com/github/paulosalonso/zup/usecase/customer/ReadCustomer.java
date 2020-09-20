package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.customer.CustomerCriteria;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;

import java.util.List;

public class ReadCustomer {

    private final ReadCustomerPort readCustomerPort;

    public ReadCustomer(ReadCustomerPort readCustomerPort) {
        this.readCustomerPort = readCustomerPort;
    }

    public Customer findById(Long id) {
        return readCustomerPort.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Customer> findAll() {
        return readCustomerPort.findAll();
    }

    public Page<Customer> findByCriteria(CustomerCriteria criteria) {
        return readCustomerPort.findByCriteria(criteria);
    }
}
