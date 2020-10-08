package de.neuefische.orderserver.service;

import de.neuefische.orderserver.api.CovidApiService;
import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.neuefische.orderserver.service.CovidCalculationUtils.mapCovidDayValues;

@Service
public class CovidService {


    private final CovidApiService covidApiService;

    public CovidService(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }

    public List<ConfirmedPerDay> getConfirmedCases() {
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays();
        return mapCovidDayValues(covidValues);
    }


}
