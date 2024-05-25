package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEntityDto {
    private String message;
    private Integer status;
    private Object data;

    public ResponseEntityDto(String message, Integer status){
        this.message = message;
        this.status = status;
    }
}

