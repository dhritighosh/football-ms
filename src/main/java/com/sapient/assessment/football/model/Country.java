package com.sapient.assessment.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Country {
    @JsonProperty("country_id")
    private String countryId;
    @JsonProperty("country_name")
    private String countryName;
}
