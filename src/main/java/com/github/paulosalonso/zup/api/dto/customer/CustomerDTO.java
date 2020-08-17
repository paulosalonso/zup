package com.github.paulosalonso.zup.api.dto.customer;

import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.model.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel("Customer")
public class CustomerDTO {

    private Long id;
    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private AddressDTO address;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public static final class Builder {

        private final CustomerDTO customerDTO;

        private Builder() {
            customerDTO = new CustomerDTO();
        }

        public Builder id(Long id) {
            customerDTO.id = id;
            return this;
        }

        public Builder name(String name) {
            customerDTO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerDTO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerDTO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerDTO.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressDTO address) {
            customerDTO.address = address;
            return this;
        }

        public CustomerDTO build() {
            return customerDTO;
        }

    }
}
