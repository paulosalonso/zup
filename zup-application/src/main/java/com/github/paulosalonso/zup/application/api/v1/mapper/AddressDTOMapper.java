package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.AddressAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.city.CityResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.AddressDTO;

public interface AddressDTOMapper {

    static AddressAdapter to(AddressDTO dto) {
        CityResponseAdapter city = null;

        if (dto.getCity() != null) {
            city = CityDTOMapper.to(dto.getCity());
        }

        return AddressAdapter.of()
                .city(city)
                .street(dto.getStreet())
                .number(dto.getNumber())
                .complement(dto.getComplement())
                .district(dto.getDistrict())
                .postalCode(dto.getPostalCode())
                .build();
    }

    static AddressDTO from(AddressAdapter address) {
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
