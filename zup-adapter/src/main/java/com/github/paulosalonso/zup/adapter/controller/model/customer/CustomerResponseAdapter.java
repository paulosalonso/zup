package com.github.paulosalonso.zup.adapter.controller.model.customer;

import com.github.paulosalonso.zup.domain.Gender;
//import io.swagger.annotations.ApiModel;

import java.time.LocalDate;

public class CustomerResponseAdapter {

    private Long id;
    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private AddressAdapter address;

    public static Builder of() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddressAdapter getAddress() {
        return address;
    }

    public void setAddress(AddressAdapter address) {
        this.address = address;
    }

    public static final class Builder {

        private final CustomerResponseAdapter customerResponseAdapter;

        private Builder() {
            customerResponseAdapter = new CustomerResponseAdapter();
        }

        public Builder id(Long id) {
            customerResponseAdapter.id = id;
            return this;
        }

        public Builder name(String name) {
            customerResponseAdapter.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerResponseAdapter.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerResponseAdapter.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerResponseAdapter.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressAdapter address) {
            customerResponseAdapter.address = address;
            return this;
        }

        public CustomerResponseAdapter build() {
            return customerResponseAdapter;
        }

    }
}
