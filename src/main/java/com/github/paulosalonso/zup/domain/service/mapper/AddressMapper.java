package com.github.paulosalonso.zup.domain.service.mapper;

import com.github.paulosalonso.zup.domain.model.Address;
import com.github.paulosalonso.zup.domain.service.vo.customer.AddressVO;

import static com.github.paulosalonso.zup.domain.service.mapper.CityMapper.cityEntityToCityVO;
import static com.github.paulosalonso.zup.domain.service.mapper.CityMapper.cityVOToCityEntity;

public interface AddressMapper {

    static AddressVO addressEntityToAddressVO(Address address) {
        return AddressVO.of()
                .city(cityEntityToCityVO(address.getCity()))
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .build();
    }

    static Address addressVOToAddressEntity(AddressVO addressVO) {
        return Address.of()
                .city(cityVOToCityEntity(addressVO.getCity()))
                .street(addressVO.getStreet())
                .number(addressVO.getNumber())
                .complement(addressVO.getComplement())
                .district(addressVO.getDistrict())
                .postalCode(addressVO.getPostalCode())
                .build();
    }
}
