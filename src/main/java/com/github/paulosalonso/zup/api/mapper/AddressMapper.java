package com.github.paulosalonso.zup.api.mapper;

import com.github.paulosalonso.zup.api.dto.city.CityDTO;
import com.github.paulosalonso.zup.api.dto.customer.AddressDTO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.AddressVO;

import static com.github.paulosalonso.zup.api.mapper.CityMapper.cityDTOToCityVO;
import static com.github.paulosalonso.zup.api.mapper.CityMapper.cityVOToCityDTO;

public interface AddressMapper {

    static AddressDTO addressVOToAddressDTO(AddressVO addressVO) {
        CityDTO cityDTO = null;

        if (addressVO.getCity() != null) {
            cityDTO = cityVOToCityDTO(addressVO.getCity());
        }

        return AddressDTO.of()
                .city(cityDTO)
                .street(addressVO.getStreet())
                .number(addressVO.getNumber())
                .complement(addressVO.getComplement())
                .district(addressVO.getDistrict())
                .postalCode(addressVO.getPostalCode())
                .build();
    }

    static AddressVO addressDTOToAddressVO(AddressDTO addressDTO) {
        CityVO city = null;

        if (addressDTO.getCity() != null) {
            city = cityDTOToCityVO(addressDTO.getCity());
        }

        return AddressVO.of()
                .city(city)
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .district(addressDTO.getDistrict())
                .postalCode(addressDTO.getPostalCode())
                .build();
    }
}
