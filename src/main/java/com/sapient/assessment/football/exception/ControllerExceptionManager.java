package com.sapient.assessment.football.exception;

import com.sapient.assessment.football.model.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionManager {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ex.printStackTrace();
        ErrorResponse response = new ErrorResponse();
        response.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setErrorMessage(ex.getMessage());
        response.setStackTrace(ExceptionUtils.getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
