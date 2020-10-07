package de.neuefische.orderserver.model.covidapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidApiCountryPerDay {

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Confirmed")
    private int confirmed;
}
