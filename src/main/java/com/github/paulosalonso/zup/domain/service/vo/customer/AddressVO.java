package com.github.paulosalonso.zup.domain.service.vo.customer;

import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;

public class AddressVO {

    private CityVO city;
    private String postalCode;
    private String street;
    private String number;
    private String district;
    private String complement;

    public static Builder of() {
        return new Builder();
    }

    public CityVO getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getDistrict() {
        return district;
    }

    public String getComplement() {
        return complement;
    }

    public static final class Builder {

        private final AddressVO addressVO;

        private Builder() {
            addressVO = new AddressVO();
        }

        public Builder city(CityVO city) {
            addressVO.city = city;
            return this;
        }

        public Builder postalCode(String postalCode) {
            addressVO.postalCode = postalCode;
            return this;
        }

        public Builder street(String street) {
            addressVO.street = street;
            return this;
        }

        public Builder number(String number) {
            addressVO.number = number;
            return this;
        }

        public Builder district(String district) {
            addressVO.district = district;
            return this;
        }

        public Builder complement(String complement) {
            addressVO.complement = complement;
            return this;
        }

        public AddressVO build() {
            return addressVO;
        }

    }
}
