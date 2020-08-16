package com.github.paulosalonso.zup.infrastructure.integration.viacep;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ViaCepIT {

    @Test
    public void whenGetCepInfoThenSuccess() {
        PostalCodeInfo postalCodeInfo = ViaCEP.getCepInfo("89217008");
        assertThat(postalCodeInfo).isNotNull();
        assertThat(postalCodeInfo.getPostalCode()).isEqualTo("89217-008");
        assertThat(postalCodeInfo.getStreet()).isEqualTo("Rua Nazareno");
        assertThat(postalCodeInfo.getComplement()).isEqualTo("");
        assertThat(postalCodeInfo.getDistrict()).isEqualTo("Glória");
        assertThat(postalCodeInfo.getCityName()).isEqualTo("Joinville");
        assertThat(postalCodeInfo.getStateInitials()).isEqualTo("SC");
        assertThat(postalCodeInfo.getIbgeCode()).isEqualTo("4209102");
    }

    @Test
    public void whenGetCepInfoWithInvalidCepThenThrowsInvalidCepException() {
        assertThatThrownBy(() -> ViaCEP.getCepInfo("xxx"))
                .isExactlyInstanceOf(InvalidCepException.class)
                .hasMessage("The postal code 'xxx' is invalid. It must contain 8 numeric characters.");
    }
}
