package com.github.paulosalonso.zup.usecase.port.city;

import com.github.paulosalonso.zup.domain.PostalCodeInfo;

import java.util.Optional;

public interface GetPostalCodeInfoPort {
    Optional<PostalCodeInfo> getPostalCodeInfo(String postalCode);
}
