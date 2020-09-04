package com.github.paulosalonso.zup.adapter.api.controller;

import com.github.paulosalonso.zup.adapter.api.dto.PageDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityCriteriaDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityResponseDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityUpdateDTO;
import com.github.paulosalonso.zup.adapter.api.mapper.CityCriteriaDTOMapper;
import com.github.paulosalonso.zup.adapter.api.mapper.CityDTOMapper;
import com.github.paulosalonso.zup.adapter.api.mapper.PageDTOMapper;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.city.CreateCity;
import com.github.paulosalonso.zup.usecase.city.DeleteCity;
import com.github.paulosalonso.zup.usecase.city.ReadCity;
import com.github.paulosalonso.zup.usecase.city.UpdateCity;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.adapter.api.mapper.CityDTOMapper.from;
import static com.github.paulosalonso.zup.adapter.api.mapper.CityDTOMapper.to;

@Api(tags = "Cities")
@RestController
@RequestMapping("/cities")
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

    @ApiOperation("City search")
    @GetMapping
    public PageDTO<CityResponseDTO> search(CityCriteriaDTO cityCriteriaDTO, Pageable pageable) {
        Page<City> page = readCity.findByCriteria(CityCriteriaDTOMapper.to(cityCriteriaDTO, pageable));
        return PageDTOMapper.from(page, CityDTOMapper::from);
    }

    @ApiOperation("Create a city")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponseDTO create(@RequestBody @Valid CityCreateDTO cityCreateDTO) {
        try {
            City city = createCity.create(to(cityCreateDTO));
            return from(city);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Get a city by IBGE code")
    @GetMapping("/{ibgeCode}")
    public CityResponseDTO read(@ApiParam(required = true) @PathVariable String ibgeCode) {
        try {
            City city = readCity.findById(ibgeCode);
            return from(city);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update a city")
    @PutMapping("/{ibgeCode}")
    public CityResponseDTO update(
            @ApiParam(required = true) @PathVariable String ibgeCode,
            @RequestBody @Valid CityUpdateDTO cityUpdateDTO) {

        try {
            City city = readCity.findById(ibgeCode);
            return from(updateCity.update(to(cityUpdateDTO, city)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a city by IBGE code")
    @DeleteMapping("/{ibgeCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable String ibgeCode) {
        try {
            deleteCity.deleteById(ibgeCode);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
