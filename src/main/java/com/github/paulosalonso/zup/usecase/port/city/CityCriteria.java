package com.github.paulosalonso.zup.usecase.port.city;

import com.github.paulosalonso.zup.usecase.Criteria;

import java.util.List;

public class CityCriteria extends Criteria {
    private String name;
    private String state;

    public static Builder of() {
        return new Builder();
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

        private CityCriteria cityCriteria;

        private Builder() {
            cityCriteria = new CityCriteria();
        }

        public Builder page(int page) {
            cityCriteria.setPage(page);
            return this;
        }

        public Builder size(int size) {
            cityCriteria.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            cityCriteria.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            cityCriteria.setName(name);
            return this;
        }

        public Builder state(String state) {
            cityCriteria.setState(state);
            return this;
        }

        public CityCriteria build() {
            return cityCriteria;
        }
    }
}
