package com.met.jumbo.rest;

import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.exception.WeatherNotFoundException;
import com.met.jumbo.model.Weather;
import com.met.jumbo.service.IWeatherService;
import com.met.jumbo.validation.ValidAddressType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Validated
public class WeatherController {

    @Autowired
    IWeatherService service;

    @GetMapping("/{type}/{address}/{date}")
    public ResponseEntity<WeatherDTO> weatherDetails(
            @ValidAddressType @PathVariable String type,
            @PathVariable String address,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date) throws WeatherNotFoundException {
        return new ResponseEntity(service.details(address, type, date),  HttpStatus.OK);
    }
}
