package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.event.CustomerCreated;
import com.github.paulosalonso.zup.usecase.port.city.ReadCityPort;
import com.github.paulosalonso.zup.usecase.port.customer.CreateCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;
import com.github.paulosalonso.zup.usecase.publisher.Publisher;
import com.github.paulosalonso.zup.usecase.publisher.PublisherFake;

public class CreateCustomer {

    private final CreateCustomerPort createCustomerPort;
    private final CreateCity createCity;
    private final Publisher publisher;

    public CreateCustomer(CreateCustomerPort createCustomerPort, CreateCity createCity) {
        this(createCustomerPort, createCity, new PublisherFake());
    }

    public CreateCustomer(CreateCustomerPort createCustomerPort, CreateCity createCity, Publisher publisher) {
        this.createCustomerPort = createCustomerPort;
        this.createCity = createCity;
        this.publisher = publisher;
    }

    public Customer create(Customer customer) {
        createCity.resolveCity(customer.getAddress());
        Customer created = createCustomerPort.create(customer);
        publisher.publish(CustomerCreated.of(created));
        return created;
    }

}
