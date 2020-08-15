package com.github.paulosalonso.zup.domain.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    @Test
    public void whenBuildAddressThenSuccess() {
        City city = City.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("ST")
                .build();

        Address address = Address.of()
                .street("street")
                .number("number")
                .district("district")
                .complement("complement")
                .postalCode("postal-code")
                .city(city)
                .build();

        assertThat(address.getStreet()).isEqualTo("street");
        assertThat(address.getNumber()).isEqualTo("number");
        assertThat(address.getDistrict()).isEqualTo("district");
        assertThat(address.getComplement()).isEqualTo("complement");
        assertThat(address.getPostalCode()).isEqualTo("postal-code");
        assertThat(address.getCity()).isEqualTo(city);
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Address.class).usingGetClass().verify();
    }

}
