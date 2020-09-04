package com.github.paulosalonso.zup.domain;

import java.time.LocalDate;

public class Customer {

    private Long id;
    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public static final class Builder {

        private Customer customer;

        private Builder() {
            customer = new Customer();
        }

        public Builder id(Long id) {
            customer.setId(id);
            return this;
        }

        public Builder name(String name) {
            customer.setName(name);
            return this;
        }

        public Builder cpf(String cpf) {
            customer.setCpf(cpf);
            return this;
        }

        public Builder gender(Gender gender) {
            customer.setGender(gender);
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customer.setBirthDate(birthDate);
            return this;
        }

        public Builder address(Address address) {
            customer.setAddress(address);
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}
