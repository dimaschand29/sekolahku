package org.example.service;

import org.example.constant.ErrorCodeDefault;
import org.example.dto.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.example.constant.ErrorCodeDefault.*;

@Service
public class ResponseEntityBuilder <T> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEntityBuilder.class);

    public <T> ResponseEntity<Object> buildNormalResponseEntity(T data) {
        try {
            HttpStatus httpStatus = HttpStatus.valueOf(SUCCESS.getHttpStatusCode());
            Integer status = SUCCESS.getStatus();
            ResponseEntityDto responseEntityDto = new ResponseEntityDto(SUCCESS.getMessage(), status, data);
            ResponseEntity<Object> responseEntity = getResponseEntity(responseEntityDto, httpStatus);
            return responseEntity;
        } catch (Exception e) {
            ResponseEntityDto responseEntityDto = new ResponseEntityDto(ERROR_CODE_NOT_FOUND.getMessage(), ERROR_CODE_NOT_FOUND.getStatus());
            HttpStatus httpStatus = HttpStatus.valueOf(ERROR_CODE_NOT_FOUND.getHttpStatusCode());
            ResponseEntity<Object> responseOtherError = getResponseEntity(responseEntityDto, httpStatus);
            return responseOtherError;
        }
    }

    public static ResponseEntity<Object> getResponseEntity(ResponseEntityDto responseEntityDto, HttpStatus httpStatus){
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(responseEntityDto, httpStatus);
        return responseEntity;
    }

    public <T> ResponseEntity<Object> buildErrorResponseEntity(ErrorCodeDefault errorCodeDefault) {
        try{
            HttpStatus httpStatus = HttpStatus.valueOf(errorCodeDefault.getHttpStatusCode());
            ResponseEntityDto responseEntityDto = new ResponseEntityDto(errorCodeDefault.getMessage(), errorCodeDefault.getStatus());
            ResponseEntity<Object> responseEntity = getResponseEntity(responseEntityDto, httpStatus);
            return responseEntity;
        } catch (Exception e){
            ResponseEntityDto responseEntityDto = new ResponseEntityDto(ERROR_CODE_NOT_FOUND.getMessage(), ERROR_CODE_NOT_FOUND.getStatus());
            HttpStatus httpStatus = HttpStatus.valueOf(ERROR_CODE_NOT_FOUND.getHttpStatusCode());
            ResponseEntity<Object> responseOtherError = getResponseEntity(responseEntityDto, httpStatus);
            return responseOtherError;
        }
    }

    public <T> ResponseEntity<Object> buildErrorBadRequestResponseEntity() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(BAD_REQUEST.getMessage(), BAD_REQUEST.getStatus());
        HttpStatus httpStatus = HttpStatus.valueOf(BAD_REQUEST.getHttpStatusCode());
        ResponseEntity<Object> responseOtherError = getResponseEntity(responseEntityDto, httpStatus);
        return responseOtherError;
    }

    public <T> ResponseEntity<Object> buildErrorWithModifiedMessageResponseEntity(ErrorCodeDefault errorCodeDefault, String arg) {
        String customMessage = errorCodeDefault.getMessage().replace("${arg}",arg);
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(customMessage, errorCodeDefault.getStatus());
        HttpStatus httpStatus = HttpStatus.valueOf(errorCodeDefault.getHttpStatusCode());
        ResponseEntity<Object> responseOtherError = getResponseEntity(responseEntityDto, httpStatus);
        return responseOtherError;
    }

    public <T> ResponseEntity<Object> buildErrorWithMultipleModifiedMessageResponseEntity(ErrorCodeDefault errorCodeDefault, Map<String, String> args) {
        String customMessage = "";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            customMessage = errorCodeDefault.getMessage().replace(entry.getKey(), entry.getValue());
        }

        ResponseEntityDto responseEntityDto = new ResponseEntityDto(customMessage, errorCodeDefault.getStatus());
        HttpStatus httpStatus = HttpStatus.valueOf(errorCodeDefault.getHttpStatusCode());
        ResponseEntity<Object> responseOtherError = getResponseEntity(responseEntityDto, httpStatus);
        return responseOtherError;
    }
}
