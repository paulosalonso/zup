package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.port.customer.UpdateCustomerPort;

public class UpdateCustomer {

    private final UpdateCustomerPort updateCustomerPort;
    private final CreateCity createCity;

    public UpdateCustomer(UpdateCustomerPort updateCustomerPort, CreateCity createCity) {
        this.updateCustomerPort = updateCustomerPort;
        this.createCity = createCity;
    }

    public Customer update(Customer customer) {
        createCity.resolveCity(customer.getAddress());
        return updateCustomerPort.update(customer);
    }
}
