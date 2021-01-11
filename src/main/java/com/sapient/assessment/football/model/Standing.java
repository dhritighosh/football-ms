package com.sapient.assessment.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Standing {
    @JsonProperty("team_key")
    private String teamId;
    @JsonProperty("overall_league_position")
    private String overallPosition;
}
