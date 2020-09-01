package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCriteriaDTO;
import com.github.paulosalonso.zup.usecase.port.customer.CustomerCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Map;
import java.util.stream.Collectors;

public interface CustomerCriteriaDTOMapper {

    static CustomerCriteria to(CustomerCriteriaDTO dto, Pageable pageable) {
        return CustomerCriteria.of()
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
