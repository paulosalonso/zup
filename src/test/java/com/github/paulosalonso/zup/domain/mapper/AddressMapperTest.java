package com.github.paulosalonso.zup.domain.mapper;

import com.github.paulosalonso.zup.domain.model.Address;
import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.AddressVO;
import org.junit.jupiter.api.Test;

import static com.github.paulosalonso.zup.domain.service.mapper.AddressMapper.addressEntityToAddressVO;
import static com.github.paulosalonso.zup.domain.service.mapper.AddressMapper.addressVOToAddressEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressMapperTest {

    @Test
    public void whenMapAddressEntityToAddressVOThenSuccess() {
        Address address = Address.of()
                .city(City.of()
                        .ibgeCode("ibge-code")
                        .name("name")
                        .state("state")
                        .build())
                .street("street")
                .number("number")
                .complement("complement")
                .district("district")
                .postalCode("postal-code")
                .build();

        AddressVO addressVO = addressEntityToAddressVO(address);

        assertThat(addressVO.getCity())
                .isNotNull()
                .isInstanceOf(CityVO.class)
                .satisfies(cityVO -> {
                    assertThat(cityVO.getIbgeCode()).isEqualTo("ibge-code");
                    assertThat(cityVO.getName()).isEqualTo("name");
                    assertThat(cityVO.getState()).isEqualTo("state");
                });
        assertThat(addressVO.getStreet()).isEqualTo("street");
        assertThat(addressVO.getNumber()).isEqualTo("number");
        assertThat(addressVO.getComplement()).isEqualTo("complement");
        assertThat(addressVO.getDistrict()).isEqualTo("district");
        assertThat(addressVO.getPostalCode()).isEqualTo("postal-code");
    }

    @Test
    public void whenMapAddressVOToAddressEntityThenSuccess() {
        AddressVO addressVO = AddressVO.of()
                .city(CityVO.of()
                        .ibgeCode("ibge-code")
                        .name("name")
                        .state("state")
                        .build())
                .street("street")
                .number("number")
                .complement("complement")
                .district("district")
                .postalCode("postal-code")
                .build();

        Address address = addressVOToAddressEntity(addressVO);

        assertThat(address.getCity())
                .isNotNull()
                .isInstanceOf(City.class)
                .satisfies(city -> {
                    assertThat(city.getIbgeCode()).isEqualTo("ibge-code");
                    assertThat(city.getName()).isEqualTo("name");
                    assertThat(city.getState()).isEqualTo("state");
                });
        assertThat(address.getStreet()).isEqualTo("street");
        assertThat(address.getNumber()).isEqualTo("number");
        assertThat(address.getComplement()).isEqualTo("complement");
        assertThat(address.getDistrict()).isEqualTo("district");
        assertThat(address.getPostalCode()).isEqualTo("postal-code");
    }
}
