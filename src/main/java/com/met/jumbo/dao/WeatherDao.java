package com.met.jumbo.dao;

import com.met.jumbo.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherDao extends JpaRepository<Weather, Integer> {
    Optional<Weather> findByZipcodeAndRecordDate(String zipcode, LocalDate date);
    Optional<Weather> findByCityAndRecordDate(String city, LocalDate date);
}
