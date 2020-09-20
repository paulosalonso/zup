package com.github.paulosalonso.zup.adapter.controller.model.city;

public class CityResponseAdapter {

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

        private CityResponseAdapter cityResponseAdapter;

        private Builder() {
            cityResponseAdapter = new CityResponseAdapter();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityResponseAdapter.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityResponseAdapter.name = name;
            return this;
        }

        public Builder state(String state) {
            cityResponseAdapter.state = state;
            return this;
        }

        public CityResponseAdapter build() {
            return cityResponseAdapter;
        }
    }
}
