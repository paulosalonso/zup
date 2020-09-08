package com.github.paulosalonso.zup.application.api.v1.controller;

import com.github.paulosalonso.zup.adapter.controller.CityController;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.application.api.v1.mapper.CityDTOMapper;
import com.github.paulosalonso.zup.application.api.v1.model.PageDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityCriteriaDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityUpdateDTO;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.application.api.v1.mapper.CityCriteriaDTOMapper.to;
import static com.github.paulosalonso.zup.application.api.v1.mapper.CityDTOMapper.from;
import static com.github.paulosalonso.zup.application.api.v1.mapper.CityDTOMapper.to;
import static com.github.paulosalonso.zup.application.api.v1.mapper.PageDTOMapper.from;

@Api(tags = "Cities")
@RestController
@RequestMapping("/v1/cities")
public class CityAPIController {

    private CityController delegate;

    public CityAPIController(CityController delegate) {
        this.delegate = delegate;
    }

    @ApiOperation("City search")
    @GetMapping
    public PageDTO<CityResponseDTO> search(CityCriteriaDTO cityCriteriaDTO, Pageable pageable) {
        return from(delegate.search(to(cityCriteriaDTO, pageable)), CityDTOMapper::from);
    }

    @ApiOperation("Create a city")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponseDTO create(@RequestBody @Valid CityCreateDTO cityCreateDTO) {
        try {
            return from(delegate.create(to(cityCreateDTO)));
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Get a city by IBGE code")
    @GetMapping("/{ibgeCode}")
    public CityResponseDTO read(@ApiParam(required = true) @PathVariable String ibgeCode) {
        return from(delegate.read(ibgeCode));
    }

    @ApiOperation("Update a city")
    @PutMapping("/{ibgeCode}")
    public CityResponseDTO update(
            @ApiParam(required = true) @PathVariable String ibgeCode,
            @RequestBody @Valid CityUpdateDTO cityUpdateAdapter) {
        CityResponseAdapter city = delegate.read(ibgeCode);
        return from(delegate.update(ibgeCode, CityDTOMapper.to(cityUpdateAdapter)));
    }

    @ApiOperation("Delete a city by IBGE code")
    @DeleteMapping("/{ibgeCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable String ibgeCode) {
        delegate.delete(ibgeCode);
    }
}
