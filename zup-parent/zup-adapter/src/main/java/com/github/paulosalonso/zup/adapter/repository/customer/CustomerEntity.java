package com.github.paulosalonso.zup.adapter.repository.customer;

import com.github.paulosalonso.zup.domain.Gender;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "Customer")
@Table(name = "customer")
public class CustomerEntity {

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
    private AddressEmbeddable address;

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

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }
    
    public static final class Builder {

        private CustomerEntity customer;

        private Builder() {
            customer = new CustomerEntity();
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

        public Builder address(AddressEmbeddable address) {
            customer.setAddress(address);
            return this;
        }

        public CustomerEntity build() {
            return customer;
        }
    }
}
