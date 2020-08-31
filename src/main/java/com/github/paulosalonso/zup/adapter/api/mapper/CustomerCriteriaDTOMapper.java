package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCriteriaDTO;
import com.github.paulosalonso.zup.usecase.port.customer.CustomerCriteria;

public interface CustomerCriteriaDTOMapper {

    static CustomerCriteria to(CustomerCriteriaDTO dto) {
        return CustomerCriteria.of()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .page(dto.getPage())
                .size(dto.getSize())
                .order(dto.getOrder())
                .build();
    }

}
