package com.github.paulosalonso.zup.domain.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Valid
    @NotNull
    @Embedded
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(cpf, customer.cpf) &&
                gender == customer.gender &&
                Objects.equals(birthDate, customer.birthDate) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, gender, birthDate, address);
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
