package com.github.paulosalonso.zup.infrastructure.integration.viacep;

import java.io.IOException;

public class ViaCEPIntegrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ViaCEPIntegrationException(String message, IOException e) {
        super(message, e);
    }
}
