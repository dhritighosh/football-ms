package com.sapient.assessment.football.exception;

import com.sapient.assessment.football.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionManager {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        log.error(ExceptionUtils.getStackTrace(ex));
        return ResponseEntity.status(ex.getResponse().getStatus().value())
                .contentType(MediaType.APPLICATION_JSON).body(ex.getResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error(ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        response.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setErrorMessage(ex.getMessage());
        response.setStackTrace(ExceptionUtils.getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
