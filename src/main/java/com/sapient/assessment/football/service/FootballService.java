package com.sapient.assessment.football.service;


import com.sapient.assessment.football.exception.CustomException;
import com.sapient.assessment.football.facade.FootballApiFacade;
import com.sapient.assessment.football.model.StandingRequest;
import com.sapient.assessment.football.model.StandingResponse;
import com.sapient.assessment.football.validator.FootballValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballService {

    @Autowired
    private FootballValidator validator;
    @Autowired
    private FootballApiFacade facade;

    public StandingResponse getStandingResponse(StandingRequest request) throws CustomException {
        validator.validate(request);
        StandingResponse response = new StandingResponse();
        response.setCountry(facade.getCountry(request.getCountryName()));
        response.setLeague(facade.getLeague(response.getCountry().getCountryId(), request.getLeagueName()));
        response.setTeam(facade.getTeam(response.getLeague().getLeagueId(), request.getTeamName()));
        response.setOverallLeaguePosition(facade.getOverallLeaguePosition(response.getLeague().getLeagueId(),
                response.getTeam().getTeamId()));
        return response;
    }


}
