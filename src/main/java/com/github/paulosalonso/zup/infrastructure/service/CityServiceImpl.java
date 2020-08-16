package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.repository.CityRepository;
import com.github.paulosalonso.zup.domain.service.CityService;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.mapper.CityMapper;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.infrastructure.integration.viacep.PostalCodeInfo;
import com.github.paulosalonso.zup.infrastructure.integration.viacep.ViaCEPIntegrationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.paulosalonso.zup.domain.service.mapper.CityMapper.*;
import static com.github.paulosalonso.zup.infrastructure.integration.viacep.ViaCEP.getCepInfo;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageBuilder.buildPageVO;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder.buildPageable;
import static com.github.paulosalonso.zup.infrastructure.repository.specification.CitySpecificationFactory.findByCitySearch;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public PageVO<CityVO> search(CitySearchVO searchCriteria) {
        Page<City> page = cityRepository.findAll(
                findByCitySearch(searchCriteria), buildPageable(searchCriteria));

        return buildPageVO(page, CityMapper::cityEntityToCityVO);
    }

    @Override
    @Transactional
    public CityVO create(CityVO cityVO) {
        if (cityRepository.existsById(cityVO.getIbgeCode())) {
            throw new CreateException(String.format(
                    "City with ibge code %s already exists.", cityVO.getIbgeCode()));
        }

        City city = cityVOToCityEntity(cityVO);
        city = cityRepository.save(city);
        return cityEntityToCityVO(city);
    }

    @Override
    public CityVO read(String id) {
        return cityRepository.findById(id)
                .map(CityMapper::cityEntityToCityVO)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public CityVO update(String ibgeCode, CityUpdateVO cityUpdateVO) {
        City city = cityRepository.findById(ibgeCode)
                .orElseThrow(NotFoundException::new);

        city = cityUpdateVOToCityEntity(cityUpdateVO, city);

        return cityEntityToCityVO(city);
    }

    @Override
    @Transactional
    public void delete(String ibgeCode) {
        if (!cityRepository.existsById(ibgeCode)) {
            throw new NotFoundException();
        }

        cityRepository.deleteById(ibgeCode);
    }

    @Override
    public CityVO createCityByPostalCode(String postalCode) {
        try {
            PostalCodeInfo postalCodeInfo = getCepInfo(postalCode);

            return create(CityVO.of()
                    .ibgeCode(postalCodeInfo.getIbgeCode())
                    .name(postalCodeInfo.getCityName())
                    .state(postalCodeInfo.getStateInitials())
                    .build());
        } catch (ViaCEPIntegrationException e) {
            throw new CreateException("Error creating city by postal code", e);
        }
    }
}
