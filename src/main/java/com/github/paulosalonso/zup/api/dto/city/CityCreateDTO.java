package com.github.paulosalonso.zup.api.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("CityCreate")
public class CityCreateDTO {

    @ApiModelProperty(value = "Pattern: [0-9]{7}", example = "3550308", required = true)
    @NotNull
    @Pattern(regexp = "[0-9]{7}")
    private String ibgeCode;

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

    public String getIbgeCode() {
        return ibgeCode;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static final class Builder {

        private CityCreateDTO cityCreateDTO;

        private Builder() {
            cityCreateDTO = new CityCreateDTO();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityCreateDTO.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityCreateDTO.name = name;
            return this;
        }

        public Builder state(String state) {
            cityCreateDTO.state = state;
            return this;
        }

        public CityCreateDTO build() {
            return cityCreateDTO;
        }
    }
}
