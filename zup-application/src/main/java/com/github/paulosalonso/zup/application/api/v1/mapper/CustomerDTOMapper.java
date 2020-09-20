package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerUpdateAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerUpdateDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface CustomerDTOMapper {

    static CustomerCreateAdapter to(CustomerCreateDTO dto) {
        return CustomerCreateAdapter.of()
                .name(dto.getName())
                .address(AddressDTOMapper.to(dto.getAddress()))
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .cpf(dto.getCpf())
                .build();
    }

    static CustomerUpdateAdapter to(CustomerUpdateDTO dto) {
        return CustomerUpdateAdapter.of()
                .address(AddressDTOMapper.to(dto.getAddress()))
                .gender(dto.getGender())
                .name(dto.getName())
                .build();
    }

    static CustomerResponseDTO from(CustomerResponseAdapter customer) {
        return CustomerResponseDTO.of()
                .id(customer.getId())
                .name(customer.getName())
                .address(AddressDTOMapper.from(customer.getAddress()))
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .cpf(customer.getCpf())
                .build();
    }

    static List<CustomerResponseDTO> fromList(List<CustomerResponseAdapter> customers) {
        return customers.stream()
                .map(CustomerDTOMapper::from)
                .collect(Collectors.toList());
    }
}
