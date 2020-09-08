package com.github.paulosalonso.zup.adapter.controller.model.customer;

import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;

public class AddressAdapter {

    private CityResponseAdapter city;
    private String postalCode;
    private String street;
    private String number;
    private String district;
    private String complement;

    public static Builder of() {
        return new Builder();
    }

    public CityResponseAdapter getCity() {
        return city;
    }

    public void setCity(CityResponseAdapter city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public static final class Builder {

        private final AddressAdapter addressAdapter;

        private Builder() {
            addressAdapter = new AddressAdapter();
        }

        public Builder city(CityResponseAdapter cityVO) {
            addressAdapter.city = cityVO;
            return this;
        }

        public Builder postalCode(String postalCode) {
            addressAdapter.postalCode = postalCode;
            return this;
        }

        public Builder street(String street) {
            addressAdapter.street = street;
            return this;
        }

        public Builder number(String number) {
            addressAdapter.number = number;
            return this;
        }

        public Builder district(String district) {
            addressAdapter.district = district;
            return this;
        }

        public Builder complement(String complement) {
            addressAdapter.complement = complement;
            return this;
        }

        public AddressAdapter build() {
            return addressAdapter;
        }

    }
}
