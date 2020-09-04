package com.github.paulosalonso.zup.domain;

import java.util.Objects;

public class PostalCodeInfo {

    private String postalCode;
    private String street;
    private String complement;
    private String district;
    private String cityName;
    private String stateInitials;
    private String ibgeCode;

    public static Builder of() {
        return new Builder();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateInitials() {
        return stateInitials;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public static final class Builder {

        private final PostalCodeInfo postalCodeInfo;

        private Builder() {
            postalCodeInfo = new PostalCodeInfo();
        }

        public Builder postalCode(String postalCode) {
            postalCodeInfo.postalCode = postalCode;
            return this;
        }

        public Builder street(String street) {
            postalCodeInfo.street = street;
            return this;
        }

        public Builder complement(String complement) {
            postalCodeInfo.complement = complement;
            return this;
        }

        public Builder district(String district) {
            postalCodeInfo.district = district;
            return this;
        }

        public Builder cityName(String cityName) {
            postalCodeInfo.cityName = cityName;
            return this;
        }

        public Builder stateInitials(String stateInitials) {
            postalCodeInfo.stateInitials = stateInitials;
            return this;
        }

        public Builder ibgeCode(String ibgeCode) {
            postalCodeInfo.ibgeCode = ibgeCode;
            return this;
        }

        public PostalCodeInfo build() {
            return postalCodeInfo;
        }
    }
}
