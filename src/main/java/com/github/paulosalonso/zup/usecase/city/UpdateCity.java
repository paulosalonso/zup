package com.github.paulosalonso.zup.usecase.city;

import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.port.city.UpdateCityPort;

public class UpdateCity {

    private final UpdateCityPort updateCityPort;

    public UpdateCity(UpdateCityPort updateCityPort) {
        this.updateCityPort = updateCityPort;
    }

    public City update(City city) {
        return updateCityPort.update(city);
    }
}
