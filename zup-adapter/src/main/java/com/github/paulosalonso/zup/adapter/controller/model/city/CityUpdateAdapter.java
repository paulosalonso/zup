package com.github.paulosalonso.zup.adapter.controller.model.city;

public class CityUpdateAdapter {

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

        private CityUpdateAdapter city;

        private Builder() {
            city = new CityUpdateAdapter();
        }

        public Builder name(String name) {
            city.name = name;
            return this;
        }

        public Builder state(String state) {
            city.state = state;
            return this;
        }

        public CityUpdateAdapter build() {
            return city;
        }
    }
}
