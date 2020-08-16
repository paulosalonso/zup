package com.github.paulosalonso.zup.domain.service.vo.city;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CitySearchVOTest {

    @Test
    public void whenBuildCitySearchVOThenSuccess() {
        List<String> order = List.of("name", "state");

        CitySearchVO citySearchVO = CitySearchVO.of()
                .page(1)
                .size(100)
                .order(order)
                .name("name")
                .state("state")
                .build();

        assertThat(citySearchVO.getPage()).isEqualTo(1);
        assertThat(citySearchVO.getSize()).isEqualTo(100);
        assertThat(citySearchVO.getOrder()).isEqualTo(order);
        assertThat(citySearchVO.getName()).isEqualTo("name");
        assertThat(citySearchVO.getState()).isEqualTo("state");
    }
}
