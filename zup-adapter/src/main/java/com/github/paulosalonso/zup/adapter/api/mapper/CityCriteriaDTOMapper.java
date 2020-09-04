package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityCriteriaDTO;
import com.github.paulosalonso.zup.usecase.port.city.CityCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.stream.Collectors;

public interface CityCriteriaDTOMapper {

    static CityCriteria to(CityCriteriaDTO dto, Pageable pageable) {
        return CityCriteria.of()
                .name(dto.getName())
                .state(dto.getState())
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .sort(SortMapper.to(pageable))
                .build();
    }

}
