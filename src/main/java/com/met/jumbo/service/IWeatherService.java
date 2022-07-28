package com.met.jumbo.service;

import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.exception.WeatherNotFoundException;
import com.met.jumbo.model.Weather;

import java.time.LocalDate;

public interface IWeatherService {
    WeatherDTO details(String address, String type, LocalDate date) throws WeatherNotFoundException;
}
