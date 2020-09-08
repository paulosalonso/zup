package com.github.paulosalonso.zup.adapter.controller.model.customer;

import com.github.paulosalonso.zup.domain.Gender;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CustomerCreateAdapter {

    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private AddressAdapter address;

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

    public AddressAdapter getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerCreateAdapter customerCreateAdapter;

        private Builder() {
            customerCreateAdapter = new CustomerCreateAdapter();
        }

        public Builder name(String name) {
            customerCreateAdapter.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerCreateAdapter.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerCreateAdapter.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerCreateAdapter.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressAdapter address) {
            customerCreateAdapter.address = address;
            return this;
        }

        public CustomerCreateAdapter build() {
            return customerCreateAdapter;
        }

    }
}
