package com.met.jumbo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class WeatherDTO {

    private String code;

    private String location;

    private String zipcode;

    private String city;

    private String description;

    private String temperature;

    private String pressure;

    private String humidity;

    private String wind;

    private String date;

}
