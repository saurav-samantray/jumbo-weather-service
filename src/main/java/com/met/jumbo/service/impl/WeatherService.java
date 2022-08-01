package com.met.jumbo.service.impl;

import com.met.jumbo.dao.WeatherDao;
import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.dto.WeatherPredictions;
import com.met.jumbo.exception.WeatherNotFoundException;
import com.met.jumbo.model.Weather;
import com.met.jumbo.service.IWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@Slf4j
public class WeatherService implements IWeatherService {

    @Autowired
    WeatherDao dao;

    @Override
    public WeatherDTO today(String address, String type, LocalDate date) throws WeatherNotFoundException {
        return formatWeather(dao
                .findByZipcodeAndAddressTypeAndRecordDate(address, type, date)
                .orElseThrow(() -> new WeatherNotFoundException()));
    }

    @Override
    public WeatherPredictions predictions(String address, String type, LocalDate date) {
        WeatherPredictions predictions = new WeatherPredictions();
        dao.findByZipcodeAndAddressTypeAndRecordDate(address, type, date)
                .ifPresent(w -> predictions.setToday(formatWeather(w)));
        dao.findByZipcodeAndAddressTypeAndRecordDate(address, type, date.plusDays(1))
                .ifPresent(w -> predictions.setTomorrow(formatWeather(w)));
        return predictions;
    }

    private static WeatherDTO formatWeather(Weather weather) {
        WeatherDTO dto = new WeatherDTO();
        BeanUtils.copyProperties(weather, dto);

        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder
                .append(weather.getRecordDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                .append(", ")
                .append(weather.getRecordDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                .append(" ")
                .append(dayOfMonthWithSuffix(weather.getRecordDate().getDayOfMonth()))
                .append(", ")
                .append(weather.getRecordDate().getYear());
        dto.setDate(dateBuilder.toString());
        return dto;
    }

    private static String dayOfMonthWithSuffix(int day){
        int lastDigit = day % 10;

        if(lastDigit == 1){
            return day+"st";
        }else if(lastDigit == 2){
            return day+"nd";
        }else if(lastDigit == 3){
            return day+"rd";
        }else{
            return day+"th";
        }
    }
}
