package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityUpdateAdapter;
import com.github.paulosalonso.zup.domain.City;

import java.util.List;
import java.util.stream.Collectors;

public interface CityAdapterMapper {

    static City to(CityResponseAdapter dto) {
        return City.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static City to(CityCreateAdapter dto) {
        return City.of()
                .ibgeCode(dto.getIbgeCode())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    static City to(CityUpdateAdapter from, City to) {
        to.setName(from.getName());
        to.setState(from.getState());
        return to;
    }

    static CityResponseAdapter from(City city) {
        return CityResponseAdapter.of()
                .ibgeCode(city.getIbgeCode())
                .name(city.getName())
                .state(city.getState())
                .build();
    }

    static List<CityResponseAdapter> from(List<City> cities) {
        return cities.stream()
                .map(CityAdapterMapper::from)
                .collect(Collectors.toList());
    }
}
