package com.met.jumbo.rest;

import com.met.jumbo.dao.WeatherDao;
import com.met.jumbo.dto.Error;
import com.met.jumbo.dto.SuggestionType;
import com.met.jumbo.dto.WeatherDTO;
import com.met.jumbo.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.met.jumbo.TestConstants.HOST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@Slf4j
class WeatherControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    WeatherDao dao;

    @Test
    public void fetch_details_success() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                HOST + port + "/api/weather/ZIPCODE/560035/"+LocalDate.now(), HttpMethod.GET, requestEntity, WeatherDTO.class);
        log.info("Response Body {}", response.getBody());
        WeatherDTO w = (WeatherDTO) response.getBody();
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
    public void fetch_details_notfound_error() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                HOST + port + "/api/weather/ZIPCODE/560030/"+LocalDate.now(), HttpMethod.GET, requestEntity, Error.class);
        log.info("Response Body {}", response.getBody());
        Error w = (Error) response.getBody();
        assertEquals("JW-1001", w.getCode());
    }

    @Test
    public void fetch_details_invalidAddressType_error() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                HOST + port + "/api/weather/ZIPCODE1/560030/"+LocalDate.now(), HttpMethod.GET, requestEntity, Error.class);
        log.info("Response Body {}", response.getBody());
        Error w = (Error) response.getBody();
        assertEquals("JW-1002", w.getCode());
    }

    @BeforeEach
    void setUp() {
        Weather w1 = new Weather(1, "snow", "Sarjapur, Bangalore", "560035", "Bangalore", "Snow Storm", "20°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.now());
        dao.save(w1);
        Weather w2 = new Weather(2, "sunny", "Bellandur, Bangalore", "560103", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.now());
        dao.save(w2);
        Weather w3 = new Weather(3, "sunny", "Home, Bangalore", "560001", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,07,01));
        dao.save(w3);
        Weather w4 = new Weather(4, "sunny", "Home 2, Bangalore", "560002", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,06,22));
        dao.save(w4);
        Weather w5 = new Weather(5, "sunny", "Home 3, Bangalore", "560003", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,05,03));
        dao.save(w5);
        Weather w6 = new Weather(6, "sunny", "Home 4, Bangalore", "560004", "Bangalore", "Clear Day", "32°C", "1011 mbar", "30%", "15 km/h WNW", LocalDate.of(2022,04,14));
        dao.save(w6);
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }
}