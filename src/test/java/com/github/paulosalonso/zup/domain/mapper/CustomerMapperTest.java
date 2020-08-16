package com.github.paulosalonso.zup.domain.mapper;

import com.github.paulosalonso.zup.domain.model.Address;
import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.model.Gender;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.AddressVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper.customerEntityToCustomerVO;
import static com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper.customerUpdateVOToCustomerEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {

    @Test
    public void whenMapCustomerEntityToCustomerVOThenSuccess() {
        Customer customer = buildCustomer();

        CustomerVO customerVO = customerEntityToCustomerVO(customer);

        assertThat(customerVO.getId()).isEqualTo(1L);
        assertThat(customerVO.getName()).isEqualTo("name");
        assertThat(customerVO.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(customerVO.getGender()).isEqualTo(Gender.MALE);
        assertThat(customerVO.getCpf()).isEqualTo("cpf");
        assertThat(customerVO.getAddress())
                .isNotNull()
                .isInstanceOf(AddressVO.class)
                .satisfies(addressVO -> {
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
                });
    }

    @Test
    public void whenMapCustomerUpdateVOToCustomerEntityThenSuccess() {
        Customer customer = buildCustomer();

        CustomerUpdateVO customerUpdateVO = CustomerUpdateVO.of()
                .name("name-updated")
                .gender(Gender.FEMALE)
                .address(AddressVO.of()
                        .city(CityVO.of()
                                .ibgeCode("ibge-code-updated")
                                .build())
                        .street("street-updated")
                        .number("number-updated")
                        .complement("complement-updated")
                        .district("district-updated")
                        .postalCode("postal-code-updated")
                        .build())
                .build();

        customerUpdateVOToCustomerEntity(customerUpdateVO, customer);

        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("name-updated");
        assertThat(customer.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(customer.getGender()).isEqualTo(Gender.FEMALE);
        assertThat(customer.getCpf()).isEqualTo("cpf");
        assertThat(customer.getAddress())
                .isNotNull()
                .isInstanceOf(Address.class)
                .satisfies(address -> {
                    assertThat(address.getCity())
                            .isNotNull()
                            .isInstanceOf(City.class)
                            .satisfies(city -> assertThat(city.getIbgeCode()).isEqualTo("ibge-code-updated"));
                    assertThat(address.getStreet()).isEqualTo("street-updated");
                    assertThat(address.getNumber()).isEqualTo("number-updated");
                    assertThat(address.getComplement()).isEqualTo("complement-updated");
                    assertThat(address.getDistrict()).isEqualTo("district-updated");
                    assertThat(address.getPostalCode()).isEqualTo("postal-code-updated");
                });
    }

    private Customer buildCustomer() {
        return Customer.of()
                .id(1L)
                .name("name")
                .address(Address.of()
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
                        .build())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .gender(Gender.MALE)
                .cpf("cpf")
                .build();
    }
}