package com.github.paulosalonso.zup.application.api.v1.model.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("CityUpdate")
public class CityUpdateDTO {

    @ApiModelProperty(example = "São Paulo", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Pattern: [A-Z]{2}", example = "SP", required = true)
    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String state;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static final class Builder {

        private CityUpdateDTO city;

        private Builder() {
            city = new CityUpdateDTO();
        }

        public Builder name(String name) {
            city.name = name;
            return this;
        }

        public Builder state(String state) {
            city.state = state;
            return this;
        }

        public CityUpdateDTO build() {
            return city;
        }
    }
}
