package com.sapient.assessment.football.validator;

import com.sapient.assessment.football.model.StandingRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class FootballValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    public FootballValidator validator;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private StandingRequest populateRequest(){
        StandingRequest request = new StandingRequest();
        request.setCountryName("england");
        request.setLeagueName("Championship");
        request.setTeamName("Bournemouth");
        return request;
    }
}
