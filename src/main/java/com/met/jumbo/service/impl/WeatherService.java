package com.met.jumbo.service.impl;

import com.met.jumbo.dao.WeatherDao;
import com.met.jumbo.dto.WeatherDTO;
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
    public WeatherDTO details(String address, String type, LocalDate date) throws WeatherNotFoundException {
        switch (type){
            case "CITY":
                return formatWeather(dao.findByCityAndRecordDate(address, date).orElseThrow(() -> new WeatherNotFoundException()));
            case "ZIPCODE":
                return formatWeather(dao.findByZipcodeAndRecordDate(address,date).orElseThrow(() -> new WeatherNotFoundException()));
            default:
                throw new WeatherNotFoundException();
        }
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
