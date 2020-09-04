package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.port.city.ReadCityPort;
import com.github.paulosalonso.zup.usecase.port.customer.CreateCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;

public class CreateCustomer {

    private final CreateCustomerPort createCustomerPort;
    private final ReadCustomerPort readCustomerPort;
    private final ReadCityPort readCityPort;
    private final CreateCity createCity;

    public CreateCustomer(CreateCustomerPort createCustomerPort,
            ReadCustomerPort readCustomerPort, ReadCityPort readCityPort, CreateCity createCity) {
        this.createCustomerPort = createCustomerPort;
        this.readCustomerPort = readCustomerPort;
        this.readCityPort = readCityPort;
        this.createCity = createCity;
    }

    public Customer create(Customer customer) {
        createCity.resolveCity(customer.getAddress());
        return createCustomerPort.create(customer);
    }

}
