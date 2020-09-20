package com.github.paulosalonso.zup.adapter.repository.mapper;

import com.github.paulosalonso.zup.adapter.repository.model.AddressEmbeddable;
import com.github.paulosalonso.zup.adapter.repository.model.CustomerEntity;
import com.github.paulosalonso.zup.domain.Address;
import com.github.paulosalonso.zup.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

public interface CustomerEntityMapper {

    static CustomerEntity from(Customer customer) {
        AddressEmbeddable address = null;

        if (customer.getAddress() != null) {
            address = AddressEmbeddableMapper.from(customer.getAddress());
        }

        return CustomerEntity.of()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .address(address)
                .build();
    }

    static Customer to(CustomerEntity customer) {
        Address address = null;

        if (customer.getAddress() != null) {
            address = AddressEmbeddableMapper.to(customer.getAddress());
        }

        return Customer.of()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .address(address)
                .build();
    }

    static List<Customer> toList(List<CustomerEntity> entities) {
        return entities.stream()
                .map(CustomerEntityMapper::to)
                .collect(Collectors.toList());
    }

}
