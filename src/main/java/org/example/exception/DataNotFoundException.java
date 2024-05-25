package org.example.exception;

import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException {
    private final String arg;
    public DataNotFoundException(String arg) {
        super();
        this.arg = arg;
    }
}

