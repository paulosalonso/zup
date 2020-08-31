package com.github.paulosalonso.zup.adapter.configuration;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerServicesConfiguration {

    @Bean
    public CreateCustomer createCustomer(CreateCustomerPort createCustomerPort,
            ReadCustomerPort readCustomerPort, ReadCityPort readCityPort, CreateCity createCity) {
        return new CreateCustomer(createCustomerPort, readCustomerPort, readCityPort, createCity);
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
}
