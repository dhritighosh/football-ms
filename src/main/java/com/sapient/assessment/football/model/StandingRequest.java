package com.sapient.assessment.football.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandingRequest {
    private String countryName;
    private String leagueName;
    private String teamName;
}
