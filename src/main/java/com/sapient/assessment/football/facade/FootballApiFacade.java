package com.sapient.assessment.football.facade;

import com.sapient.assessment.football.configuration.FootballConfiguration;
import com.sapient.assessment.football.exception.CustomException;
import com.sapient.assessment.football.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class FootballApiFacade {
    @Autowired
    private FootballConfiguration config;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Country getCountry(String countryName) throws CustomException {
        Country[] countries;
        try {
            countries = restTemplateBuilder.build().getForObject(config.getGetCountriesUrl(), Country[].class, config.getKey());
        } catch (RestClientException ex) {
            throw populateServiceUnavailableException(ex);
        }
        HashMap<String, Country> countryMap = new HashMap<>();
        Arrays.asList(countries).forEach(country -> countryMap.put(country.getCountryName().trim().toUpperCase(), country));
        if (countryMap.get(countryName.toUpperCase().trim()) == null)
            throw populateInvalidAttributeException("countryName");
        return countryMap.get(countryName.toUpperCase().trim());
    }

    public League getLeague(String countryId, String leagueName) throws CustomException {
        League[] leagues;
        try {
            leagues = restTemplateBuilder.build().getForObject(config.getGetCompetitionsUrl(), League[].class, countryId, config.getKey());
        } catch (RestClientException ex) {
            throw populateServiceUnavailableException(ex);
        }
        HashMap<String, League> leagueMap = new HashMap<>();
        Arrays.asList(leagues).forEach(league -> leagueMap.put(league.getLeagueName().trim().toUpperCase(), league));
        if (leagueMap.get(leagueName.toUpperCase().trim()) == null)
            throw populateInvalidAttributeException("leagueName");
        return leagueMap.get(leagueName.toUpperCase().trim());
    }

    public Team getTeam(String leagueId, String teamName) throws CustomException {
        Team[] teams;
        try {
            teams = restTemplateBuilder.build().getForObject(config.getGetTeamsUrl(), Team[].class, leagueId, config.getKey());
        } catch (RestClientException ex) {
            throw populateServiceUnavailableException(ex);
        }
        HashMap<String, Team> teamMap = new HashMap<>();
        Arrays.asList(teams).forEach(team -> teamMap.put(team.getTeamName().trim().toUpperCase(), team));
        if (teamMap.get(teamName.toUpperCase().trim()) == null)
            throw populateInvalidAttributeException("teamName");
        return teamMap.get(teamName.toUpperCase().trim());
    }

    public String getOverallLeaguePosition(String leagueId, String teamId) throws CustomException {
        Standing[] standings;
        try {
            standings = restTemplateBuilder.build().getForObject(config.getGetStandingUrl(), Standing[].class, leagueId, config.getKey());
        } catch (RestClientException ex) {
            throw populateServiceUnavailableException(ex);
        }
        HashMap<String, Standing> standingMap = new HashMap<>();
        Arrays.asList(standings).forEach(standing -> standingMap.put(standing.getTeamId().trim().toUpperCase(), standing));
        return standingMap.get(teamId).getOverallPosition();
    }

    private CustomException populateServiceUnavailableException(RestClientException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        response.setErrorType(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        response.setErrorMessage(ex.getMessage());
        return new CustomException(response);
    }

    private CustomException populateInvalidAttributeException(String attribute) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setErrorType(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setErrorMessage("Invalid " + attribute);
        return new CustomException(response);
    }
}
