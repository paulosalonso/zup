package com.github.paulosalonso.zup.adapter.controller.model.city;

public class CityCreateAdapter {

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

        private CityCreateAdapter cityCreateAdapter;

        private Builder() {
            cityCreateAdapter = new CityCreateAdapter();
        }

        public Builder ibgeCode(String ibgeCode) {
            cityCreateAdapter.ibgeCode = ibgeCode;
            return this;
        }

        public Builder name(String name) {
            cityCreateAdapter.name = name;
            return this;
        }

        public Builder state(String state) {
            cityCreateAdapter.state = state;
            return this;
        }

        public CityCreateAdapter build() {
            return cityCreateAdapter;
        }
    }
}
