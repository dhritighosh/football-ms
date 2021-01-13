package com.sapient.assessment.football.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.assessment.football.exception.ControllerExceptionManager;
import com.sapient.assessment.football.exception.CustomException;
import com.sapient.assessment.football.model.ErrorResponse;
import com.sapient.assessment.football.model.StandingRequest;
import com.sapient.assessment.football.model.StandingResponse;
import com.sapient.assessment.football.service.FootballService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;

public class FootballRestControllerTest {

    private MockMvc target;
    private ObjectMapper objectMapper;
    @InjectMocks
    private FootballRestController controller;

    @Mock
    private FootballService service;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        target = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ControllerExceptionManager()).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getStandingSuccess() throws Exception{
        StandingResponse expected =
                objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("success_response.json"), StandingResponse.class);
        Mockito.when(controller.getStanding(ArgumentMatchers.any(StandingRequest.class)))
                .thenReturn(expected);
        StandingResponse actual = objectMapper.readValue(target.perform(MockMvcRequestBuilders
                .get("/football/standing?countryName=england&leagueName=Championship&teamName=Bournemouth"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), StandingResponse.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getOverallLeaguePosition(),actual.getOverallLeaguePosition());
    }

    @Test
    public void getStandingCustomException() throws Exception{
        ErrorResponse expected =
                objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("custom_exception_response.json"), ErrorResponse.class);
        expected.setStatus(HttpStatus.BAD_REQUEST);
        Mockito.when(controller.getStanding(ArgumentMatchers.any(StandingRequest.class)))
                .thenThrow(new CustomException(expected));
        ErrorResponse actual = objectMapper.readValue(target.perform(MockMvcRequestBuilders
                .get("/football/standing?countryName=england&leagueName=Championship&teamName=Bournemouth"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString(), ErrorResponse.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getErrorMessage(),actual.getErrorMessage());
    }

    @Test
    public void getStandingGenericException() throws Exception{
        Mockito.when(controller.getStanding(ArgumentMatchers.any(StandingRequest.class)))
                .thenThrow(new RestClientException("Unable to reach Service"));
        ErrorResponse actual = objectMapper.readValue(target.perform(MockMvcRequestBuilders
                .get("/football/standing?countryName=england&leagueName=Championship&teamName=Bournemouth"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn()
                .getResponse()
                .getContentAsString(), ErrorResponse.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals("Unable to reach Service",actual.getErrorMessage());
    }

}
