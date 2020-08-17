package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Address;
import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.model.Gender;
import com.github.paulosalonso.zup.domain.repository.CustomerRepository;
import com.github.paulosalonso.zup.domain.service.CityService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder.buildPageable;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CityService cityService;

    @Test
    public void whenSearchThenReturnCustomers() {
        CustomerSearchVO customerSearchVO = CustomerSearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name"))
                .build();

        List<com.github.paulosalonso.zup.domain.model.Customer> cities = List.of(buildCustomerById(1L, "-a"), buildCustomerById(2L, "-b"));

        Pageable pageable = buildPageable(customerSearchVO);

        when(customerRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(new PageImpl<>(cities));

        PageVO<CustomerVO> customerVOPage = customerService.search(customerSearchVO);

        assertThat(customerVOPage.getPage()).isEqualTo(0);
        assertThat(customerVOPage.getPageSize()).isEqualTo(2);
        assertThat(customerVOPage.getTotalPages()).isEqualTo(1);
        assertThat(customerVOPage.getTotalSize()).isEqualTo(2);
        assertThat(customerVOPage.getContent())
                .hasSize(2)
                .satisfies(customersVOs -> {
                    assertCustomerVO(customersVOs.get(0), 1L, "-a");
                    assertCustomerVO(customersVOs.get(1), 2L, "-b");
                });

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(customerRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getPageNumber()).isEqualTo(1);
        assertThat(pageableArgument.getPageSize()).isEqualTo(20);
        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.ASC));
    }

    @Test
    public void whenSearchWithDescOrderThenUseDirectionDesc() {
        CustomerSearchVO customerSearchVO = CustomerSearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name.desc"))
                .build();

        when(customerRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(emptyList()));

        PageVO<CustomerVO> customerVOPage = customerService.search(customerSearchVO);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(customerRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.DESC));
    }

    @Test
    public void whenSearchWithInvalidOrderThenUseDirectionAsc() {
        CustomerSearchVO customerSearchVO = CustomerSearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name.xxx"))
                .build();

        when(customerRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(emptyList()));

        PageVO<CustomerVO> customerVOPage = customerService.search(customerSearchVO);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(customerRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.ASC));
    }

    @Test
    public void whenCreateThenReturnCustomerVO() {
        CustomerCreateVO customerCreateVO = CustomerCreateVO.of()
                .name("name")
                .address(AddressVO.of()
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
                        .build())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .gender(Gender.MALE)
                .cpf("cpf")
                .build();

        Customer customerToCreate = buildCustomerWithoutId();
        Customer createdCustomer = buildCustomerById(1L, "");

        when(cityService.read("ibge-code")).thenReturn(CityVO.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build());

        when(customerRepository.save(customerToCreate)).thenReturn(createdCustomer);

        CustomerVO customerVO = customerService.create(customerCreateVO);

        assertCustomerVO(customerVO, 1L, "");

        verify(customerRepository).save(customerToCreate);
    }

    @Test
    public void whenCreateCustomerDuplicatedCpfThenThrowsCreateException() {
        CustomerCreateVO customerCreateVO = CustomerCreateVO.of()
                .address(AddressVO.of()
                        .city(CityVO.of().build())
                        .build())
                .cpf("cpf")
                .build();

        Customer customerToCreate = Customer.of()
                .address(Address.of()
                        .city(City.of().build())
                        .build())
                .cpf("cpf")
                .build();

        when(cityService.read(null)).thenReturn(CityVO.of().build());
        when(customerRepository.save(customerToCreate)).thenThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> customerService.create(customerCreateVO))
                .isExactlyInstanceOf(CreateException.class)
                .hasMessage("There is already a customer with this cpf");
    }

    @Test
    public void whenReadThenReturnCustomer() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(buildCustomerById(1L, "")));

        assertCustomerVO(customerService.read(1L), 1L, "");

        verify(customerRepository).findById(1L);
    }

    @Test
    public void whenReadThenThrowsNotFoundException() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.read(1L))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(customerRepository).findById(1L);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void whenUpdateThenReturnCustomerVO() {
        when(cityService.read("ibge-code-updated")).thenReturn(CityVO.of()
                .ibgeCode("ibge-code-updated")
                .name("name-updated")
                .state("state-updated")
                .build());

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(buildCustomerById(1L, "")));

        CustomerUpdateVO customerToUpdate = CustomerUpdateVO.of()
                .name("name-updated")
                .gender(Gender.FEMALE)
                .address(AddressVO.of()
                        .city(CityVO.of()
                                .ibgeCode("ibge-code-updated")
                                .name("name-updated")
                                .state("state-updated")
                                .build())
                        .street("street-updated")
                        .number("number-updated")
                        .complement("complement-updated")
                        .district("district-updated")
                        .postalCode("postal-code-updated")
                        .build())
                .build();

        CustomerVO updatedCustomerVO = customerService.update(1L, customerToUpdate);

        assertThat(updatedCustomerVO.getId()).isEqualTo(1L);
        assertThat(updatedCustomerVO.getName()).isEqualTo("name-updated");
        assertThat(updatedCustomerVO.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(updatedCustomerVO.getGender()).isEqualTo(Gender.FEMALE);
        assertThat(updatedCustomerVO.getCpf()).isEqualTo("cpf");
        assertThat(updatedCustomerVO.getAddress())
                .isNotNull()
                .isInstanceOf(AddressVO.class)
                .satisfies(addressVO -> {
                    assertThat(addressVO.getCity())
                            .isNotNull()
                            .isInstanceOf(CityVO.class)
                            .satisfies(cityVO -> {
                                assertThat(cityVO.getIbgeCode()).isEqualTo("ibge-code-updated");
                                assertThat(cityVO.getName()).isEqualTo("name-updated");
                                assertThat(cityVO.getState()).isEqualTo("state-updated");
                            });
                    assertThat(addressVO.getStreet()).isEqualTo("street-updated");
                    assertThat(addressVO.getNumber()).isEqualTo("number-updated");
                    assertThat(addressVO.getComplement()).isEqualTo("complement-updated");
                    assertThat(addressVO.getDistrict()).isEqualTo("district-updated");
                    assertThat(addressVO.getPostalCode()).isEqualTo("postal-code-updated");
                });

        verify(customerRepository).findById(1L);
    }

    @Test
    public void whenUpdateThenThrowsNotFoundException() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.update(1L, CustomerUpdateVO.of().build()))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(customerRepository).findById(1L);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void whenDeleteThenSuccess() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        customerService.delete(1L);

        verify(customerRepository).existsById(1L);
        verify(customerRepository).deleteById(1L);
    }

    @Test
    public void whenDeleteThenThrowsNotFountException() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> customerService.delete(1L))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(customerRepository).existsById(1L);
        verifyNoMoreInteractions(customerRepository);
    }

    private Customer buildCustomerWithoutId() {
        return buildCustomerById(null, "");
    }

    private Customer buildCustomerById(Long id, String suffix) {
        return com.github.paulosalonso.zup.domain.model.Customer.of()
                .id(id)
                .name("name" + suffix)
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
                .cpf("cpf" + suffix)
                .build();
    }

    private void assertCustomerVO(CustomerVO customerVO, Long id, String suffix) {
        assertThat(customerVO.getId()).isEqualTo(id);
        assertThat(customerVO.getName()).isEqualTo("name" + suffix);
        assertThat(customerVO.getBirthDate()).isEqualTo(LocalDate.of(1988, Month.FEBRUARY, 26));
        assertThat(customerVO.getGender()).isEqualTo(Gender.MALE);
        assertThat(customerVO.getCpf()).isEqualTo("cpf" + suffix);
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

}
