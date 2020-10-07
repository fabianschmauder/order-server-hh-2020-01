package de.neuefische.orderserver.service;

import de.neuefische.orderserver.api.CovidApiService;
import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidService {


    private final CovidApiService covidApiService;

    public CovidService(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }

    public List<ConfirmedPerDay> getConfirmedCases(){
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays();
        return mapCovidDayValues(covidValues);
    }


    private ArrayList<ConfirmedPerDay> mapCovidDayValues(CovidApiCountryPerDay[] covidValues) {
        ArrayList<ConfirmedPerDay> resultValues = new ArrayList<>();
        for (CovidApiCountryPerDay covidValue : covidValues) {
            resultValues.add(new ConfirmedPerDay(
                    covidValue.getDate(),
                    covidValue.getConfirmed()
            ));
        }
        return resultValues;
    }


}
