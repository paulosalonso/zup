package com.github.paulosalonso.zup.usecase.customer;

import com.github.paulosalonso.zup.domain.Address;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.domain.Gender;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.event.CustomerCreated;
import com.github.paulosalonso.zup.usecase.port.customer.CreateCustomerPort;
import com.github.paulosalonso.zup.usecase.publisher.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateCustomerTest {

    private CreateCustomer createCustomer;

    @Mock
    private CreateCustomerPort createCustomerPort;

    @Mock
    private CreateCity createCity;

    @Mock
    private Publisher publisher;

    @BeforeEach
    public void setup() {
        initMocks(this);
        createCustomer = new CreateCustomer(createCustomerPort, createCity, publisher);
    }

    @Test
    public void whenCreateCustomerThenResolveCity() {
        Customer customer = buildCustomer();
        createCustomer.create(customer);
        verify(createCity).resolveCity(customer.getAddress());
    }

    @Test
    public void whenCreateCustomerThenCallPort() {
        Customer customer = buildCustomer();
        createCustomer.create(customer);
        verify(createCustomerPort).create(customer);
    }

    @Test
    public void whenCreateCustomerThenPublishCustomerCreatedEvent() {
        Customer customer = buildCustomer();

        when(createCustomerPort.create(customer)).thenReturn(customer);

        createCustomer.create(customer);

        ArgumentCaptor<CustomerCreated> captor = ArgumentCaptor.forClass(CustomerCreated.class);
        verify(publisher).publish(captor.capture());

        CustomerCreated event = captor.getValue();
        assertThat(event.getCustomer()).isSameAs(customer);
    }

    private Customer buildCustomer() {
        return Customer.of()
                .name("name")
                .cpf("00000000000")
                .gender(Gender.MALE)
                .birthDate(LocalDate.now())
                .address(Address.of()
                        .street("street")
                        .number("number")
                        .complement("complement")
                        .district("district")
                        .postalCode("postal-code")
                        .city(City.of()
                                .ibgeCode("ibge-code")
                                .name("name")
                                .state("state")
                                .build())
                        .build())
                .build();
    }
}
