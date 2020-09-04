package com.github.paulosalonso.zup.usecase.city;

import com.github.paulosalonso.zup.domain.Address;
import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.mapper.PostalCodeInfoMapper;
import com.github.paulosalonso.zup.usecase.port.city.CreateCityPort;
import com.github.paulosalonso.zup.usecase.port.city.GetPostalCodeInfoPort;
import com.github.paulosalonso.zup.usecase.port.city.ReadCityPort;

public class CreateCity {

    private final CreateCityPort createCityPort;
    private final ReadCityPort readCityPort;
    private final GetPostalCodeInfoPort getPostalCodeInfoPort;

    public CreateCity(CreateCityPort createCityPort, ReadCityPort readCityPort, GetPostalCodeInfoPort getPostalCodeInfoPort) {
        this.createCityPort = createCityPort;
        this.readCityPort = readCityPort;
        this.getPostalCodeInfoPort = getPostalCodeInfoPort;
    }

    public City create(City city) {
        if (readCityPort.existsById(city.getIbgeCode())) {
            throw new CreateException(String.format(
                    "City with ibge code %s already exists.", city.getIbgeCode()));
        }

        return createCityPort.create(city);
    }

    public City createByPostalCode(String postalCode) {
        City city = getPostalCodeInfoPort.getPostalCodeInfo(postalCode)
                .map(PostalCodeInfoMapper::to)
                .orElseThrow(NotFoundException::new);

        return create(city);
    }

    public void resolveCity(Address address) {
        if (address.getCity() != null) {
            if (readCityPort.existsById(address.getCity().getIbgeCode())) {
                return;
            }
        } else {
            readCityPort.findByPostalCode(address.getPostalCode())
                    .ifPresent(city -> address.setCity(city));

            if (address.getCity() != null) {
                return;
            }
        }

        try {
            City city = createByPostalCode(address.getPostalCode());
            address.setCity(city);
        } catch (NotFoundException e) {
            throw new CreateException("Could not resolve the city");
        }
    }
}
