package com.sapient.assessment.football.controller;

import com.sapient.assessment.football.model.StandingRequest;
import com.sapient.assessment.football.model.StandingResponse;
import com.sapient.assessment.football.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football/standing")
public class FootballRestController {

    @Autowired
    private FootballService service;

    //http://localhost:8888/football/standing?countryName=england&leagueName=Premier%20League&teamName=Manchester%20City

    @GetMapping
    public StandingResponse getStanding(@ModelAttribute StandingRequest request){
        return service.getStandingResponse(request);
    }
}
