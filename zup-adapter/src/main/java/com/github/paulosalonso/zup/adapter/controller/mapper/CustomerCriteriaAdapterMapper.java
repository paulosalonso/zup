package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCriteriaAdapter;
import com.github.paulosalonso.zup.usecase.port.customer.CustomerCriteria;
import org.springframework.data.domain.Pageable;

public interface CustomerCriteriaAdapterMapper {

    static CustomerCriteria to(CustomerCriteriaAdapter dto) {
        return CustomerCriteria.of()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .page(dto.getPage())
                .size(dto.getSize())
                .sort(dto.getSort())
                .build();
    }

}
