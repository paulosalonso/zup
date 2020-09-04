package com.github.paulosalonso.zup.usecase.port.city;

import com.github.paulosalonso.zup.domain.City;

public interface DeleteCityPort {
    void delete(City city);
    void deleteById(String id);
}
