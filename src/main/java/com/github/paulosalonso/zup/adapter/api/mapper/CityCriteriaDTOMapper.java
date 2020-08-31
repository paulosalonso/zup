package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityCriteriaDTO;
import com.github.paulosalonso.zup.usecase.port.city.CityCriteria;

public interface CityCriteriaDTOMapper {

    static CityCriteria to(CityCriteriaDTO dto) {
        return CityCriteria.of()
                .name(dto.getName())
                .state(dto.getState())
                .page(dto.getPage())
                .size(dto.getSize())
                .order(dto.getOrder())
                .build();
    }

}
