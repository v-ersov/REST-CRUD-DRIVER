package com.rest.crud.restcrudexample.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> fieldErrors;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(int statusCode, Map<String, String> fieldErrors) {
        this.statusCode = statusCode;
        this.fieldErrors = fieldErrors;
    }
}
