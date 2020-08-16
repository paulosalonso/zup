package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.repository.CityRepository;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder.buildPageable;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Test
    public void whenSearchThenReturnCities() {
        CitySearchVO citySearchVO = CitySearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name"))
                .build();

        List<City> cities = List.of(buildCity("-a"), buildCity("-b"));

        Pageable pageable = buildPageable(citySearchVO);

        when(cityRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(new PageImpl<>(cities));

        PageVO<CityVO> cityVOPage = cityService.search(citySearchVO);

        assertThat(cityVOPage.getPage()).isEqualTo(0);
        assertThat(cityVOPage.getPageSize()).isEqualTo(2);
        assertThat(cityVOPage.getTotalPages()).isEqualTo(1);
        assertThat(cityVOPage.getTotalSize()).isEqualTo(2);
        assertThat(cityVOPage.getContent())
                .hasSize(2)
                .satisfies(citiesVOs -> {
                    assertCityVO(citiesVOs.get(0), "-a");
                    assertCityVO(citiesVOs.get(1), "-b");
                });

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(cityRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getPageNumber()).isEqualTo(1);
        assertThat(pageableArgument.getPageSize()).isEqualTo(20);
        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.ASC));
    }

    @Test
    public void whenSearchWithDescOrderThenUseDirectionDesc() {
        CitySearchVO citySearchVO = CitySearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name,desc"))
                .build();

        when(cityRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(emptyList()));

        PageVO<CityVO> cityVOPage = cityService.search(citySearchVO);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(cityRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.DESC));
    }

    @Test
    public void whenSearchWithInvalidOrderThenUseDirectionAsc() {
        CitySearchVO citySearchVO = CitySearchVO.of()
                .page(1)
                .size(20)
                .order(List.of("name,xxx"))
                .build();

        when(cityRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(emptyList()));

        PageVO<CityVO> cityVOPage = cityService.search(citySearchVO);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(cityRepository).findAll(any(Specification.class), pageableCaptor.capture());
        Pageable pageableArgument = pageableCaptor.getValue();

        assertThat(pageableArgument.getSort().getOrderFor("name"))
                .isNotNull()
                .satisfies(order -> assertThat(order.getDirection()).isEqualTo(Direction.ASC));
    }

    @Test
    public void whenCreateThenReturnCity() {
        CityVO cityToCreate = buildCityVO("");
        City createdCity = buildCity("");

        when(cityRepository.save(createdCity)).thenReturn(createdCity);

        CityVO cityVO = cityService.create(cityToCreate);

        assertCityVO(cityVO, "");

        ArgumentCaptor<City> cityCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository).save(cityCaptor.capture());
        City cityArgument = cityCaptor.getValue();
        assertThat(cityArgument).isEqualTo(createdCity);
    }

    @Test
    public void whenReadThenReturnCity() {
        when(cityRepository.findById("ibge-code"))
                .thenReturn(Optional.of(buildCity("")));

        assertCityVO(cityService.read("ibge-code"), "");

        verify(cityRepository).findById("ibge-code");
    }

    @Test
    public void whenReadThenThrowsNotFoundException() {
        when(cityRepository.findById("ibge-code"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> cityService.read("ibge-code"))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(cityRepository).findById("ibge-code");
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void whenUpdateThenReturnCity() {
        when(cityRepository.findById("ibge-code"))
                .thenReturn(Optional.of(buildCity("")));

        CityUpdateVO cityToUpdate = CityUpdateVO.of()
                .name("name-updated")
                .state("state-updated")
                .build();

        CityVO updatedCity = cityService.update("ibge-code", cityToUpdate);
        assertThat(updatedCity.getIbgeCode()).isEqualTo("ibge-code");
        assertThat(updatedCity.getName()).isEqualTo("name-updated");
        assertThat(updatedCity.getState()).isEqualTo("state-updated");

        verify(cityRepository).findById("ibge-code");
    }

    @Test
    public void whenUpdateThenThrowsNotFoundException() {
        when(cityRepository.findById("ibge-code"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> cityService.update("ibge-code", CityUpdateVO.of().build()))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(cityRepository).findById("ibge-code");
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void whenDeleteThenSuccess() {
        when(cityRepository.existsById("ibge-code")).thenReturn(true);

        cityService.delete("ibge-code");

        verify(cityRepository).existsById("ibge-code");
        verify(cityRepository).deleteById("ibge-code");
    }

    @Test
    public void whenDeleteThenThrowsNotFountException() {
        when(cityRepository.existsById("ibge-code")).thenReturn(false);

        assertThatThrownBy(() -> cityService.delete("ibge-code"))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(cityRepository).existsById("ibge-code");
        verifyNoMoreInteractions(cityRepository);
    }



    private CityVO buildCityVO(String suffix) {
        return CityVO.of()
                .ibgeCode("ibge-code" + suffix)
                .name("name" + suffix)
                .state("state" + suffix)
                .build();
    }

    private City buildCity(String suffix) {
        return City.of()
                .ibgeCode("ibge-code" + suffix)
                .name("name" + suffix)
                .state("state" + suffix)
                .build();
    }

    private void assertCityVO(CityVO cityVO, String suffix) {
        assertThat(cityVO.getIbgeCode()).isEqualTo("ibge-code" + suffix);
        assertThat(cityVO.getName()).isEqualTo("name" + suffix);
        assertThat(cityVO.getState()).isEqualTo("state" + suffix);
    }

}
