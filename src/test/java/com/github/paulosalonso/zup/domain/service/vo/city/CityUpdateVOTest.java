package com.github.paulosalonso.zup.domain.service.vo.city;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityUpdateVOTest {

    @Test
    public void whenBuildCityUpdateVOThenSuccess() {
        CityUpdateVO cityUpdateVO = CityUpdateVO.of()
                .name("name")
                .state("state")
                .build();

        assertThat(cityUpdateVO.getName()).isEqualTo("name");
        assertThat(cityUpdateVO.getState()).isEqualTo("state");
    }
}
