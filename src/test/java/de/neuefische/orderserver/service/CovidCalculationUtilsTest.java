package de.neuefische.orderserver.service;

import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.model.covidapi.CovidApiCountryPerDay;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.neuefische.orderserver.service.CovidCalculationUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CovidCalculationUtilsTest {


    @Test
    public void mapCovidValues(){
        //GIVEN
        CovidApiCountryPerDay[] values = {
                new CovidApiCountryPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new CovidApiCountryPerDay("2020-04-05 20:50:31 +0000 UTC", 100)
        };

        //WHEN

        List<ConfirmedPerDay> confirmedPerDays = mapCovidDayValues(values);

        //THEN
        assertThat(confirmedPerDays, contains(
                new ConfirmedPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new ConfirmedPerDay("2020-04-05 20:50:31 +0000 UTC", 100)
                ));
    }


    @Test
    public void filterValuesGreaterThan100(){
        //GIVEN
        List<ConfirmedPerDay> values = List.of(
                new ConfirmedPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new ConfirmedPerDay("2020-04-05 20:50:31 +0000 UTC", 100),
                new ConfirmedPerDay("2020-04-06 20:50:31 +0000 UTC", 120),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 150)
        );

        //WHEN
        List<ConfirmedPerDay> filteredValues = filterCovidValuesGreaterThan100(values);

        //THEN
        assertThat(filteredValues, contains(
                new ConfirmedPerDay("2020-04-06 20:50:31 +0000 UTC", 120),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 150)
        ));
    }

    @Test
    public void filterValuesGreaterThan100AndLowerThan200(){
        //GIVEN
        List<ConfirmedPerDay> values = List.of(
                new ConfirmedPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new ConfirmedPerDay("2020-04-05 20:50:31 +0000 UTC", 100),
                new ConfirmedPerDay("2020-04-06 20:50:31 +0000 UTC", 120),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 150),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 250)
        );

        //WHEN
        List<ConfirmedPerDay> filteredValues = filterCovidValuesGreaterThan100AndLower200(values);

        //THEN
        assertThat(filteredValues, contains(
                new ConfirmedPerDay("2020-04-06 20:50:31 +0000 UTC", 120),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 150)
        ));
    }

    @Test
    public void checkIfValueExistsGreater150ShouldReturnTrue(){
        //GIVEN
        List<ConfirmedPerDay> values = List.of(
                new ConfirmedPerDay("2020-04-04 20:50:31 +0000 UTC", 10),
                new ConfirmedPerDay("2020-04-05 20:50:31 +0000 UTC", 100),
                new ConfirmedPerDay("2020-04-06 20:50:31 +0000 UTC", 120),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 150),
                new ConfirmedPerDay("2020-04-07 20:50:31 +0000 UTC", 250)
        );

        //WHEN
        boolean check = checkIfValueExistsGreater150(values);

        //THEN
        assertThat(check, is(
               true
        ));
    }
}
