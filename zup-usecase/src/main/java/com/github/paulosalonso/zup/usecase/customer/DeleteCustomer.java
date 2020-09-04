package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.port.customer.DeleteCustomerPort;

public class DeleteCustomer {

    private final DeleteCustomerPort deleteCustomerPort;

    public DeleteCustomer(DeleteCustomerPort deleteCustomerPort) {
        this.deleteCustomerPort = deleteCustomerPort;
    }

    public void delete(Customer customer) {
        deleteCustomerPort.delete(customer);
    }

    public void deleteById(Long id) {
        deleteCustomerPort.deleteById(id);
    }
}
