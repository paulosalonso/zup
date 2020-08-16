package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.model.Gender;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerVOTest {

    @Test
    public void whenBuildCustomerVOThenSuccess() {
        AddressVO address = AddressVO.of()
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

        CustomerVO customerVO = CustomerVO.of()
                .id(1L)
                .name("name")
                .address(address)
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .cpf("cpf")
                .gender(Gender.MALE)
                .build();

        assertThat(customerVO.getId()).isEqualTo(1L);
        assertThat(customerVO.getName()).isEqualTo("name");
        assertThat(customerVO.getAddress()).isSameAs(address);
        assertThat(customerVO.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(customerVO.getCpf()).isEqualTo("cpf");
        assertThat(customerVO.getGender()).isEqualTo(Gender.MALE);
    }
}
