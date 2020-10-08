package de.neuefische.orderserver.service;

import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CovidCalculationUtils {

    public static List<ConfirmedPerDay> mapCovidDayValues(CovidApiCountryPerDay[] covidValues) {
        return Arrays.stream(covidValues) // gleiche zu Arrays.stream(covidValues)
                .map(covidValue -> new ConfirmedPerDay(
                        covidValue.getDate(),
                        covidValue.getConfirmed()
                ))
                .collect(Collectors.toList());
    }


    public static List<ConfirmedPerDay> filterCovidValuesGreaterThan100( List<ConfirmedPerDay> values){
        return values.stream()
                .filter(value -> value.getConfirmed() > 100)
                .collect(Collectors.toList());
    }
    public static List<ConfirmedPerDay> filterCovidValuesGreaterThan100AndLower200( List<ConfirmedPerDay> values){
        return values.stream()
                .filter(value -> value.getConfirmed() > 100)
                .filter(value -> value.getConfirmed() < 200)
                .collect(Collectors.toList());
    }

    public static boolean checkIfValueExistsGreater150( List<ConfirmedPerDay> values){
        return values.stream().anyMatch(value -> value.getConfirmed() > 150);
    }
}
