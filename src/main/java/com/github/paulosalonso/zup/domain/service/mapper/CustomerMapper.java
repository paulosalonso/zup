package com.github.paulosalonso.zup.domain.service.mapper;

import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;

import static com.github.paulosalonso.zup.domain.service.mapper.AddressMapper.addressEntityToAddressVO;
import static com.github.paulosalonso.zup.domain.service.mapper.AddressMapper.addressVOToAddressEntity;

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

    static Customer customerCreateVOToCustomerEntity(CustomerCreateVO customerCreateVO) {
        return Customer.of()
                .name(customerCreateVO.getName())
                .address(addressVOToAddressEntity(customerCreateVO.getAddress()))
                .cpf(customerCreateVO.getCpf())
                .gender(customerCreateVO.getGender())
                .birthDate(customerCreateVO.getBirthDate())
                .build();
    }

    static Customer customerUpdateVOToCustomerEntity(CustomerUpdateVO customerUpdateVO, Customer customer) {
        customer.setName(customerUpdateVO.getName());
        customer.setGender(customerUpdateVO.getGender());
        customer.setAddress(addressVOToAddressEntity(customerUpdateVO.getAddress()));
        return customer;
    }
}
