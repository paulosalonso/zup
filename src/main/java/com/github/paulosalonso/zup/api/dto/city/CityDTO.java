package com.github.paulosalonso.zup.api.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("City")
public class CityDTO {

    @ApiModelProperty(example = "3550308")
    private String ibgeCode;

    @ApiModelProperty(example = "São Paulo")
    private String name;

    @ApiModelProperty(example = "SP")
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

        private CityDTO cityDTO;

        private Builder() {
            cityDTO = new CityDTO();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityDTO.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityDTO.name = name;
            return this;
        }

        public Builder state(String state) {
            cityDTO.state = state;
            return this;
        }

        public CityDTO build() {
            return cityDTO;
        }
    }
}
