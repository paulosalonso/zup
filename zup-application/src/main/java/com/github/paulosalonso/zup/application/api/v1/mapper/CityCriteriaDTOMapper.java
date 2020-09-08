package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityCriteriaAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityCriteriaDTO;
import org.springframework.data.domain.Pageable;

public interface CityCriteriaDTOMapper {

    static CityCriteriaAdapter to(CityCriteriaDTO dto, Pageable pageable) {
        return CityCriteriaAdapter.of()
                .name(dto.getName())
                .state(dto.getState())
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .sort(SortMapper.to(pageable))
                .build();
    }

}
