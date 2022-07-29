package com.met.jumbo.service.impl;

import com.met.jumbo.dao.WeatherDao;
import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.exception.WeatherNotFoundException;
import com.met.jumbo.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherServiceTest {

    @InjectMocks
    WeatherService service;

    @Mock
    WeatherDao dao;



    @Test
    public void validZipCode_success() throws WeatherNotFoundException {
        WeatherDTO w = service.details("560103", "ZIPCODE", LocalDate.now());
        assertEquals("sunny", w.getCode());
        assertEquals("Bellandur, Bangalore", w.getLocation());
        assertEquals("560103", w.getZipcode());
        assertEquals("Bangalore", w.getCity());
        assertEquals("Clear Day", w.getDescription());
        assertEquals("32°C", w.getTemperature());
        assertEquals("1011 mbar", w.getPressure());
        assertEquals("15 km/h WNW", w.getWind());
        assertEquals("30%", w.getHumidity());
    }

    @Test
    public void invalidZipCode_exception() {
        assertThrows(WeatherNotFoundException.class, () -> service.details("000000", "ZIPCODE", LocalDate.now()));
    }

    @Test
    public void validCity_success() throws WeatherNotFoundException {
        WeatherDTO w = service.details("Bangalore", "CITY", LocalDate.now());
        assertEquals("snow", w.getCode());
        assertEquals("Sarjapur, Bangalore", w.getLocation());
        assertEquals("560035", w.getZipcode());
        assertEquals("Bangalore", w.getCity());
        assertEquals("Snow Storm", w.getDescription());
        assertEquals("20°C", w.getTemperature());
        assertEquals("1011 mbar", w.getPressure());
        assertEquals("15 km/h WNW", w.getWind());
        assertEquals("30%", w.getHumidity());
    }

    @Test
    public void invalidCity_exception() {
        assertThrows(WeatherNotFoundException.class, () -> service.details("abcd", "CITY", LocalDate.now()));
    }

    @Test
    public void invalidAddressType_exception() {
        assertThrows(WeatherNotFoundException.class, () -> service.details("abcd", "XYZ", LocalDate.now()));
    }

    @Test
    public void validZip_dateFormat_success() throws WeatherNotFoundException {
        WeatherDTO w1 = service.details("560001", "ZIPCODE", LocalDate.of(2022,07,01));
        assertEquals("Friday, July 1st, 2022", w1.getDate());

        WeatherDTO w2 = service.details("560002", "ZIPCODE", LocalDate.of(2022,06,22));
        assertEquals("Wednesday, June 22nd, 2022", w2.getDate());

        WeatherDTO w3 = service.details("560003", "ZIPCODE", LocalDate.of(2022,05,03));
        assertEquals("Tuesday, May 3rd, 2022", w3.getDate());

        WeatherDTO w4 = service.details("560004", "ZIPCODE", LocalDate.of(2022,04,14));
        assertEquals("Thursday, April 14th, 2022", w4.getDate());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Weather w1 = new Weather(1, "snow", "Sarjapur, Bangalore", "560035", "Bangalore", "Snow Storm", "20°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.now());
        Weather w2 = new Weather(2, "sunny", "Bellandur, Bangalore", "560103", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.now());
        Weather w3 = new Weather(3, "sunny", "Home, Bangalore", "560001", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,07,01));
        Weather w4 = new Weather(4, "sunny", "Home 2, Bangalore", "560002", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,06,22));
        Weather w5 = new Weather(5, "sunny", "Home 3, Bangalore", "560003", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,05,03));
        Weather w6 = new Weather(6, "sunny", "Home 4, Bangalore", "560004", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,04,14));
        Mockito.when(dao.findByCityAndRecordDate("Bangalore", LocalDate.now())).thenReturn(Optional.of(w1));
        Mockito.when(dao.findByZipcodeAndRecordDate("560103", LocalDate.now())).thenReturn(Optional.of(w2));

        Mockito.when((dao.findByZipcodeAndRecordDate("560001", LocalDate.of(2022,07,01)))).thenReturn(Optional.of(w3));
        Mockito.when((dao.findByZipcodeAndRecordDate("560002", LocalDate.of(2022,06,22)))).thenReturn(Optional.of(w4));
        Mockito.when((dao.findByZipcodeAndRecordDate("560003", LocalDate.of(2022,05,03)))).thenReturn(Optional.of(w5));
        Mockito.when((dao.findByZipcodeAndRecordDate("560004", LocalDate.of(2022,04,14)))).thenReturn(Optional.of(w6));
    }
}