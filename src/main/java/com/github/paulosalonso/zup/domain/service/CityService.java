package com.github.paulosalonso.zup.domain.service;

import com.github.paulosalonso.zup.domain.service.crud.*;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;

public interface CityService extends SearchService<CityVO, CitySearchVO>, CreateService<CityVO, CityVO>,
        ReadService<CityVO, String>, UpdateService<String, CityUpdateVO, CityVO>, DeleteService<String> {

    CityVO findCityByPostalCode(String postalCode);
    CityVO createCityByPostalCode(String postalCode);
}
