package com.github.paulosalonso.zup.adapter.api.dto.customer;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel("Address")
public class AddressDTO {

    private CityResponseDTO city;

    @ApiModelProperty(example = "00000000", required = true)
    @NotNull
    @Pattern(regexp = "[0-9]{8}")
    private String postalCode;

    @ApiModelProperty(example = "Rua A", required = true)
    @NotBlank
    @Size(max = 100)
    private String street;

    @ApiModelProperty(example = "100")
    @Size(max = 20)
    private String number;

    @ApiModelProperty(example = "Centro")
    @Size(max = 60)
    private String district;

    @ApiModelProperty(example = "Fundos")
    @Size(max = 60)
    private String complement;

    public static Builder of() {
        return new Builder();
    }

    public CityResponseDTO getCity() {
        return city;
    }

    public void setCity(CityResponseDTO city) {
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

        private final AddressDTO addressDTO;

        private Builder() {
            addressDTO = new AddressDTO();
        }

        public Builder city(CityResponseDTO cityVO) {
            addressDTO.city = cityVO;
            return this;
        }

        public Builder postalCode(String postalCode) {
            addressDTO.postalCode = postalCode;
            return this;
        }

        public Builder street(String street) {
            addressDTO.street = street;
            return this;
        }

        public Builder number(String number) {
            addressDTO.number = number;
            return this;
        }

        public Builder district(String district) {
            addressDTO.district = district;
            return this;
        }

        public Builder complement(String complement) {
            addressDTO.complement = complement;
            return this;
        }

        public AddressDTO build() {
            return addressDTO;
        }

    }
}
