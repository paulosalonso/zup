package com.github.paulosalonso.zup.adapter.repository.model;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

@Embeddable
public class AddressEmbeddable {

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    CityEntity city;

//    @NotNull
//    @Pattern(regexp = "[0-9]{8}")
    private String postalCode;

//    @NotBlank
//    @Size(max = 100)
    private String street;

//    @Size(max = 20)
    @Column(name = "address_number")
    private String number;

//    @Size(max = 60)
    private String district;

//    @Size(max = 60)
    @Column(name = "address_complement")
    private String complement;

    public static Builder of() {
        return new Builder();
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
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

        private final AddressEmbeddable address;

        private Builder() {
            this.address = new AddressEmbeddable();
        }

        public Builder city(CityEntity city) {
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

        public AddressEmbeddable build() {
            return address;
        }
    }
}
