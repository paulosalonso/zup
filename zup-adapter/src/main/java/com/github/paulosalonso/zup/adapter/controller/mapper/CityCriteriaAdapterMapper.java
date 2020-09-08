package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityCriteriaAdapter;
import com.github.paulosalonso.zup.usecase.port.city.CityCriteria;
import org.springframework.data.domain.Pageable;

public interface CityCriteriaAdapterMapper {

    static CityCriteria to(CityCriteriaAdapter dto) {
        return CityCriteria.of()
                .name(dto.getName())
                .state(dto.getState())
                .page(dto.getPage())
                .size(dto.getSize())
                .sort(dto.getSort())
                .build();
    }

}
