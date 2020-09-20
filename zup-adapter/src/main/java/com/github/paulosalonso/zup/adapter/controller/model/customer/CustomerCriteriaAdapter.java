package com.github.paulosalonso.zup.adapter.controller.model.customer;

import com.github.paulosalonso.zup.adapter.controller.model.CriteriaAdapter;
import com.github.paulosalonso.zup.domain.Gender;
import com.github.paulosalonso.zup.usecase.Criteria;

import java.time.LocalDate;
import java.util.Map;

public class CustomerCriteriaAdapter extends CriteriaAdapter {

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

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public static final class Builder {

        private final CustomerCriteriaAdapter customerCriteriaAdapter;

        private Builder() {
            customerCriteriaAdapter = new CustomerCriteriaAdapter();
        }

        public Builder page(int page) {
            customerCriteriaAdapter.setPage(page);
            return this;
        }

        public Builder size(int size) {
            customerCriteriaAdapter.setSize(size);
            return this;
        }

        public Builder sort(Map<String, Criteria.SortDirection> sort) {
            customerCriteriaAdapter.setSort(sort);
            return this;
        }

        public Builder name(String name) {
            customerCriteriaAdapter.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerCriteriaAdapter.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerCriteriaAdapter.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerCriteriaAdapter.birthDate = birthDate;
            return this;
        }

        public CustomerCriteriaAdapter build() {
            return customerCriteriaAdapter;
        }

    }
}
