package com.github.paulosalonso.zup.domain.service.vo.city;

import com.github.paulosalonso.zup.domain.service.vo.SearchVO;

import java.util.List;

public class CitySearchVO extends SearchVO {
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

        private CitySearchVO citySearchVO;

        private Builder() {
            citySearchVO = new CitySearchVO();
        }

        public Builder page(int page) {
            citySearchVO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            citySearchVO.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            citySearchVO.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            citySearchVO.setName(name);
            return this;
        }

        public Builder state(String state) {
            citySearchVO.setState(state);
            return this;
        }

        public CitySearchVO build() {
            return citySearchVO;
        }
    }
}
