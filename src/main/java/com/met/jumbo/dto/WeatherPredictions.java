package com.met.jumbo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherPredictions {
    private WeatherDTO today;
    private WeatherDTO tomorrow;
    private List<WeatherMinDTO> predictions;
}
