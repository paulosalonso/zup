package com.github.paulosalonso.zup.application.configuration;

import com.github.paulosalonso.zup.adapter.controller.CityController;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.city.DeleteCity;
import com.github.paulosalonso.zup.usecase.city.ReadCity;
import com.github.paulosalonso.zup.usecase.city.UpdateCity;
import com.github.paulosalonso.zup.usecase.port.city.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CityConfiguration {

    @Bean
    public CreateCity createCity(CreateCityPort createCityPort,
            ReadCityPort readCityPort, GetPostalCodeInfoPort getPostalCodeInfoPort) {
        return new CreateCity(createCityPort, readCityPort, getPostalCodeInfoPort);
    }

    @Bean
    public ReadCity readCity(ReadCityPort readCityPort) {
        return new ReadCity(readCityPort);
    }

    @Bean
    public UpdateCity updateCity(UpdateCityPort updateCityPort) {
        return new UpdateCity(updateCityPort);
    }

    @Bean
    public DeleteCity deleteCity(DeleteCityPort deleteCityPort) {
        return new DeleteCity(deleteCityPort);
    }

    @Bean
    public CityController cityController(CreateCity createCity, ReadCity readCity, UpdateCity updateCity, DeleteCity deleteCity) {
        return new CityController(createCity, readCity, updateCity, deleteCity);
    }

}
