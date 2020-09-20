package com.github.paulosalonso.zup.adapter.repository;

import com.github.paulosalonso.zup.adapter.repository.mapper.CityEntityMapper;
import com.github.paulosalonso.zup.adapter.repository.mapper.PageMapper;
import com.github.paulosalonso.zup.adapter.repository.mapper.PageableMapper;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.city.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.paulosalonso.zup.adapter.repository.mapper.CityEntityMapper.*;
import static com.github.paulosalonso.zup.adapter.repository.specification.CitySpecificationFactory.findByCityCriteria;

@Repository
public class CityRepositoryGateway implements ReadCityPort, CreateCityPort, DeleteCityPort, UpdateCityPort {

    private final CityRepository repository;
    private final GetPostalCodeInfoPort getPostalCodeInfoPort;

    public CityRepositoryGateway(CityRepository repository, GetPostalCodeInfoPort getPostalCodeInfoPort) {
        this.repository = repository;
        this.getPostalCodeInfoPort = getPostalCodeInfoPort;
    }

    @Override
    public City create(City city) {
        return to(repository.save(from(city)));
    }

    @Override
    public void delete(City city) {
        repository.delete(from(city));
    }

    @Override
    public void deleteById(String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByPostalCode(String postalCode) {
        return getPostalCodeInfoPort.getPostalCodeInfo(postalCode)
                .map(info -> true)
                .orElse(false);
    }

    @Override
    public Optional<City> findById(String id) {
        return repository.findById(id).map(CityEntityMapper::to);
    }

    @Override
    public Optional<City> findByPostalCode(String postalCode) {
        return getPostalCodeInfoPort.getPostalCodeInfo(postalCode)
                .flatMap(postalCodeInfo -> repository.findById(postalCodeInfo.getIbgeCode()))
                .map(CityEntityMapper::to);
    }

    @Override
    public List<City> findAll() {
        return toList(repository.findAll());
    }

    @Override
    public Page<City> findByCriteria(CityCriteria criteria) {
        return PageMapper.to(repository.findAll(findByCityCriteria(criteria),
                PageableMapper.from(criteria)), CityEntityMapper::toList);
    }

    @Override
    public City update(City city) {
        return to(repository.save(from(city)));
    }
}
