package com.github.paulosalonso.zup.domain.service.vo.city;

public class CityUpdateVO {
    private String name;
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

        private CityUpdateVO city;

        private Builder() {
            city = new CityUpdateVO();
        }

        public Builder name(String name) {
            city.name = name;
            return this;
        }

        public Builder state(String state) {
            city.state = state;
            return this;
        }

        public CityUpdateVO build() {
            return city;
        }
    }
}
