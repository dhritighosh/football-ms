package com.sapient.assessment.football.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "data.api")
@Setter
@Getter
public class FootballConfiguration {
    private String getCountriesUrl;
    private String getTeamsUrl;
    private String getCompetitionsUrl;
    private String getStandingUrl;
    private String key;
}
