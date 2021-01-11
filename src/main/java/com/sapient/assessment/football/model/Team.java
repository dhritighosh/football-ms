package com.sapient.assessment.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Team {
    @JsonProperty("team_key")
    private String teamId;
    @JsonProperty("team_name")
    private String teamName;
}
