package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityUpdateAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityUpdateDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface CityDTOMapper {

    static CityResponseAdapter to(CityResponseDTO dto) {
        return CityResponseAdapter.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static CityCreateAdapter to(CityCreateDTO dto) {
        return CityCreateAdapter.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static CityUpdateAdapter to(CityUpdateDTO dto) {
        return CityUpdateAdapter.of()
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static CityResponseDTO from(CityResponseAdapter city) {
        return CityResponseDTO.of()
                .ibgeCode(city.getIbgeCode())
                .name(city.getName())
                .state(city.getState())
                .build();
    }

    static List<CityResponseDTO> from(List<CityResponseAdapter> cities) {
        return cities.stream()
                .map(CityDTOMapper::from)
                .collect(Collectors.toList());
    }
}
