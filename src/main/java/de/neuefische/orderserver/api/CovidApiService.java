package de.neuefische.orderserver.api;

import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CovidApiService {

    private final static String apiUrl = "https://api.covid19api.com/country/germany";
    private final RestTemplate restTemplate = new RestTemplate();

    public CovidApiCountryPerDay[] getCovidApiCountryPerDays() {
        ResponseEntity<CovidApiCountryPerDay[]> response = restTemplate.getForEntity(apiUrl, CovidApiCountryPerDay[].class);
        return response.getBody();
    }
}
