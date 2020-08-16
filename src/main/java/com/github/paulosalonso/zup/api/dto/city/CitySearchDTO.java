package com.github.paulosalonso.zup.api.dto.city;

import com.github.paulosalonso.zup.api.dto.SearchDTO;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("CitySearch")
public class CitySearchDTO extends SearchDTO {
    @ApiModelProperty("Returns cities where the name contains this value")
    private String name;

    @ApiModelProperty("Returns cities where the state is equal to this value")
    private String state;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static final class Builder {

        private CitySearchVO citySearchDTO;

        private Builder() {
            citySearchDTO = new CitySearchVO();
        }

        public Builder page(int page) {
            citySearchDTO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            citySearchDTO.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            citySearchDTO.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            citySearchDTO.setName(name);
            return this;
        }

        public Builder state(String state) {
            citySearchDTO.setState(state);
            return this;
        }

        public CitySearchVO build() {
            return citySearchDTO;
        }
    }
}
