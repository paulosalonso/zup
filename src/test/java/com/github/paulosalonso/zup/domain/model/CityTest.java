package com.github.paulosalonso.zup.domain.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {

    @Test
    public void whenBuildCityThenSuccess() {
        City city = City.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("ST")
                .build();

        assertThat(city.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(city.getName()).isEqualTo("name");
        assertThat(city.getState()).isEqualTo("ST");
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(City.class)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .usingGetClass()
                .verify();
    }
}
