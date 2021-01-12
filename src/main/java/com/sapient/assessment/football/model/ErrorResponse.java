package com.sapient.assessment.football.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "status")
public class ErrorResponse {
    private HttpStatus status;
    private String errorType;
    private String errorMessage;
    private String stackTrace;
}
