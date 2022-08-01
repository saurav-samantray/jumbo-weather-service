package com.met.jumbo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherMinDTO {
    private String code;
    private String temperature;
    private String date;
}
