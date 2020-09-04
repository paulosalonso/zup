package com.github.paulosalonso.zup.usecase.mapper;

import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.domain.PostalCodeInfo;

public interface PostalCodeInfoMapper {

    static City to(PostalCodeInfo postalCodeInfo) {
        return City.of()
                .ibgeCode(postalCodeInfo.getIbgeCode())
                .name(postalCodeInfo.getCityName())
                .state(postalCodeInfo.getStateInitials())
                .build();
    }
}
