package com.sapient.assessment.football.validator;

import com.sapient.assessment.football.exception.CustomException;
import com.sapient.assessment.football.model.StandingRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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

    @Test
    public void validateSuccess() throws CustomException {
        validator.validate(populateRequest());
    }

    @Test
    public void validateAllBlankError() throws CustomException{
        expectedException.expect(CustomException.class);
        expectedException.expectMessage("Required query parameters not found: countryName, leagueName, teamName");
        validator.validate(new StandingRequest());
    }

    @Test
    public void validateCountryNameBlankError() throws CustomException{
        expectedException.expect(CustomException.class);
        expectedException.expectMessage("Required query parameter not found: countryName");
        StandingRequest request = populateRequest();
        request.setCountryName(null);
        validator.validate(request);
    }
    @Test
    public void validateLeagueNameBlankError() throws CustomException{
        expectedException.expect(CustomException.class);
        expectedException.expectMessage("Required query parameter not found: leagueName");
        StandingRequest request = populateRequest();
        request.setLeagueName(null);
        validator.validate(request);
    }
    @Test
    public void validateTeamNameBlankError() throws CustomException{
        expectedException.expect(CustomException.class);
        expectedException.expectMessage("Required query parameter not found: teamName");
        StandingRequest request = populateRequest();
        request.setTeamName(null);
        validator.validate(request);
    }


    private StandingRequest populateRequest(){
        StandingRequest request = new StandingRequest();
        request.setCountryName("england");
        request.setLeagueName("Championship");
        request.setTeamName("Bournemouth");
        return request;
    }
}
