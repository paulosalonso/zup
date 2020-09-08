package com.github.paulosalonso.zup.adapter.repository.mapper;

import com.github.paulosalonso.zup.adapter.repository.model.AddressEmbeddable;
import com.github.paulosalonso.zup.domain.Address;

import java.util.List;
import java.util.stream.Collectors;

public interface AddressEmbeddableMapper {

    static AddressEmbeddable from(Address address) {
        return AddressEmbeddable.of()
                .city(CityEntityMapper.from(address.getCity()))
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .build();
    }

    static Address to(AddressEmbeddable address) {
        return Address.of()
                .city(CityEntityMapper.to(address.getCity()))
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .build();
    }

    static List<Address> toList(List<AddressEmbeddable> addresses) {
        return addresses.stream()
                .map(AddressEmbeddableMapper::to)
                .collect(Collectors.toList());
    }

}
