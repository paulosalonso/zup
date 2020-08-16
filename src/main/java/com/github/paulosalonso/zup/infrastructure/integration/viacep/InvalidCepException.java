package com.github.paulosalonso.zup.infrastructure.integration.viacep;

public class InvalidCepException extends RuntimeException {
    public InvalidCepException(String message) {
        super(message);
    }
}
