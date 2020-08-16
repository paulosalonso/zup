package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressVOTest {

    @Test
    public void whenBuildAddressVOThenSuccess() {
        CityVO city = CityVO.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build();

        AddressVO addressVO = AddressVO.of()
                .city(city)
                .street("street")
                .number("number")
                .complement("complement")
                .district("district")
                .postalCode("postal-code")
                .build();

        assertThat(addressVO.getCity()).isSameAs(city);
        assertThat(addressVO.getStreet()).isEqualTo("street");
        assertThat(addressVO.getNumber()).isEqualTo("number");
        assertThat(addressVO.getComplement()).isEqualTo("complement");
        assertThat(addressVO.getDistrict()).isEqualTo("district");
        assertThat(addressVO.getPostalCode()).isEqualTo("postal-code");
    }
}
