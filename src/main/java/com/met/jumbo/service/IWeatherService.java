package com.met.jumbo.service;

import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.dto.WeatherPredictions;
import com.met.jumbo.exception.WeatherNotFoundException;

import java.time.LocalDate;

public interface IWeatherService {
    WeatherDTO today(String address, String type, LocalDate date) throws WeatherNotFoundException;
    WeatherPredictions predictions(String address, String type, LocalDate date) throws WeatherNotFoundException;
}
