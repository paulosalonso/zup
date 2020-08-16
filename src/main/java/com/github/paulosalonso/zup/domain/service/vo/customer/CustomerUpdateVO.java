package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.model.Gender;

public class CustomerUpdateVO {

    private String name;
    private Gender gender;
    private AddressVO address;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public AddressVO getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerUpdateVO customerUpdateVO;

        private Builder() {
            customerUpdateVO = new CustomerUpdateVO();
        }

        public Builder name(String name) {
            customerUpdateVO.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            customerUpdateVO.gender = gender;
            return this;
        }

        public Builder address(AddressVO address) {
            customerUpdateVO.address = address;
            return this;
        }

        public CustomerUpdateVO build() {
            return customerUpdateVO;
        }

    }
}
