package com.github.paulosalonso.zup.adapter.repository.city.postalcode.viacep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.paulosalonso.zup.domain.PostalCodeInfo;
import com.github.paulosalonso.zup.usecase.port.city.GetPostalCodeInfoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

import static com.github.paulosalonso.zup.adapter.repository.city.postalcode.PostalCodeValidator.validatePostalCode;
import static com.github.paulosalonso.zup.adapter.repository.city.postalcode.viacep.ViaCEPResponseMapper.to;

@Component
public class ViaCEPRepository implements GetPostalCodeInfoPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViaCEPRepository.class);
    private static final String VIACEP_URI = "http://viacep.com.br/ws/%s/json";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final HttpClient httpClient;

    public ViaCEPRepository(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Optional<PostalCodeInfo> getPostalCodeInfo(String cep) {
        LOGGER.info("Getting information of postal code {}", cep);

        validatePostalCode(cep);

        try {
            ViaCEPResponse viaCepResponse = MAPPER.readValue(requestAsJson(cep), ViaCEPResponse.class);

            if (!viaCepResponse.isErro()) {
                return Optional.of(to(viaCepResponse));
            }
        } catch (IOException e) {
            String message = "An error occurred mapping ViaCEP API response to CEPInfo type.";
            LOGGER.error(message, e);
        } catch (ViaCEPIntegrationException e) {
            String message = "An error occurred getting cep information from ViaCEP API.";
            LOGGER.error(message, e);
        }

        return Optional.empty();
    }

    private String requestAsJson(String cep) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(VIACEP_URI, cep)))
                .build();

        try {
            return httpClient.send(request, BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ViaCEPIntegrationException(e);
        }
    }

}
