package com.github.paulosalonso.zup.domain.service.vo.city;

public class CityVO {
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

        private CityVO cityVO;

        private Builder() {
            cityVO = new CityVO();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityVO.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityVO.name = name;
            return this;
        }

        public Builder state(String state) {
            cityVO.state = state;
            return this;
        }

        public CityVO build() {
            return cityVO;
        }
    }
}
