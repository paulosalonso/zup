package com.github.paulosalonso.zup.application.api.v1.model.customer;

import com.github.paulosalonso.zup.domain.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel("CustomerCreate")
public class CustomerCreateDTO {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "00000000000000", required = true)
    @NotBlank
    private String cpf;

    @ApiModelProperty(example = "MALE", required = true)
    @NotNull
    private Gender gender;

    @ApiModelProperty(example = "1988-02-26")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Valid
    private AddressDTO address;

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

    public AddressDTO getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerCreateDTO customerCreateDTO;

        private Builder() {
            customerCreateDTO = new CustomerCreateDTO();
        }

        public Builder name(String name) {
            customerCreateDTO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerCreateDTO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerCreateDTO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerCreateDTO.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressDTO address) {
            customerCreateDTO.address = address;
            return this;
        }

        public CustomerCreateDTO build() {
            return customerCreateDTO;
        }

    }
}
