package org.example.exception;

import lombok.Data;
import org.example.constant.ErrorCodeDefault;

@Data
public class ErrorException extends RuntimeException {
    private final ErrorCodeDefault errorCodeDefault;
    public ErrorException(ErrorCodeDefault errorCodeDefault) {
        super();
        this.errorCodeDefault = errorCodeDefault;
    }
}

