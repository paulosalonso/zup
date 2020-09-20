package com.github.paulosalonso.zup.domain;

public class City {

    private String ibgeCode;
    private String name;
    private String state;

    public static Builder of() {
        return new Builder();
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public static final class Builder {

        private City city;

        private Builder() {
            city = new City();
        }

        public Builder ibgeCode(String ibgeCode) {
            city.setIbgeCode(ibgeCode);
            return this;
        }

        public Builder name(String name) {
            city.setName(name);
            return this;
        }

        public Builder state(String state) {
            city.setState(state);
            return this;
        }

        public City build() {
            return city;
        }
    }
}
