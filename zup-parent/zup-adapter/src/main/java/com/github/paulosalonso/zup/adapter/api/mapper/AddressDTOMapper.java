package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityResponseDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.AddressDTO;
import com.github.paulosalonso.zup.domain.Address;
import com.github.paulosalonso.zup.domain.City;

public interface AddressDTOMapper {

    static Address to(AddressDTO dto) {
        City city = null;

        if (dto.getCity() != null) {
            city = CityDTOMapper.to(dto.getCity());
        }

        return Address.of()
                .city(city)
                .street(dto.getStreet())
                .number(dto.getNumber())
                .complement(dto.getComplement())
                .district(dto.getDistrict())
                .postalCode(dto.getPostalCode())
                .build();
    }

    static AddressDTO from(Address address) {
        CityResponseDTO city = null;

        if (address.getCity() != null) {
            city = CityDTOMapper.from(address.getCity());
        }

        return AddressDTO.of()
                .city(city)
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .build();
    }
}
