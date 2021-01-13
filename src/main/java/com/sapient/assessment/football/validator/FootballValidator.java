package com.sapient.assessment.football.validator;

import com.sapient.assessment.football.exception.CustomException;
import com.sapient.assessment.football.model.ErrorResponse;
import com.sapient.assessment.football.model.StandingRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FootballValidator {

    public void validate(StandingRequest request) throws CustomException {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setErrorType(HttpStatus.BAD_REQUEST.getReasonPhrase());
        if (request == null ||
                (StringUtils.isAllBlank(request.getCountryName(), request.getLeagueName(), request.getTeamName()))) {
            response.setErrorMessage("Required query parameters not found: countryName, leagueName, teamName");
            throw new CustomException(response);
        }
        if (StringUtils.isBlank(request.getCountryName())) {
            response.setErrorMessage("Required query parameter not found: countryName");
            throw new CustomException(response);
        }
        if (StringUtils.isBlank(request.getLeagueName())) {
            response.setErrorMessage("Required query parameter not found: leagueName");
            throw new CustomException(response);
        }
        if (StringUtils.isBlank(request.getTeamName())) {
            response.setErrorMessage("Required query parameter not found: teamName");
            throw new CustomException(response);
        }

    }
}
