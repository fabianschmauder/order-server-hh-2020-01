package de.neuefische.orderserver.controller;

import de.neuefische.orderserver.model.ConfirmedPerDay;
import de.neuefische.orderserver.service.CovidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("covid/confirmed")
public class ConfirmedCasesController {

    private final CovidService covidService;

    public ConfirmedCasesController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping
    public List<ConfirmedPerDay> getConfirmed(){
        return covidService.getConfirmedCases();
    }

}
