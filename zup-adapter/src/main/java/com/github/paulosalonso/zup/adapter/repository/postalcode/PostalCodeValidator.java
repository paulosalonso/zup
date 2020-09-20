package com.github.paulosalonso.zup.adapter.repository.postalcode;

public interface PostalCodeValidator {

    String CEP_PATTERN = "[0-9]{8}";

    static void validatePostalCode(String cep) {
        if (!cep.matches(CEP_PATTERN)) {
            String message = String.format(
                    "The postal code '%s' is invalid. It must contain 8 numeric characters.",
                    cep);

            throw new InvalidPostalCodeException(message);
        }
    }
}
