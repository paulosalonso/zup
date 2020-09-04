package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityResponseDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityUpdateDTO;
import com.github.paulosalonso.zup.domain.City;

import java.util.List;
import java.util.stream.Collectors;

public interface CityDTOMapper {

    static City to(CityResponseDTO dto) {
        return City.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static City to(CityCreateDTO dto) {
        return City.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static City to(CityUpdateDTO from, City to) {
        to.setName(from.getName());
        to.setState(from.getState());
        return to;
    }

    static CityResponseDTO from(City city) {
        return CityResponseDTO.of()
                .ibgeCode(city.getIbgeCode())
                .name(city.getName())
                .state(city.getState())
                .build();
    }

    static List<CityResponseDTO> from(List<City> cities) {
        return cities.stream()
                .map(CityDTOMapper::from)
                .collect(Collectors.toList());
    }
}
