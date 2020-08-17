package com.github.paulosalonso.zup.api.mapper;

import com.github.paulosalonso.zup.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.api.dto.city.CityDTO;
import com.github.paulosalonso.zup.api.dto.city.CitySearchDTO;
import com.github.paulosalonso.zup.api.dto.city.CityUpdateDTO;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;

public interface CityMapper {

    static CitySearchVO citySearchDTOToCitySearchVO(CitySearchDTO citySearchDTO) {
        return CitySearchVO.of()
                .page(citySearchDTO.getPage())
                .size(citySearchDTO.getSize())
                .order(citySearchDTO.getOrder())
                .name(citySearchDTO.getName())
                .state(citySearchDTO.getState())
                .build();
    }

    static CityDTO cityVOToCityDTO(CityVO cityVO) {
        return CityDTO.of()
                .ibgeCode(cityVO.getIbgeCode())
                .name(cityVO.getName())
                .state(cityVO.getState())
                .build();
    }

    static CityVO cityDTOToCityVO(CityDTO cityDTO) {
        return CityVO.of()
                .ibgeCode(cityDTO.getIbgeCode())
                .name(cityDTO.getName())
                .state(cityDTO.getState())
                .build();
    }

    static CityVO cityCreateDTOToCityVO(CityCreateDTO cityCreateDTO) {
        return CityVO.of()
                .ibgeCode(cityCreateDTO.getIbgeCode())
                .name(cityCreateDTO.getName())
                .state(cityCreateDTO.getState())
                .build();
    }

    static CityUpdateVO cityCreateDTOToCityUpdateVO(CityUpdateDTO cityUpdateDTO) {
        return CityUpdateVO.of()
                .name(cityUpdateDTO.getName())
                .state(cityUpdateDTO.getState())
                .build();
    }
}
