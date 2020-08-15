package com.github.paulosalonso.zup.domain.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void whenBuildCustomerThenSuccess() {
        Address address = Address.of()
                .street("street")
                .number("number")
                .district("district")
                .complement("complement")
                .postalCode("postal-code")
                .city(City.of()
                        .ibgeCode("ibge-code")
                        .name("name")
                        .state("ST")
                        .build())
                .build();

        Customer customer = Customer.of()
                .id(1L)
                .name("name")
                .cpf("cpf")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .address(address)
                .build();

        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("name");
        assertThat(customer.getCpf()).isEqualTo("cpf");
        assertThat(customer.getGender()).isEqualTo(Gender.MALE);
        assertThat(customer.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(customer.getAddress()).isEqualTo(address);
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Customer.class)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .usingGetClass()
                .verify();
    }
}
