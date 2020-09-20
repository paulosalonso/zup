package com.github.paulosalonso.zup.adapter.controller;

import com.github.paulosalonso.zup.adapter.controller.mapper.CityAdapterMapper;
import com.github.paulosalonso.zup.adapter.controller.mapper.PageAdapterMapper;
import com.github.paulosalonso.zup.adapter.controller.model.PageAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityCriteriaAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityUpdateAdapter;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.city.DeleteCity;
import com.github.paulosalonso.zup.usecase.city.ReadCity;
import com.github.paulosalonso.zup.usecase.city.UpdateCity;

import static com.github.paulosalonso.zup.adapter.controller.mapper.CityAdapterMapper.from;
import static com.github.paulosalonso.zup.adapter.controller.mapper.CityAdapterMapper.to;
import static com.github.paulosalonso.zup.adapter.controller.mapper.CityCriteriaAdapterMapper.to;

public class CityController {

    private final CreateCity createCity;
    private final ReadCity readCity;
    private final UpdateCity updateCity;
    private final DeleteCity deleteCity;

    public CityController(CreateCity createCity, ReadCity readCity, UpdateCity updateCity, DeleteCity deleteCity) {
        this.createCity = createCity;
        this.readCity = readCity;
        this.updateCity = updateCity;
        this.deleteCity = deleteCity;
    }

    public PageAdapter<CityResponseAdapter> search(CityCriteriaAdapter cityCriteriaAdapter) {
        Page<City> page = readCity.findByCriteria(to(cityCriteriaAdapter));
        return PageAdapterMapper.from(page, CityAdapterMapper::from);
    }

    public CityResponseAdapter create(CityCreateAdapter cityCreateAdapter) {
        City city = createCity.create(to(cityCreateAdapter));
        return from(city);
    }

    public CityResponseAdapter read(String ibgeCode) {
        City city = readCity.findById(ibgeCode);
        return from(city);
    }

    public CityResponseAdapter update(String ibgeCode, CityUpdateAdapter cityUpdateAdapter) {
        City city = readCity.findById(ibgeCode);
        return from(updateCity.update(to(cityUpdateAdapter, city)));
    }

    public void delete(String ibgeCode) {
        deleteCity.deleteById(ibgeCode);
    }
}
