package com.github.paulosalonso.zup.application.api.v1.model.customer;

import com.github.paulosalonso.zup.domain.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel("Customer")
public class CustomerResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "João da Silva")
    private String name;

    @ApiModelProperty(example = "00000000000000")
    private String cpf;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

    @ApiModelProperty(example = "1988-02-26")
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

        private final CustomerResponseDTO customerResponseDTO;

        private Builder() {
            customerResponseDTO = new CustomerResponseDTO();
        }

        public Builder id(Long id) {
            customerResponseDTO.id = id;
            return this;
        }

        public Builder name(String name) {
            customerResponseDTO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerResponseDTO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerResponseDTO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerResponseDTO.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressDTO address) {
            customerResponseDTO.address = address;
            return this;
        }

        public CustomerResponseDTO build() {
            return customerResponseDTO;
        }

    }
}
