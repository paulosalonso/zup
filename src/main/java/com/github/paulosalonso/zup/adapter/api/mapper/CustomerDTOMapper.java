package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerUpdateDTO;
import com.github.paulosalonso.zup.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

public interface CustomerDTOMapper {

    static Customer to(CustomerCreateDTO dto) {
        return Customer.of()
                .name(dto.getName())
                .address(AddressDTOMapper.to(dto.getAddress()))
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .cpf(dto.getCpf())
                .build();
    }

    static Customer to(CustomerUpdateDTO dto, Customer customer) {
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setAddress(AddressDTOMapper.to(dto.getAddress()));

        return customer;
    }

    static CustomerDTO from(Customer customer) {
        return CustomerDTO.of()
                .id(customer.getId())
                .name(customer.getName())
                .address(AddressDTOMapper.from(customer.getAddress()))
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .cpf(customer.getCpf())
                .build();
    }

    static List<CustomerDTO> fromList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerDTOMapper::from)
                .collect(Collectors.toList());
    }
}
