package com.sapient.assessment.football.exception;

import com.sapient.assessment.football.model.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
    private ErrorResponse response;

    public CustomException(ErrorResponse response) {
        super(response.getErrorMessage());
        this.response = response;
    }
}
