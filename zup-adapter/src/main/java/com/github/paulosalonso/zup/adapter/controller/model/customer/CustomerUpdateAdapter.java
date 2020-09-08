package com.github.paulosalonso.zup.adapter.controller.model.customer;

import com.github.paulosalonso.zup.domain.Gender;

public class CustomerUpdateAdapter {

    private String name;
    private Gender gender;
    private AddressAdapter address;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public AddressAdapter getAddress() {
        return address;
    }

    public static final class Builder {

        private final CustomerUpdateAdapter customerUpdateAdapter;

        private Builder() {
            customerUpdateAdapter = new CustomerUpdateAdapter();
        }

        public Builder name(String name) {
            customerUpdateAdapter.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            customerUpdateAdapter.gender = gender;
            return this;
        }

        public Builder address(AddressAdapter address) {
            customerUpdateAdapter.address = address;
            return this;
        }

        public CustomerUpdateAdapter build() {
            return customerUpdateAdapter;
        }

    }
}
