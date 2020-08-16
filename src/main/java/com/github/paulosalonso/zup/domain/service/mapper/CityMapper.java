package com.github.paulosalonso.zup.domain.service.mapper;

import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;

public interface CityMapper {

    static City cityVoToCityEntity(CityVO cityVO) {
        return City.of()
                .ibgeCode(cityVO.getIbgeCode())
                .name(cityVO.getName())
                .state(cityVO.getState())
                .build();
    }

    static CityVO cityEntityToCityVO(City city) {
        return CityVO.of()
                .ibgeCode(city.getIbgeCode())
                .name(city.getName())
                .state(city.getState())
                .build();
    }

    static City cityUpdateVOToCityEntity(CityUpdateVO cityUpdateVO, City city) {
        city.setName(cityUpdateVO.getName());
        city.setState(cityUpdateVO.getState());
        return city;
    }
}
