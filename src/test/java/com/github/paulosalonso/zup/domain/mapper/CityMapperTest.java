package com.github.paulosalonso.zup.domain.mapper;

import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import org.junit.jupiter.api.Test;

import static com.github.paulosalonso.zup.domain.service.mapper.CityMapper.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CityMapperTest {

    @Test
    public void whenMapCityVOToCityEntityThenSuccess() {
        CityVO cityVO = CityVO.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build();

        City city = cityVOToCityEntity(cityVO);

        assertThat(city.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(city.getName()).isEqualTo("name");
        assertThat(city.getState()).isEqualTo("state");
    }

    @Test
    public void whenMapCityEntityToCityVOThenSuccess() {
        City city = City.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build();

        CityVO cityVO = cityEntityToCityVO(city);

        assertThat(cityVO.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(cityVO.getName()).isEqualTo("name");
        assertThat(cityVO.getState()).isEqualTo("state");
    }

    @Test
    public void whenMapCityUpdateVOToCityEntityThenSuccess() {
        City city = City.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build();

        CityUpdateVO cityUpdateVO = CityUpdateVO.of()
                .name("name-updated")
                .state("state-updated")
                .build();

        cityUpdateVOToCityEntity(cityUpdateVO, city);

        assertThat(city.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(city.getName()).isEqualTo("name-updated");
        assertThat(city.getState()).isEqualTo("state-updated");
    }
}
