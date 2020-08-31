package com.github.paulosalonso.zup.adapter.api.dto.city;

import com.github.paulosalonso.zup.adapter.api.dto.CriteriaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("CitySearch")
public class CityCriteriaDTO extends CriteriaDTO {
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

        private CityCriteriaDTO cityCriteriaDTO;

        private Builder() {
            cityCriteriaDTO = new CityCriteriaDTO();
        }

        public Builder page(int page) {
            cityCriteriaDTO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            cityCriteriaDTO.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            cityCriteriaDTO.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            cityCriteriaDTO.setName(name);
            return this;
        }

        public Builder state(String state) {
            cityCriteriaDTO.setState(state);
            return this;
        }

        public CityCriteriaDTO build() {
            return cityCriteriaDTO;
        }
    }
}