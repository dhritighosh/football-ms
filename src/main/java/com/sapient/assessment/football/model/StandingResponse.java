package com.sapient.assessment.football.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandingResponse {
    private FootballEntity country;
    private FootballEntity league;
    private FootballEntity team;
    private String overallLeaguePosition;
}
