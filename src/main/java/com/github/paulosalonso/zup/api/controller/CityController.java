package com.github.paulosalonso.zup.api.controller;

import com.github.paulosalonso.zup.api.dto.PageDTO;
import com.github.paulosalonso.zup.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.api.dto.city.CityDTO;
import com.github.paulosalonso.zup.api.dto.city.CitySearchDTO;
import com.github.paulosalonso.zup.api.dto.city.CityUpdateDTO;
import com.github.paulosalonso.zup.api.mapper.CityMapper;
import com.github.paulosalonso.zup.domain.service.CityService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.api.dto.page.PageBuilder.buildPageDTO;
import static com.github.paulosalonso.zup.api.mapper.CityMapper.*;

@Api(tags = "Cities")
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation("City search")
    @GetMapping
    public PageDTO<CityDTO> search(CitySearchDTO citySearchDTO) {
        PageVO<CityVO> page = cityService.search(CityMapper.citySearchDTOToCitySearchVO(citySearchDTO));
        return buildPageDTO(page, CityMapper::cityVOToCityDTO);
    }

    @ApiOperation("Create a city")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO create(@RequestBody @Valid CityCreateDTO cityCreateDTO) {
        try {
            CityVO cityVO = cityService.create(cityCreateDTOToCityVO(cityCreateDTO));
            return cityVOToCityDTO(cityVO);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Get a city by IBGE code")
    @GetMapping("/{ibgeCode}")
    public CityDTO read(@ApiParam(required = true) @PathVariable String ibgeCode) {
        try {
            CityVO city = cityService.read(ibgeCode);
            return cityVOToCityDTO(city);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update a city")
    @PutMapping("/{ibgeCode}")
    public CityDTO update(
            @ApiParam(required = true) @PathVariable String ibgeCode,
            @RequestBody @Valid CityUpdateDTO cityUpdateDTO) {

        try {
            CityVO city = cityService.update(ibgeCode, cityCreateDTOToCityUpdateVO(cityUpdateDTO));
            return cityVOToCityDTO(city);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a city by IBGE code")
    @DeleteMapping("/{ibgeCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable String ibgeCode) {
        try {
            cityService.delete(ibgeCode);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
