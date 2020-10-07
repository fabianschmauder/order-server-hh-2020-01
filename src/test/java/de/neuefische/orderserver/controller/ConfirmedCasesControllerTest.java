package de.neuefische.orderserver.controller;

import de.neuefische.orderserver.api.CovidApiService;
import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfirmedCasesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    private CovidApiService covidApiService;

    @Test
    public void getConfirmedShouldReturnListOfConfirmedValues() {
        //GIVEN
        String url = "http://localhost:" + port + "/covid/confirmed";
        CovidApiCountryPerDay[] values = {
                new CovidApiCountryPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new CovidApiCountryPerDay("2020-04-05 20:50:31 +0000 UTC", 100)
        };
        when(covidApiService.getCovidApiCountryPerDays()).thenReturn(values);

        //WHEN
        ResponseEntity<ConfirmedPerDay[]> confirmedResponse = restTemplate.getForEntity(url, ConfirmedPerDay[].class);


        //THEN
        assertThat(confirmedResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(confirmedResponse.getBody().length, is(2) );
        assertThat(confirmedResponse.getBody()[0], is(new ConfirmedPerDay("2020-04-04 20:50:31 +0000 UTC", 10)) );
        assertThat(confirmedResponse.getBody()[1], is(new ConfirmedPerDay("2020-04-05 20:50:31 +0000 UTC", 100)) );

    }

}
