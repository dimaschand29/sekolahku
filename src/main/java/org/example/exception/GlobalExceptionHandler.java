package org.example.exception;

import org.example.constant.ErrorCodeDefault;
import org.example.service.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @ExceptionHandler(ErrorException.class)
    public <T> ResponseEntity<Object> handleCustomException(ErrorException ex) {
        ErrorCodeDefault errorCodeDefault = ex.getErrorCodeDefault();
        ResponseEntity<Object> response = responseEntityBuilder.buildErrorResponseEntity(errorCodeDefault);
        return response;
    }

    @ExceptionHandler({WebExchangeBindException.class, HttpMessageNotReadableException.class})
    public <T> ResponseEntity<Object> handleBadRequestException () {
        ResponseEntity<Object> response = responseEntityBuilder.buildErrorBadRequestResponseEntity();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseEntity<Object> handleNotNullException (MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String arg = errors.values().stream().map(e -> {
            String init = "";
            init = init+e;
            return init;
        }).collect(Collectors.toList()).toString();

        ErrorCodeDefault errorCodeDefault = ErrorCodeDefault.DATA_MUST_NOT_BE_EMPTY;
        ResponseEntity<Object> response = responseEntityBuilder.buildErrorWithModifiedMessageResponseEntity(errorCodeDefault, arg);
        return response;
    }

    @ExceptionHandler(DataNotFoundException.class)
    public <T> ResponseEntity<Object> handleDataNotFoundException (DataNotFoundException dataNotFoundException) {
        String arg = dataNotFoundException.getArg();
        ErrorCodeDefault errorCodeDefault = ErrorCodeDefault.DATA_NOT_FOUND;
        ResponseEntity<Object> response = responseEntityBuilder.buildErrorWithModifiedMessageResponseEntity(errorCodeDefault, arg);
        return response;
    }

    @ExceptionHandler(AlreadyExistException.class)
    public <T> ResponseEntity<Object> handleAlreadyExistException (AlreadyExistException alreadyExistException) {
        String arg = alreadyExistException.getArg();
        ErrorCodeDefault errorCodeDefault = ErrorCodeDefault.ALREADY_EXIST;
        ResponseEntity<Object> response = responseEntityBuilder.buildErrorWithModifiedMessageResponseEntity(errorCodeDefault, arg);
        return response;
    }
}
