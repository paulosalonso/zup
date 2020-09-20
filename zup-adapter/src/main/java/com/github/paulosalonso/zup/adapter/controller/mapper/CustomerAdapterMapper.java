package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerUpdateAdapter;
import com.github.paulosalonso.zup.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

public interface CustomerAdapterMapper {

    static Customer to(CustomerCreateAdapter dto) {
        return Customer.of()
                .name(dto.getName())
                .address(AddressAdapterMapper.to(dto.getAddress()))
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .cpf(dto.getCpf())
                .build();
    }

    static Customer to(CustomerUpdateAdapter dto, Customer customer) {
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setAddress(AddressAdapterMapper.to(dto.getAddress()));

        return customer;
    }

    static CustomerResponseAdapter from(Customer customer) {
        return CustomerResponseAdapter.of()
                .id(customer.getId())
                .name(customer.getName())
                .address(AddressAdapterMapper.from(customer.getAddress()))
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .cpf(customer.getCpf())
                .build();
    }

    static List<CustomerResponseAdapter> fromList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerAdapterMapper::from)
                .collect(Collectors.toList());
    }
}
