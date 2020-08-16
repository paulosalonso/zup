package com.github.paulosalonso.zup.domain.service.vo.city;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityVOTest {

    @Test
    public void whenCreateCityVOThenSuccess() {
        CityVO cityVO = CityVO.of()
                .ibgeCode("ibge-code")
                .name("name")
                .state("state")
                .build();

        assertThat(cityVO.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(cityVO.getName()).isEqualTo("name");
        assertThat(cityVO.getState()).isEqualTo("state");
    }

}
