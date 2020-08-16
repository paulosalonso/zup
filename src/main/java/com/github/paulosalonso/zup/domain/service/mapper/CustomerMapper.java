package com.github.paulosalonso.zup.domain.service.mapper;

import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;

import static com.github.paulosalonso.zup.domain.service.mapper.AddressMapper.addressEntityToAddressVO;

public interface CustomerMapper {

    static CustomerVO customerEntityToCustomerVO(Customer customer) {
        return CustomerVO.of()
                .id(customer.getId())
                .name(customer.getName())
                .address(addressEntityToAddressVO(customer.getAddress()))
                .cpf(customer.getCpf())
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .build();
    }
}
