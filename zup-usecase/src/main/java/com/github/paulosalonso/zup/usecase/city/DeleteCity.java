package com.github.paulosalonso.zup.usecase.city;

import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.port.city.DeleteCityPort;

public class DeleteCity {

    private final DeleteCityPort deleteCityPort;

    public DeleteCity(DeleteCityPort deleteCityPort) {
        this.deleteCityPort = deleteCityPort;
    }

    public void delete(City city) {
        deleteCityPort.delete(city);
    }

    public void deleteById(String id) {
        deleteCityPort.deleteById(id);
    }
}
