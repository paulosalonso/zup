package com.github.paulosalonso.zup.api.dto.customer;

import com.github.paulosalonso.zup.domain.model.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("CustomerUpdate")
public class CustomerUpdateDTO {

    @ApiModelProperty(example = "João da Silva")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "MALE")
    @NotNull
    private Gender gender;

    @Valid
    private AddressDTO address;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerUpdateDTO customerUpdateDTO;

        private Builder() {
            customerUpdateDTO = new CustomerUpdateDTO();
        }

        public Builder name(String name) {
            customerUpdateDTO.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            customerUpdateDTO.gender = gender;
            return this;
        }

        public Builder address(AddressDTO address) {
            customerUpdateDTO.address = address;
            return this;
        }

        public CustomerUpdateDTO build() {
            return customerUpdateDTO;
        }

    }
}
