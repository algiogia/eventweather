package org.ivavin.eventweather.service;

import java.util.List;

import org.ivavin.eventweather.model.Forecast;

public interface WeatherService {

	List<Forecast> getWeather(String location) throws WeatherServiceException;
}
