package com.sapient.assessment.football.controller;

import com.sapient.assessment.football.model.StandingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football/standing")
public class FootballRestController {

    @GetMapping
    public StandingResponse getStanding(){
        return null;
    }
}