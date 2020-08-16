package com.github.paulosalonso.zup.infrastructure.integration.viacep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ViaCEP {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViaCEP.class);
    private static final String VIACEP_URL = "http://viacep.com.br/ws/%s/json";
    private static final String CEP_PATTERN = "[0-9]{8}";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static PostalCodeInfo getCepInfo(String cep) {
        LOGGER.info("Getting information of postal code {}", cep);

        validateCep(cep);

        try {
            return MAPPER.readValue(request(cep), PostalCodeInfo.class);
        } catch (IOException e) {
            String message = "An error occurred mapping ViaCEP API response to CEPInfo type.";
            throw new ViaCEPIntegrationException(message, e);
        }
    }

    private static BufferedReader request(String cep) {
        try {
            URL url = new URL(String.format(VIACEP_URL, cep));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (MalformedURLException e) {
            return null; // The URL will never be invalid
        } catch (IOException e) {
            String message = "An error occurred connecting to ViaCEP API.";
            throw new ViaCEPIntegrationException(message, e);
        }
    }

    private static void validateCep(String cep) {
        if (!cep.matches(CEP_PATTERN)) {
            String message = String.format(
                    "The postal code '%s' is invalid. It must contain 8 numeric characters.",
                    cep);

            throw new InvalidCepException(message);
        }
    }
}
