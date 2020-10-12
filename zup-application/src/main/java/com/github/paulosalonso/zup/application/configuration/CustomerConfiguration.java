package com.github.paulosalonso.zup.application.configuration;

import com.github.paulosalonso.zup.adapter.controller.CustomerController;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.customer.CreateCustomer;
import com.github.paulosalonso.zup.usecase.customer.DeleteCustomer;
import com.github.paulosalonso.zup.usecase.customer.ReadCustomer;
import com.github.paulosalonso.zup.usecase.customer.UpdateCustomer;
import com.github.paulosalonso.zup.usecase.port.city.ReadCityPort;
import com.github.paulosalonso.zup.usecase.port.customer.CreateCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.DeleteCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;
import com.github.paulosalonso.zup.usecase.port.customer.UpdateCustomerPort;
import com.github.paulosalonso.zup.usecase.publisher.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    public CreateCustomer createCustomer(
            CreateCustomerPort createCustomerPort, CreateCity createCity, Publisher publisher) {
        return new CreateCustomer(createCustomerPort, createCity, publisher);
    }

    @Bean
    public ReadCustomer readCustomer(ReadCustomerPort readCustomerPort) {
        return new ReadCustomer(readCustomerPort);
    }

    @Bean
    public UpdateCustomer updateCustomer(UpdateCustomerPort updateCustomerPort, CreateCity createCity) {
        return new UpdateCustomer(updateCustomerPort, createCity);
    }

    @Bean
    public DeleteCustomer deleteCustomer(DeleteCustomerPort deleteCustomerPort) {
        return new DeleteCustomer(deleteCustomerPort);
    }

    @Bean
    public CustomerController customerController(CreateCustomer createCustomer,
            ReadCustomer readCustomer, UpdateCustomer updateCustomer, DeleteCustomer deleteCustomer) {
        return new CustomerController(createCustomer, readCustomer, updateCustomer, deleteCustomer);
    }
}
