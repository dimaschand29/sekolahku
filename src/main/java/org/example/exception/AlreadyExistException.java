package org.example.exception;

import lombok.Data;

@Data
public class AlreadyExistException extends RuntimeException {
    private final String arg;
    public AlreadyExistException(String arg) {
        super();
        this.arg = arg;
    }
}

