package org.example.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCodeDefault {

    SUCCESS("Success", HttpStatus.OK.value(), ErrorCodeStatus.SUCCESS.status),
    DATABASE_ERROR("Can't connect to database", HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeStatus.FAILED.status),
    ERROR_CODE_NOT_FOUND("Error code not found", 404, ErrorCodeStatus.FAILED.status),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST.value(), ErrorCodeStatus.FAILED.status),
    DATA_MUST_NOT_BE_EMPTY("${arg} must not be empty", HttpStatus.BAD_REQUEST.value(), ErrorCodeStatus.FAILED.status),
    DATA_NOT_FOUND("${arg} not found", HttpStatus.BAD_REQUEST.value(), ErrorCodeStatus.FAILED.status),
    PASSWORD_INCORRECT("Password incorrect", HttpStatus.BAD_REQUEST.value(), ErrorCodeStatus.FAILED.status),
    SESSION_EXPIRED("Session expired", HttpStatus.UNAUTHORIZED.value(), ErrorCodeStatus.FAILED.status),

    ALREADY_EXIST("${arg} already exist", HttpStatus.BAD_REQUEST.value(), ErrorCodeStatus.FAILED.status);

    private final String message;
    private final Integer httpStatusCode;
    private final Integer status;

    ErrorCodeDefault(String message, Integer httpStatusCode, Integer status) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public Integer getStatus() {
        return status;
    }
}
