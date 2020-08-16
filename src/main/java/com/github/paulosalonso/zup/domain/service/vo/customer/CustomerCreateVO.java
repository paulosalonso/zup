package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.model.Gender;

import java.time.LocalDate;

public class CustomerCreateVO {

    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private AddressVO address;

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

    public AddressVO getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerCreateVO customerCreateVO;

        private Builder() {
            customerCreateVO = new CustomerCreateVO();
        }

        public Builder name(String name) {
            customerCreateVO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerCreateVO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerCreateVO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerCreateVO.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressVO address) {
            customerCreateVO.address = address;
            return this;
        }

        public CustomerCreateVO build() {
            return customerCreateVO;
        }

    }
}
