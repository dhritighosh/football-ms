package com.sapient.assessment.football.facade;

import com.sapient.assessment.football.configuration.FootballConfiguration;
import com.sapient.assessment.football.model.Country;
import com.sapient.assessment.football.model.League;
import com.sapient.assessment.football.model.Standing;
import com.sapient.assessment.football.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class FootballApiFacade {
    @Autowired
    private FootballConfiguration config;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Country getCountry(String countryName){
        Country[] countries = restTemplateBuilder.build().getForObject(config.getGetCountriesUrl(), Country[].class, config.getKey());
        HashMap<String, Country> countryMap = new HashMap<>();
        Arrays.asList(countries).forEach(country -> countryMap.put(country.getCountryName().trim().toUpperCase(), country));
        return countryMap.get(countryName.toUpperCase().trim());
    }

    public League getLeague(String countryId, String leagueName){
        League[] leagues = restTemplateBuilder.build().getForObject(config.getGetCompetitionsUrl(), League[].class, countryId, config.getKey());
        HashMap<String, League> leagueMap = new HashMap<>();
        Arrays.asList(leagues).forEach(league -> leagueMap.put(league.getLeagueName().trim().toUpperCase(), league));
        return leagueMap.get(leagueName.toUpperCase().trim());
    }

    public Team getTeam(String leagueId, String teamName){
        Team[] teams = restTemplateBuilder.build().getForObject(config.getGetTeamsUrl(), Team[].class, leagueId, config.getKey());
        HashMap<String, Team> teamMap = new HashMap<>();
        Arrays.asList(teams).forEach(team -> teamMap.put(team.getTeamName().trim().toUpperCase(), team));
        return teamMap.get(teamName.toUpperCase().trim());
    }

    public String getOverallLeaguePosition(String leagueId, String teamId){
        Standing[] standings = restTemplateBuilder.build().getForObject(config.getGetStandingUrl(), Standing[].class, leagueId, config.getKey());
        HashMap<String, Standing> standingMap = new HashMap<>();
        Arrays.asList(standings).forEach(standing -> standingMap.put(standing.getTeamId().trim().toUpperCase(), standing));
        return standingMap.get(teamId).getOverallPosition();
    }

}
