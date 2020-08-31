package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Address;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.city.ReadCityPort;
import com.github.paulosalonso.zup.usecase.port.customer.CreateCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;
import org.springframework.dao.DataIntegrityViolationException;

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

        try {
            return createCustomerPort.create(customer);
        } catch (DataIntegrityViolationException e) {
            throw new CreateException("There is already a customer with this cpf");
        }
    }

}
