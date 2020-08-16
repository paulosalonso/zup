package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.model.Gender;
import com.github.paulosalonso.zup.domain.service.vo.SearchVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;

import java.time.LocalDate;
import java.util.List;

public class CustomerSearchVO extends SearchVO {

    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public static final class Builder {

        private final CustomerSearchVO customerSearchVO;

        private Builder() {
            customerSearchVO = new CustomerSearchVO();
        }

        public Builder page(int page) {
            customerSearchVO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            customerSearchVO.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            customerSearchVO.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            customerSearchVO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerSearchVO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerSearchVO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerSearchVO.birthDate = birthDate;
            return this;
        }

        public CustomerSearchVO build() {
            return customerSearchVO;
        }

    }
}
