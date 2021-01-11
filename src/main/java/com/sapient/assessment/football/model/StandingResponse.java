package com.sapient.assessment.football.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandingResponse {
    private FootballEntity country;
    private FootballEntity league;
    private FootballEntity team;
    private String overallLeaguePosition;
}
