package com.github.paulosalonso.zup.usecase.port.customer;

import com.github.paulosalonso.zup.domain.Gender;
import com.github.paulosalonso.zup.usecase.Criteria;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CustomerCriteria extends Criteria {

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

        private final CustomerCriteria customerSearchVO;

        private Builder() {
            customerSearchVO = new CustomerCriteria();
        }

        public Builder page(int page) {
            customerSearchVO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            customerSearchVO.setSize(size);
            return this;
        }

        public Builder sort(Map<String, SortDirection> sort) {
            customerSearchVO.setSort(sort);
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

        public CustomerCriteria build() {
            return customerSearchVO;
        }

    }
}
