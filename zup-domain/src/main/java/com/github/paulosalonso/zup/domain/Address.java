package com.github.paulosalonso.zup.domain;

public class Address {

    private City city;
    private String postalCode;
    private String street;
    private String number;
    private String district;
    private String complement;

    public static Builder of() {
        return new Builder();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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

        private final Address address;

        private Builder() {
            this.address = new Address();
        }

        public Builder city(City city) {
            address.setCity(city);
            return this;
        }

        public Builder postalCode(String postalCode) {
            address.setPostalCode(postalCode);
            return this;
        }

        public Builder street(String street) {
            address.setStreet(street);
            return this;
        }

        public Builder number(String number) {
            address.setNumber(number);
            return this;
        }

        public Builder district(String district) {
            address.setDistrict(district);
            return this;
        }

        public Builder complement(String complement) {
            address.setComplement(complement);
            return this;
        }

        public Address build() {
            return address;
        }
    }
}
