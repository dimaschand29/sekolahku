package org.example.constant;

public enum ErrorCodeStatus {

    SUCCESS(1),
    FAILED(0);

    public Integer status;

    ErrorCodeStatus(Integer status){
        this.status = status;
    }
}
