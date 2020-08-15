package com.github.paulosalonso.zup.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Embeddable
public class Address {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    City city;

    @Pattern(regexp = "[0-9]{8}")
    private String postalCode;

    @NotBlank
    @Size(max = 100)
    private String street;

    @Size(max = 20)
    @Column(name = "address_number")
    private String number;

    @Size(max = 60)
    private String district;

    @Size(max = 60)
    @Column(name = "address_complement")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(street, address.street) &&
                Objects.equals(number, address.number) &&
                Objects.equals(district, address.district) &&
                Objects.equals(complement, address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode, street, number, district, complement);
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
