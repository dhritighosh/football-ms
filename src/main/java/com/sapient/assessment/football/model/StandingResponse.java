package com.sapient.assessment.football.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandingResponse {
    private Country country;
    private League league;
    private Team team;
    private String overallLeaguePosition;
}
