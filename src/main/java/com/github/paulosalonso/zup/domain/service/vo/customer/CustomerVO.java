package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.model.Gender;

import java.time.LocalDate;

public class CustomerVO {

    private Long id;
    private String name;
    private String cpf;
    private Gender gender;
    private LocalDate birthDate;
    private AddressVO address;

    public static Builder of() {
        return new Builder();
    }

    public Long getId() {
        return id;
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

        private final CustomerVO customerVO;

        private Builder() {
            customerVO = new CustomerVO();
        }

        public Builder id(Long id) {
            customerVO.id = id;
            return this;
        }

        public Builder name(String name) {
            customerVO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerVO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerVO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerVO.birthDate = birthDate;
            return this;
        }

        public Builder address(AddressVO address) {
            customerVO.address = address;
            return this;
        }

        public CustomerVO build() {
            return customerVO;
        }

    }
}
