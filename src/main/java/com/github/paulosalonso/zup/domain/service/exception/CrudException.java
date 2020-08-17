package com.github.paulosalonso.zup.domain.service.exception;

public class CrudException extends RuntimeException {
    public CrudException() {}

    public CrudException(String message) {
        super(message);
    }

    public CrudException(String message, Throwable cause) {
        super(message, cause);
    }
}
