package com.github.paulosalonso.zup.api.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("City")
public class CityDTO {

    private String ibgeCode;
    private String name;
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
