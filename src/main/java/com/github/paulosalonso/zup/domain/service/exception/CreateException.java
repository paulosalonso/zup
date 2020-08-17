package com.github.paulosalonso.zup.domain.service.exception;

public class CreateException extends CrudException {
    public CreateException(String message) {
        super(message);
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
