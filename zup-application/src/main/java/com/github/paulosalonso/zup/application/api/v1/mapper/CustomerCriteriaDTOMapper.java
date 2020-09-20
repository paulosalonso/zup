package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCriteriaAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerCriteriaDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerCriteriaDTOMapper {

    static CustomerCriteriaAdapter to(CustomerCriteriaDTO dto, Pageable pageable) {
        return CustomerCriteriaAdapter.of()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .sort(SortMapper.to(pageable))
                .build();
    }

}
