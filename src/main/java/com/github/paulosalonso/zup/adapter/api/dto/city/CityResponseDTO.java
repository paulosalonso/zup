package com.github.paulosalonso.zup.adapter.api.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("City")
public class CityResponseDTO {

    @ApiModelProperty(example = "3550308", required = true)
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

        private CityResponseDTO cityResponseDTO;

        private Builder() {
            cityResponseDTO = new CityResponseDTO();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityResponseDTO.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityResponseDTO.name = name;
            return this;
        }

        public Builder state(String state) {
            cityResponseDTO.state = state;
            return this;
        }

        public CityResponseDTO build() {
            return cityResponseDTO;
        }
    }
}
