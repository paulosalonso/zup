package com.github.paulosalonso.zup.adapter.controller.model.city;

import com.github.paulosalonso.zup.usecase.Criteria;

import java.util.Map;

public class CityCriteriaAdapter extends Criteria {

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

        private CityCriteriaAdapter cityCriteriaAdapter;

        private Builder() {
            cityCriteriaAdapter = new CityCriteriaAdapter();
        }

        public Builder page(int page) {
            cityCriteriaAdapter.setPage(page);
            return this;
        }

        public Builder size(int size) {
            cityCriteriaAdapter.setSize(size);
            return this;
        }

        public Builder sort(Map<String, SortDirection> sort) {
            cityCriteriaAdapter.setSort(sort);
            return this;
        }

        public Builder name(String name) {
            cityCriteriaAdapter.setName(name);
            return this;
        }

        public Builder state(String state) {
            cityCriteriaAdapter.setState(state);
            return this;
        }

        public CityCriteriaAdapter build() {
            return cityCriteriaAdapter;
        }
    }
}
