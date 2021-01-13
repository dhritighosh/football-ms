package com.sapient.assessment.football.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.assessment.football.facade.FootballApiFacade;
import com.sapient.assessment.football.model.StandingRequest;
import com.sapient.assessment.football.model.StandingResponse;
import com.sapient.assessment.football.validator.FootballValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;

public class FootballServiceTest {

    @InjectMocks
    public FootballService service;
    @Mock
    public FootballValidator validator;
    @Mock
    public FootballApiFacade facade;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getStandingResponse() throws Exception{
        StandingResponse expected = populateResponse();
        Mockito.when(facade.getCountry(ArgumentMatchers.anyString())).thenReturn(expected.getCountry());
        Mockito.when(facade.getLeague(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(expected.getLeague());
        Mockito.when(facade.getTeam(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(expected.getTeam());
        Mockito.when(facade.getOverallLeaguePosition(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(expected.getOverallLeaguePosition());
        StandingResponse actual = service.getStandingResponse(populateRequest());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getOverallLeaguePosition(), actual.getOverallLeaguePosition());
    }


    private StandingRequest populateRequest(){
        StandingRequest request = new StandingRequest();
        request.setCountryName("england");
        request.setLeagueName("Championship");
        request.setTeamName("Bournemouth");
        return request;
    }

    private StandingResponse populateResponse() throws Exception{
        StandingResponse response = new ObjectMapper().readValue(getClass().getClassLoader().getResourceAsStream("success_response.json"), StandingResponse.class);
        return  response;
    }
}
